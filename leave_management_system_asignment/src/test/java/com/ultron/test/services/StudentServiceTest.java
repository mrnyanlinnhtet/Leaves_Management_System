package com.ultron.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.leaves.model.dto.input.RegistrationForm;
import com.jdc.leaves.model.service.StudentService;

@SpringJUnitConfig(locations = "/root-config.xml")
@TestMethodOrder(OrderAnnotation.class)
public class StudentServiceTest {
	
	@Autowired
	public StudentService studentService;
	
	@Order(1)
	@ParameterizedTest
	@Sql(scripts = {"/sql/truncate_db.sql","/sql/student.sql"})
	@CsvSource(value = {
			",,,3",
			"nyan,,,1",
			",09,,3",
			",,nyan,1",
			"hla,,,0"
			
	})
	void search_student_test(String name,String phone,String email,int count) {
		var result = studentService.search(Optional.ofNullable(name),Optional.ofNullable(phone), Optional.ofNullable(email));
		assertEquals(count,result.size());
	}
	
	@Order(2)
	@ParameterizedTest
	@Sql(scripts = {"/sql/truncate_db.sql","/sql/student.sql"})
	@CsvSource(value = "nyan@gmail.com")
	void find_by_email(String email) {
		var stu = studentService.findByEmail(email);
		assertEquals(1, stu);
	}
	
	@Order(3)
	@ParameterizedTest
	@Sql(scripts = {"/sql/truncate_db.sql","/sql/student.sql"})
	@ValueSource(ints = 1)
	void find_by_id(int id) {
		var stu = studentService.findById(id);
		assertEquals("Aye Myat Mon",stu.getName());
	}
	
	@Order(4)
	@ParameterizedTest
	@Sql(scripts = {"/sql/truncate_db.sql","/sql/student.sql"})
	@CsvSource(value = {"6,Tony Stark,tony@gmail.com,0922345668,MIT",
			             "7,Tony Ja,ja@gmail.com,09332345668,Oxford"})
	void create_student(int id,String name,String email,String phone,String education) {
		var form = new RegistrationForm(0, null, id, name, email, phone, education);
		var stu = studentService.createStudent(form);
		assertNotNull(stu);
	}

}
