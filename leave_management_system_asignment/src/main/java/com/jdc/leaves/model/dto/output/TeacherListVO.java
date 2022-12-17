package com.jdc.leaves.model.dto.output;

import java.time.LocalDate;

import com.jdc.leaves.model.dto.input.TeacherForm;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TeacherListVO {

	public TeacherListVO() {
	}

	private int id;

	private String name;

	private String phone;

	private String email;

	private LocalDate assignDate;

	private int classCount;
	
	

	public TeacherForm teacherForm() {
		return new TeacherForm(id, name, phone, email, assignDate);
	}



	public TeacherListVO(int id, String name, String phone, String email, LocalDate assignDate, int classCount) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.assignDate = assignDate;
		this.classCount = classCount;
	}

}