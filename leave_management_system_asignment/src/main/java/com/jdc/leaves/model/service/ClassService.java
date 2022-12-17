package com.jdc.leaves.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.leaves.model.dto.input.ClassForm;
import com.jdc.leaves.model.dto.output.ClassDetailsVO;
import com.jdc.leaves.model.dto.output.ClassListVO;
import com.jdc.leaves.model.service.mapper.ClassFormRowMapper;
import com.jdc.leaves.model.service.mapper.ClassListVORowMapper;

@Service
public class ClassService {

	private static final String SELECT_PROJECTION = """
			SELECT c.id id, t.id teacherId,a.name teacherName,t.phone teacherPhone,c.start_date startDate,c.months months,
			c.description description,COUNT(r.student_id) studentCount FROM
			 classes c JOIN teacher t ON t.id = c.teacher_id
			 JOIN account a ON a.id = t.id
			 LEFT JOIN registration r ON c.id = r.classes_id WHERE 1 = 1  """;

	private static final String SELECT_GROUP_BY = " GROUP BY c.id, t.id,a.name,t.phone,c.start_date,c.months,c.description";

	private NamedParameterJdbcTemplate template;
	private SimpleJdbcInsert insert;
	private ClassListVORowMapper mapper;
	private ClassFormRowMapper formMapper;

	@Autowired
	private RegistrationService regService;
	@Autowired
	private LeaveService leaveService;

	public ClassService(DataSource dataSource) {
		template = new NamedParameterJdbcTemplate(dataSource);
		insert = new SimpleJdbcInsert(dataSource);
		mapper = new ClassListVORowMapper();
		formMapper = new ClassFormRowMapper();

		insert.setTableName("classes");
		insert.setGeneratedKeyName("id");
		insert.setColumnNames(List.of("teacher_id", "start_date", "months", "description"));
	}

	// Dynamic Search
	public List<ClassListVO> search(Optional<String> teacher, Optional<LocalDate> from, Optional<LocalDate> to) {

		var where = new StringBuffer();

		var params = new HashMap<String, Object>();

		// Search With Teacher Name
		where.append(teacher.filter(StringUtils::hasText).map(a -> {
			params.put("teacher", a.toLowerCase().concat("%"));
			return " AND LOWER(a.name) LIKE :teacher";
		}).orElse(""));

		// Search With Date From
		where.append(from.map(a -> {
			params.put("from", Date.valueOf(a));
			return " AND c.start_date >= :from";
		}).orElse(""));

		// Search With Date To
		where.append(to.map(a -> {
			params.put("to", Date.valueOf(a));
			return " AND c.start_date <= :to";
		}).orElse(""));

		final String SQL = "%s %s %s".formatted(SELECT_PROJECTION,where.toString(),SELECT_GROUP_BY);

		return template.query(SQL, params, mapper);
	}

	public ClassForm findById(int classId) {
		final String SELECT_QUERY = "SELECT * FROM classes WHERE id = :id";
		var param = new HashMap<String, Object>();
		param.put("id", classId);
		return template.queryForObject(SELECT_QUERY, param, formMapper);
	}

	public ClassListVO findInfoById(int classId) {

		final String SQL_CLASS_INFO = "%s AND c.id = :id %s".formatted(SELECT_PROJECTION, SELECT_GROUP_BY);
		var params = new HashMap<String, Object>();
		params.put("id", classId);
		return template.queryForObject(SQL_CLASS_INFO, params, mapper);

	}

	public ClassDetailsVO findDetailsById(int classId) {

		var classDetail = new ClassDetailsVO();

		// For Class Info
		var classListVO = findInfoById(classId);
		classDetail.setClassInfo(classListVO);

		// For Registration
		classDetail.setRegistrations(regService.searchByClassId(classId));

		// For Leaves
		classDetail.setLeaves(
				leaveService.search(Optional.of(classId),Optional.empty(), Optional.empty()));

		return classDetail;
	}

	// Save Process
	@Transactional
	public int save(ClassForm form) {
		if (form.getId() == 0) {
			return insert(form);
		}
		return update(form);
	}

	private int update(ClassForm form) {
		var params = new HashMap<String, Object>();
		params.put("teacher", form.getTeacher());
		params.put("start", Date.valueOf(form.getStart()));
		params.put("months", form.getMonths());
		params.put("description", form.getDescription());
		params.put("id", form.getId());
		final String UPDATE_QUERY = """
				UPDATE classes SET teacher_id =:teacher,start_date =:start,months=:months,description=:description
				WHERE id =:id;
				""";
		template.update(UPDATE_QUERY, params);
		return form.getId();
	}

	private int insert(ClassForm form) {
		var params = new HashMap<String, Object>();
		params.put("teacher_id", form.getTeacher());
		params.put("start_date", Date.valueOf(form.getStart()));
		params.put("months", form.getMonths());
		params.put("description", form.getDescription());
		var getGeneratedId = insert.executeAndReturnKey(params).intValue();
		return getGeneratedId;
	}

}