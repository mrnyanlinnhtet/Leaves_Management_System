package com.ultron.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.leaves.model.dto.input.RegistrationForm;
import com.jdc.leaves.model.dto.output.ClassListVO;
import com.jdc.leaves.model.dto.output.StudentListVO;
import com.jdc.leaves.model.service.RegistrationService;

@SpringJUnitConfig(locations = "/root-config.xml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationServiceTest {

	@Autowired
	private RegistrationService service;

	@Order(1)
	@ParameterizedTest
	@Sql(scripts = { "/sql/truncate_db.sql", "/sql/registration.sql" })
	@CsvSource(value = "1,2022-07-10,0,Tony Stark,tony@gmail.com,0934213345,PHD")
	void save_form_success(int classId, LocalDate registrationDate, int studentId, String studentName, String email,
			String phone, String education) {
		var form = new RegistrationForm(classId, registrationDate, studentId, studentName, email, phone, education);
		var result = service.save(form);

		assertNotNull(result);
	}

	@Order(2)
	@ParameterizedTest
	@CsvSource(value = "1,2022-07-10,7,Tony Stark,tony@gmail.com,0934213345,PHD Of Nuclear Engineering")
	void update_form_success(int classId, LocalDate registrationDate, int studentId, String studentName, String email,
			String phone, String education) {
		var form = new RegistrationForm(classId, registrationDate, studentId, studentName, email, phone, education);
		var result = service.save(form);

		assertEquals(7, result);
	}

	@Order(3)
	@ParameterizedTest
	@Sql(scripts = { "/sql/truncate_db.sql", "/sql/registration.sql" })
	@CsvSource(value = "1,4,2022-07-01,1,Min Lwin,09782929282,2022-07-10,3,JavaEE,3,Nyan Linn Htet,09988877779,nyan@gmail.com,Computer Science Major,1")
	void find_detail_by_id_success(int classId, int studentId, LocalDate registDate, int teacherId, String teacherName,
			String teacherPhone, LocalDate startDate, int moths, String description, long studentCount, String name,
			String phone, String email, String education, long classCount) {

		var result = service.findDetailsById(classId, studentId);
		var classInfo = new ClassListVO(classId, teacherId, teacherName, teacherPhone, startDate, moths, description,
				studentCount);
		var student = new StudentListVO(studentId, name, phone, email, education, classCount);

		assertNotNull(result);
		assertEquals(classInfo.getTeacherName(), result.getClassInfo().getTeacherName());
		assertEquals(student.getName(), result.getStudent().getName());
		assertEquals(registDate, result.getRegistDate());

	}

	@Order(4)
	@ParameterizedTest
	@Sql(scripts = { "/sql/truncate_db.sql", "/sql/registration.sql" })
	@CsvSource(value = "1,2022-07-01,4,Nyan Linn Htet,nyan@gmail.com,09988877779,Computer Science Major")
	void find_form_by_id_success(int classId, LocalDate registrationDate, int studentId, String studentName,
			String email, String phone, String education) {

		var form = service.getFormById(classId, studentId);

		var regForm = new RegistrationForm(classId, registrationDate, studentId, studentName, email, phone, education);

		assertNotNull(form);
		assertEquals(regForm.getStudentId(), form.getStudentId());
		assertEquals(regForm.getStudentName(), form.getStudentName());
		assertEquals(regForm.getEmail(), form.getEmail());
		assertEquals(regForm.getPhone(), form.getPhone());

	}

	@Order(5)
	@ParameterizedTest
	@Sql(scripts = { "/sql/truncate_db.sql", "/sql/registration.sql" })
	@CsvSource(value = { "4,1", "5,2", "6,2" })
	void search_by_student_id_success(int studentId, int size) {

		var result = service.searchByStudentId(studentId);
		assertNotNull(result);
		assertEquals(result.size(), size);
	}
	
	
	@Order(6)
	@ParameterizedTest
	@Sql(scripts = { "/sql/truncate_db.sql", "/sql/registration.sql" })
	@CsvSource(value = { "1,3",
			             "2,2"})
	void search_by_class_id_success(int classId,int size) {
		
		var result = service.searchByClassId(classId);
		assertNotNull(result);
		assertEquals(result.size(),size);
	}
}
