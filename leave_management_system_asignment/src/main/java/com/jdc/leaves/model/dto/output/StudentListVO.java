package com.jdc.leaves.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentListVO {

    public StudentListVO() {
    }

    private int id;

    private String name;

    private String phone;

    private String email;

    private String education;

    private long classCount;

	public StudentListVO(int id, String name, String phone, String email, String education, long classCount) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.education = education;
		this.classCount = classCount;
	}
    
    

}