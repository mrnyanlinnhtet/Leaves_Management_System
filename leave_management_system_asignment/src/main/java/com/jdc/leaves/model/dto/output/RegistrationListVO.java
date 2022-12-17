package com.jdc.leaves.model.dto.output;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationListVO {

    public RegistrationListVO() {
    }

    private int id;

    private int classId;

    private int teacherId;

    private String teacher;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    private int studentId;

    private String student;

    private String studentPhone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;
    
    private String classInfo;

	public RegistrationListVO(int classId, int teacherId, String teacher, LocalDate startDate, int studentId,
			String student, String studentPhone, LocalDate registrationDate, String classInfo) {
		super();
		this.classId = classId;
		this.teacherId = teacherId;
		this.teacher = teacher;
		this.startDate = startDate;
		this.studentId = studentId;
		this.student = student;
		this.studentPhone = studentPhone;
		this.registrationDate = registrationDate;
		this.classInfo = classInfo;
	}
    
    
    
    

}