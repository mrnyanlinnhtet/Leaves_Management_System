package com.jdc.leaves.model.dto.input;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherForm {

    public TeacherForm() {
    }
    

    private int id;

    @NotEmpty(message = "Please enter name !")
    private String name;

    @NotEmpty(message = "Please enter  phone number !")
    private String phone;

    @NotEmpty(message = "Please enter email !")
    private String email;

    @NotNull(message = "Please enter assign date !")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate assignDate;

	public TeacherForm(int id, String name, String phone, String email, LocalDate assignDate) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.assignDate = assignDate;
	}
    
    

}