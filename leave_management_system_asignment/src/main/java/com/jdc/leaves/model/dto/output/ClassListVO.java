package com.jdc.leaves.model.dto.output;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassListVO {

    public ClassListVO() {
    }

   
	private int id;

    private int teacherId;

    private String teacherName;

    private String teacherPhone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    private int months;

    private String description;

    private long studentCount;

	public ClassListVO(int id, int teacherId, String teacherName, String teacherPhone, LocalDate startDate, int months,
			String description, long studentCount) {
		super();
		this.id = id;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.teacherPhone = teacherPhone;
		this.startDate = startDate;
		this.months = months;
		this.description = description;
		this.studentCount = studentCount;
	}
    
    
    

}