package com.ultron.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.leaves.model.dto.input.LeaveForm;
import com.jdc.leaves.model.dto.output.LeaveSummaryVO;
import com.jdc.leaves.model.service.LeaveService;

@SpringJUnitConfig(locations = "/root-config.xml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LeavesServiceTest {

	@Autowired
	private LeaveService service;

//	@Order(1)
//	@ParameterizedTest
//	@Sql(scripts = {"/sql/truncate_db.sql","/sql/leaves.sql"})
//	@CsvSource(value = {
//			",,,,2",
//			"1,,,,1",
//			"2,,,,1",
//			",Nyan Linn Htet,,,1",
//			",Aye Myat Mon,,,1",
//			",,2022-11-23,2022-11-26,2",
//		
//	})
//	void search_success(Integer classId,String studentName,LocalDate from ,LocalDate to,int size) {
//		
//		var result  = service.search(Optional.ofNullable(classId), Optional.ofNullable(studentName),
//				Optional.ofNullable(from), Optional.ofNullable(to));
//		
//	   assertNotNull(result);
//	   assertEquals(result.size(),size);
//		
//	}

	@Order(2)
	@ParameterizedTest
	@Sql(scripts = { "/sql/truncate_db.sql", "/sql/leaves.sql" })
	@CsvSource(value = { "2,6,2022-11-23,2022-11-24,7,For Master Exam" })
	void save_success(int classId, int studentId, LocalDate applyDate, LocalDate startDate, int days, String reason) {

		var form = new LeaveForm(classId, studentId, applyDate, startDate, days, reason);

		var result = service.save(form);

		assertNotNull(result);
		assertEquals(1, result);

	}
	
	
	@Order(3)
	@ParameterizedTest
	@Sql(scripts = { "/sql/truncate_db.sql", "/sql/leaves.sql" })
	@CsvSource(value = "2,2022-11-24,1")
	void find_leave_for_class_success(int classId,LocalDate date,int size) {
		
		var result = service.findLeaveForClass(classId, date);
		
		assertNotNull(result);
		assertEquals(result,size);
		
	}
	
	@Order(4)
	@ParameterizedTest
	@Sql(scripts = { "/sql/truncate_db.sql", "/sql/leaves.sql" })
	@CsvSource(value = "2022-11-24,3")
	void search_summary_success(LocalDate targetDate,int size) {
		
		 List<LeaveSummaryVO> result = service.searchSummary(Optional.ofNullable(targetDate));
		   assertNotNull(result);
		   assertEquals(result.size(),size);
		   
			}

}
