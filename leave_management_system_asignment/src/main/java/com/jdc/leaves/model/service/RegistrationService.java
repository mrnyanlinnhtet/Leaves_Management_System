package com.jdc.leaves.model.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.leaves.model.dto.input.RegistrationForm;
import com.jdc.leaves.model.dto.output.RegistrationDetailsVO;
import com.jdc.leaves.model.dto.output.RegistrationListVO;

@Service
public class RegistrationService {
	
	private SimpleJdbcInsert insert;
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private ClassService classService;
	
	private static final String SELECT_BY_CLASS = """
			select r.classes_id classId, c.teacher_id teacherId, ta.name teacher, c.description classInfo, 
			c.start_date startDate, r.student_id studentId, sa.name student, s.phone studentPhone, r.registration_date registrationDate 
			from registration r 
			join classes c on r.classes_id = c.id 
			join teacher t on c.teacher_id = t.id join account ta on t.id = ta.id 
			join student s on r.student_id = s.id join account sa on s.id = sa.id 
			where r.classes_id = :classId
			""";
	
	private static final String SELECT_BY_STUDENT = """
			select r.classes_id classId, c.teacher_id teacherId, ta.name teacher, c.description classInfo, 
			c.start_date startDate, r.student_id studentId, sa.name student, s.phone studentPhone, r.registration_date registrationDate 
			from registration r 
			join classes c on r.classes_id = c.id 
			join teacher t on c.teacher_id = t.id join account ta on t.id = ta.id 
			join student s on r.student_id = s.id join account sa on s.id = sa.id 
			where r.student_id = :studentId
			""";
	
	

	public RegistrationService(DataSource dataSource) {
		
		 template = new NamedParameterJdbcTemplate(dataSource);
		
		 insert  = new SimpleJdbcInsert(dataSource);
		 insert.setTableName("registration");
		 insert.setColumnNames(List.of("classes_id","student_id","registration_date"));
	}

	//save process
	@Transactional
	public int save(RegistrationForm form) {
		if (form.getStudentId() == 0) {
			return insertForm(form);
		}
		return updateForm(form);
	}

	
	//Find Detail By id
	public RegistrationDetailsVO findDetailsById(int classId, int studentId) {
		final String REG_DATE_SELECT  = """
			SELECT registration_date FROM registration 
			WHERE student_id = :studentId
			AND classes_id = :classId
				""";
		var params = new HashMap<String,Object>();
		params.put("studentId", studentId);
		params.put("classId", classId);
		
		   var detail = new RegistrationDetailsVO();
		   
		   //Set Student
		   var stu = studentService.findById(studentId);
		   detail.setStudent(stu);
		   
		   //Set Class
		   var info = classService.findInfoById(classId);
		   detail.setClassInfo(info);
		   
		   //Set registration Date
		 var regDate = template.queryForObject(REG_DATE_SELECT,params,Date.class);
		 detail.setRegistDate(regDate.toLocalDate());
		   
		return detail;
	}

	//Get Form by id
	public RegistrationForm getFormById(int classId, int studentId) {
		var SELECT_PROJECTION = """
				SELECT r.classes_id classId, r.student_id studentId, r.registration_date registDate, 
				a.name studentName, a.email, s.phone, s.education
				FROM registration r JOIN student s ON r.student_id = s.id join account a on s.id = a.id 
				WHERE r.classes_id = :classId and r.student_id = :studentId
				""";
		var params = new HashMap<String,Object>();
		params.put("studentId",studentId);
		params.put("classId", classId);
		return template.queryForObject(SELECT_PROJECTION,params, new BeanPropertyRowMapper<>(RegistrationForm.class));
	}

	
	//Search by classId Process
	public List<RegistrationListVO> searchByClassId(int classId) {
		return template.query(SELECT_BY_CLASS, Map.of("classId", classId), new BeanPropertyRowMapper<>(RegistrationListVO.class));
	}
	
	//Search by studentId Process
	public List<RegistrationListVO> searchByStudentId(int studentId) {
		return template.query(SELECT_BY_STUDENT, Map.of("studentId", studentId), new BeanPropertyRowMapper<>(RegistrationListVO.class));
	}

	//Update Process
	private int updateForm(RegistrationForm form) {
	//Student Update Process
		final String UPDATE_STUDENT ="""
		UPDATE student SET phone =:phone,education =:education
		 WHERE id =:id
				""";
		var params = new HashMap<String,Object>();
		params.put("phone",form.getPhone());
		params.put("education", form.getEducation());
		params.put("id",form.getStudentId());
	    template.update(UPDATE_STUDENT,params);
	    
	    
	    //Registration Update Process
	    final String UPDATE_REG = """
	    UPDATE registration SET registration_date =:registration_date
	    WHERE student_id =student_id AND classes_id =:class_id		
	    		""";
	    var param = new HashMap<String,Object>();
		 param.put("registration_date",form.getRegistrationDate());
		 param.put("student_id", form.getStudentId());
		 param.put("class_id", form.getClassId());
		 
		 template.update(UPDATE_REG,param);
		 return form.getStudentId();
	}

	//Insert Process
	private int insertForm(RegistrationForm form) {
	
		var student_id = studentService.findByEmail(form.getEmail());
		//var classes = classService.findInfoById(form.getClassId());
		
		
		if(null == student_id) {
			student_id = studentService.createStudent(form);
		}
		
		//Set Student 
		form.setStudentId(student_id);
		
		//Set Class
		//form.setClassId(classes.getId());
		
		
		//Set Registration Date
		if(form.getRegistrationDate() == null) {
			form.setRegistrationDate(LocalDate.now());
		}
		
		var param = new HashMap<String,Object>();
		param.put("student_id", form.getStudentId());
		param.put("classes_id", form.getClassId());
		param.put("registration_date",Date.valueOf(form.getRegistrationDate()));
		return insert.execute(param);
		
	}
	
}

