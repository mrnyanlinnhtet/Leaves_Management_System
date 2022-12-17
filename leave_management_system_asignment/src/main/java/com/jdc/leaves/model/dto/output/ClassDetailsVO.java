package com.jdc.leaves.model.dto.output;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassDetailsVO {

    public ClassDetailsVO() {
    }

    private ClassListVO classInfo;

    private List<RegistrationListVO> registrations;

    private List<LeaveListVO> leaves;

}