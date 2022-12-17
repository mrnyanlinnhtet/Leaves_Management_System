package com.jdc.leaves.model.dto.input;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RegistrationForm {

	public RegistrationForm() {
	}
	
	

	public RegistrationForm(int classId, LocalDate registrationDate, int studentId, String studentName, String email,
			String phone, String education) {
		super();
		this.classId = classId;
		this.registrationDate = registrationDate;
		this.studentId = studentId;
		this.studentName = studentName;
		this.email = email;
		this.phone = phone;
		this.education = education;
	}



	private int classId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate registrationDate;

	private int studentId;

	@NotEmpty(message = "Please enter student name !")
	private String studentName;

	@NotEmpty(message = "Please enter email !")
	private String email;

	@NotEmpty(message = "Please enter phone number !")
	private String phone;

	@NotEmpty(message = "Please enter student's last education !")
	private String education;

}