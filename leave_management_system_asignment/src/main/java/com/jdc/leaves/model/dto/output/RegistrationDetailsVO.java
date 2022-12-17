package com.jdc.leaves.model.dto.output;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDetailsVO {

    public RegistrationDetailsVO() {
    }

    private LocalDate registDate;

    private ClassListVO classInfo;

    private StudentListVO student;

}