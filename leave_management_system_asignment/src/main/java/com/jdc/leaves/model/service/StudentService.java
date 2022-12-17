package com.jdc.leaves.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.leaves.model.dto.input.RegistrationForm;
import com.jdc.leaves.model.dto.output.StudentDetailVO;
import com.jdc.leaves.model.dto.output.StudentListVO;

@Service
public class StudentService {

	private NamedParameterJdbcTemplate template;
	private SimpleJdbcInsert accountInsert;
	private SimpleJdbcInsert studentInsert;

	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private PasswordEncoder encoder;
	private final String SELECT_PROJECTION = """
			SELECT s.id,a.name,s.phone,a.email,s.education,COUNT(r.classes_id) classCount
			FROM student s JOIN account a
			ON s.id = a.id
			LEFT JOIN registration r
			ON s.id = r.student_id WHERE 1 = 1""";

	private final String SELECT_GROUP_BY = " GROUP BY s.id,a.name,s.phone,a.email,s.education";

	public StudentService(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);

		// For Account Insert
		accountInsert = new SimpleJdbcInsert(dataSource);
		accountInsert.setGeneratedKeyName("id");
		accountInsert.setTableName("account");
		accountInsert.setColumnNames(List.of("name", "role", "email", "password"));

		// For Student Insert
		studentInsert = new SimpleJdbcInsert(dataSource);
		studentInsert.setTableName("student");
	}

	// Search Process
	public List<StudentListVO> search(Optional<String> name, Optional<String> phone, Optional<String> email) {

		var where = new StringBuffer();
		var params = new HashMap<String, Object>();

		where.append(name.filter(StringUtils::hasLength).map(a -> {
			params.put("name", a.toLowerCase().concat("%"));
			return " AND LOWER(a.name) LIKE :name";
		}).orElse(""));

		where.append(phone.filter(StringUtils::hasLength).map(a -> {
			params.put("phone", a.concat("%"));
			return " AND LOWER(s.phone) LIKE :phone";
		}).orElse(""));

		where.append(email.filter(StringUtils::hasLength).map(a -> {
			params.put("email", a.toLowerCase().concat("%"));
			return " AND LOWER(a.email) LIKE :email";
		}).orElse(""));

		final String SQL = "%s %s %s ORDER BY a.name".formatted(SELECT_PROJECTION, where.toString(), SELECT_GROUP_BY);

		return template.query(SQL, params, new BeanPropertyRowMapper<>(StudentListVO.class));
	}

	// Find By Email
	public Integer findByEmail(String email) {
		final String FIND_BY_EMAIL_QUERY = "SELECT s.id FROM student s JOIN account a ON s.id = a.id WHERE a.email LIKE :email";
		var params = new HashMap<String, Object>();
		params.put("email", email.toLowerCase().concat("%"));
		return template.queryForList(FIND_BY_EMAIL_QUERY, params,Integer.class).stream().findFirst().orElse(null);
		
	}

	// Create Student Process
	@Transactional(propagation = Propagation.REQUIRED)
	public int createStudent(RegistrationForm form) {

		//For Account
		var accountParam = new HashMap<String, Object>();

		accountParam.put("name", form.getStudentName());
		accountParam.put("role", "Student");
		accountParam.put("email", form.getEmail());
		accountParam.put("password", encoder.encode(form.getPhone()));

		var generatedId = accountInsert.executeAndReturnKey(accountParam);

		//For Student 
		studentInsert.execute(Map.of("id", generatedId.intValue(), "phone", form.getPhone(), "education", form.getEducation()));
				
		return generatedId.intValue();

	}

	// Find By Id
	public StudentListVO findById(int id) {
		final String FIND_BY_ID_QUERY = "%s AND s.id = :id %s".formatted(SELECT_PROJECTION, SELECT_GROUP_BY);
		var param = new HashMap<String, Object>();
		param.put("id", id);
		return template.queryForObject(FIND_BY_ID_QUERY, param, new BeanPropertyRowMapper<>(StudentListVO.class));
	}

	public StudentDetailVO findDetailByLoginId(String email) {
		var detail = new StudentDetailVO();
		
		//Find student Id 
		var studentId = findByEmail(email);
		
		//Set Student
		detail.setStudent(findById(studentId));
		//Set Registration
		detail.setRegistration(registrationService.searchByStudentId(studentId));
		
		return detail;
	}
	
	

}