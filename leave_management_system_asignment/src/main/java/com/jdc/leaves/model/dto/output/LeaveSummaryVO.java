package com.jdc.leaves.model.dto.output;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class LeaveSummaryVO {

    public LeaveSummaryVO() {
    }

    private int classId;

    private String teacher;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    private long students;

    private long leaves;
    
    private String details;

	public LeaveSummaryVO(ClassListVO vo) {
		super();
		this.classId = vo.getId();
		this.teacher = vo.getTeacherName();
		this.startDate = vo.getStartDate();
		this.students = vo.getStudentCount();
		this.details = vo.getDescription();
	}
    

}