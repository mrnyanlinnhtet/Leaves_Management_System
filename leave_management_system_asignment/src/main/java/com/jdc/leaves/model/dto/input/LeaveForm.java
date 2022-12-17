package com.jdc.leaves.model.dto.input;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaveForm {

    public LeaveForm() {
    }

    private int classId;

    private int studentId;

    @NotNull(message = "Please enter apply date !")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate applyDate;

    @NotNull(message = "Please enter start date !")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Min(value = 2,message = "Please enter leave day !")
    private int days;

    @NotEmpty(message = "Please enter reason for leave !")
    private String reason;

	public LeaveForm(int classId, int studentId, LocalDate applyDate, LocalDate startDate, int days, String reason) {
		super();
		this.classId = classId;
		this.studentId = studentId;
		this.applyDate = applyDate;
		this.startDate = startDate;
		this.days = days;
		this.reason = reason;
	}
	
	public LeaveForm(int classId, int studentId) {
		super();
		this.classId = classId;
		this.studentId = studentId;
	}
	
	
	
	public List<Map<String, Object>> leavesDaysInsert() {
		return IntStream.iterate(0, count -> count + 1).limit(days)
			.mapToObj(day -> startDate.plusDays(day))
			.map(this::daysInsertParam)
			.toList();
	}
	
	private Map<String, Object> daysInsertParam(LocalDate leaveDate) {
		return Map.of(
				"leaves_apply_date", Date.valueOf(applyDate),
				"leaves_classes_id", classId,
				"leaves_student_id", studentId,
				"leave_date", leaveDate
		);
	}



	


	
    
    

}