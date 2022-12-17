package com.jdc.leaves.controller;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.leaves.model.dto.input.LeaveForm;
import com.jdc.leaves.model.service.ClassService;
import com.jdc.leaves.model.service.LeaveService;
import com.jdc.leaves.model.service.StudentService;

@Controller
@RequestMapping("/leaves")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public String index( @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Optional<LocalDate> from, 
			             @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam Optional<LocalDate> to, 
			             ModelMap model) {
		var leaveListVO = leaveService.search(Optional.empty(), from, to);
		model.put("list", leaveListVO);
		return "leaves";
	}

	@GetMapping("edit")
	public String edit(@RequestParam int classId, @RequestParam int studentId,ModelMap model) {
		//class info 
		var classInfo = classService.findDetailsById(classId);
		//student info
		var studentInfo = studentService.findById(studentId);
		
		model.put("classInfo", classInfo);
		model.put("studentInfo", studentInfo);
		
		return "leaves-edit";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute("leavesForm") LeaveForm form, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {
			//class info 
			var classInfo = classService.findDetailsById(form.getClassId());
			//student info
			var studentInfo = studentService.findById(form.getStudentId());
			model.put("classInfo", classInfo);
			model.put("studentInfo", studentInfo);
			return "leaves-edit";
		}
		
		leaveService.save(form);
		
		return "redirect:/leaves";
	}

	@ModelAttribute("leavesForm")
	public LeaveForm form(@RequestParam(required = false) Integer classId,
			@RequestParam(required = false) Integer studentId) {

		if (classId != null && studentId != null) {
		  var leaveForm = new LeaveForm(classId, studentId);
		   leaveForm.setApplyDate(LocalDate.now());
		  return leaveForm;
		}

		return new LeaveForm();

	}

}