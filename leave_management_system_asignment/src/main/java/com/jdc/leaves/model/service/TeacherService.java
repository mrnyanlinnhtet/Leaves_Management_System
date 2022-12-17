package com.jdc.leaves.model.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.leaves.model.dto.input.TeacherForm;
import com.jdc.leaves.model.dto.output.IdWithName;
import com.jdc.leaves.model.dto.output.TeacherListVO;

@Service
public class TeacherService {

	
	private NamedParameterJdbcTemplate namedJdbc;
	private SimpleJdbcInsert accountInsert;
	private SimpleJdbcInsert teacherInsert;

	private final String SELECT_PROJECTION = """
			SELECT t.id,a.name,t.phone,a.email,t.assign_date assignDate,COUNT(c.id) classCount 
			FROM teacher t
			JOIN account a
			ON a.id = t.id
			LEFT JOIN classes c ON
			 c.teacher_id = t.id
						""";
	private final String SELECT_GROUP_BY = " GROUP BY t.id,a.name,t.phone,a.email,t.assign_date";
	
	private static final String SELECT_DATA = """
			SELECT t.id,a.name 
			FROM teacher t JOIN account a
			ON t.id = a.id WHERE deleted =:delete 
			""";

	private RowMapper<TeacherListVO> mapper;

	@Autowired
	private PasswordEncoder encoder;

	public TeacherService(DataSource dataSource) {
		namedJdbc = new NamedParameterJdbcTemplate(dataSource);

		// For Account Table
		accountInsert = new SimpleJdbcInsert(dataSource);
		accountInsert.setTableName("account");
		accountInsert.setGeneratedKeyName("id");
		accountInsert.setColumnNames(List.of("name", "role", "email", "password"));

		// For Teacher Table
		teacherInsert = new SimpleJdbcInsert(dataSource);
		teacherInsert.setTableName("teacher");

		// For RowMapper
		mapper = new BeanPropertyRowMapper<>(TeacherListVO.class);

	}

	@Transactional
	public int save(TeacherForm form) {
		if (form.getId() == 0) {
			return insert(form);
			
		}
		return edit(form);
	}

	// Search
	public List<TeacherListVO> search(Optional<String> name, Optional<String> phone, Optional<String> email) {

		var where = new StringBuffer();
		var params = new HashMap<String, Object>();

		// Dynamic Query For Email
		where.append(email.filter(StringUtils::hasLength).map(a -> {
			params.put("email", a.toLowerCase().concat("%"));
			return "AND LOWER(a.email) LIKE :email ";
		}).orElse(""));

		// Dynamic Query For Name
		where.append(name.filter(StringUtils::hasLength).map(a -> {
			params.put("name", a.toLowerCase().concat("%"));
			return "AND LOWER(a.name) LIKE :name ";
		}).orElse(""));

		// Dynamic Query For Phone
		where.append(phone.filter(StringUtils::hasLength).map(t -> {
			params.put("phone", t.concat("%"));
			return "AND t.phone LIKE :phone ";
		}).orElse(""));

		final String SQL = "%s WHERE 1=1 %s %s".formatted(SELECT_PROJECTION, where.toString(), SELECT_GROUP_BY);
		return namedJdbc.query(SQL, params, mapper);
	}

	// Find By Id
	public TeacherListVO findById(int id) {
		final String SQL = "%s WHERE t.id=:id %s".formatted(SELECT_PROJECTION, SELECT_GROUP_BY);

		var params = new HashMap<String, Object>();
		params.put("id", id);

		return namedJdbc.queryForObject(SQL, params, mapper);
	}

	// Get Available Teachers
	public List<IdWithName> getAvailableTeachers() {
		var params = new HashMap<String, Object>();
		params.put("delete", false);
		return namedJdbc.query(SELECT_DATA, params,new BeanPropertyRowMapper<>(IdWithName.class));
	}

	// Insert Form
	private int insert(TeacherForm form) {
		// Insert To Account
		var paramsForAccount = new HashMap<String, Object>();
		paramsForAccount.put("name", form.getName());
		paramsForAccount.put("role", "Teacher");
		paramsForAccount.put("email", form.getEmail());
		paramsForAccount.put("password", encoder.encode(form.getPhone()));
		var account = accountInsert.executeAndReturnKey(paramsForAccount);

		// Insert To Teacher
		var paramsForTeacher = new HashMap<String, Object>();
		paramsForTeacher.put("id", account.intValue());
		paramsForTeacher.put("phone", form.getPhone());
		paramsForTeacher.put("assign_date", Date.valueOf(form.getAssignDate()));
		teacherInsert.execute(paramsForTeacher);
		return account.intValue();
	}

	// Edit Form
	private int edit(TeacherForm form) {
		// Update For Account
		final String UPDATE_ACCOUNT = "UPDATE account SET name=:name WHERE id =:id";
		var paramsForAccount = new HashMap<String, Object>();
		paramsForAccount.put("name", form.getName());
		paramsForAccount.put("id", form.getId());
		namedJdbc.update(UPDATE_ACCOUNT, paramsForAccount);

		// Update For Teacher
		final String UPDATE_TEACHER = "UPDATE teacher SET phone=:phone,assign_date=:assign_date WHERE id=:id";
		var paramsForTeacher = new HashMap<String, Object>();
		paramsForTeacher.put("phone", form.getPhone());
		paramsForTeacher.put("assign_date", Date.valueOf(form.getAssignDate()));
		paramsForTeacher.put("id", form.getId());
		namedJdbc.update(UPDATE_TEACHER, paramsForTeacher);

		return form.getId();
	}

}