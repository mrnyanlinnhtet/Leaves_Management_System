package com.jdc.leaves.model.dto.output;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaveListVO {

	public LeaveListVO() {
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate applyDate;

	private String classInfo;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate classStartDate;
	private int classId;

	private int studentId;

	private String student;

	private String studentPhone;

	private int teacherId;

	private String teacher;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	private int days;

	private String reason;

}