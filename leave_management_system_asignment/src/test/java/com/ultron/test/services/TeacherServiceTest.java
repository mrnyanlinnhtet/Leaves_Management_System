package com.ultron.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.leaves.model.dto.input.TeacherForm;
import com.jdc.leaves.model.service.TeacherService;

@SpringJUnitConfig(locations = "/root-config.xml")
@TestMethodOrder(OrderAnnotation.class)
public class TeacherServiceTest {

	@Autowired
	private TeacherService service;

	@Order(1)
	@ParameterizedTest
	@Sql(scripts = "/sql/truncate_db.sql")
	@CsvSource(value = "1,Min Lwin,09223453,lwinmin@gmail.com,2022-09-10")
	void save_insert_success(int id, String name, String phone, String email, LocalDate assignDate) {
		var form = new TeacherForm(id, name, phone, email, assignDate);
		var result = service.save(form);
		assertEquals(1, result);
	}

	@Order(2)
	@ParameterizedTest
	@Sql(scripts = { "/sql/truncate_db.sql", "/sql/teacher.sql" })
	@CsvSource(value = { "1,Min Lwin,0911111,lwinmin@gmail.com,2022-09-09,2",
			"2,Waifer Kolar,0922222,waifer@gmail.com,2022-10-10,1",
			"3,Khant Zaw,0933333,khantzaw@gmail.com,2022-12-01,0" })
	void find_by_id_test(int id, String name, String phone, String email, LocalDate assignDate, int classCount) {
		var obj = service.findById(id);

		assertEquals(id, obj.getId());
		assertEquals(name, obj.getName());
		assertEquals(phone, obj.getPhone());
		assertEquals(email, obj.getEmail());
		assertEquals(assignDate, obj.getAssignDate());
		assertEquals(classCount, obj.getClassCount());
	}

	@Order(1)
	@ParameterizedTest
	@Sql(scripts = { "/sql/truncate_db.sql", "/sql/teacher.sql" })
	@CsvSource(value = {
          ",,,3",
          "Min,,,1",
          "Win,,,0",
          ",091,,1",
          ",11,,0",
          "Min,091,,1",
          ",,lwin,1",
          ",,win,0",
          "Min,091,lwin,1"
	})
	void search_teacher_test(String name, String phone, String email, int count) {
		var result = service.search(Optional.ofNullable(name), Optional.ofNullable(phone), Optional.ofNullable(email));
		assertEquals(count, result.size());
	}

}
