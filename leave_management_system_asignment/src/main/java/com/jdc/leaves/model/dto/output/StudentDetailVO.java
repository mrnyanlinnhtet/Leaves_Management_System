package com.jdc.leaves.model.dto.output;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDetailVO {

	 private StudentListVO student;
	 private List<RegistrationListVO> registration;
}
