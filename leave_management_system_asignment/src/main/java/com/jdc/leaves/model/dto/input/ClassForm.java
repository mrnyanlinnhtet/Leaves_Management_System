package com.jdc.leaves.model.dto.input;

import java.time.LocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassForm {

    public ClassForm() {
    }
     private int id;
    @Min(value = 1,message = "Please select the teacher !")
    private int teacher;

    @NotNull(message = "Please enter the start date !")
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate start;

    @Min(value = 1,message = "Please enter duration !")
    private int months;

    @NotEmpty(message = "Please enter description !")
    private String description;
    
    
    
    

}