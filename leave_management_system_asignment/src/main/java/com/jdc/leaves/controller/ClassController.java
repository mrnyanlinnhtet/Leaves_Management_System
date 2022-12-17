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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.leaves.model.dto.input.ClassForm;
import com.jdc.leaves.model.dto.input.RegistrationForm;
import com.jdc.leaves.model.service.ClassService;
import com.jdc.leaves.model.service.RegistrationService;
import com.jdc.leaves.model.service.TeacherService;

@Controller
@RequestMapping("/classes")
public class ClassController {

	@Autowired
	private ClassService classService;
	@Autowired 
	private RegistrationService regService;
	 

	@Autowired
	private TeacherService teacherService;

	@GetMapping
	public String index(@RequestParam Optional<String> teacher,
		 @DateTimeFormat(pattern = "yyyy-MM-dd")@RequestParam Optional<LocalDate> from,
		 @DateTimeFormat(pattern = "yyyy-MM-dd")@RequestParam Optional<LocalDate> to, ModelMap model) {
		var result = classService.search(teacher,from,to);
		model.put("list", result);
		return "classes";
	}

	@GetMapping("edit")
	public String edit(@RequestParam Optional<Integer> id, ModelMap model) {
		model.put("teachers", teacherService.getAvailableTeachers());
		return "classes-edit";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute("formSave") ClassForm form,BindingResult result,ModelMap map) {
		
		if(result.hasErrors()) {
			map.put("teachers", teacherService.getAvailableTeachers());
			return "classes-edit";
		}
		
		var id = classService.save(form);
		return "redirect:/classes/%d".formatted(id);
		
	}

	@GetMapping("{id}")
	public String showDetails(@PathVariable int id, ModelMap model) {
		var result = classService.findDetailsById(id);
		model.put("dto", result);
		return "classes-details";
	}

	@GetMapping("registration")
	public String editRegistration(@RequestParam(required = false,defaultValue = "0") int studentId,
			                       @RequestParam(required = false,defaultValue="0") int classId,
			                       ModelMap map){
		 var result = classService.findInfoById(classId);
		 map.put("classInfo",result);
		return "registration-edit";
	}

	@PostMapping("registration")
	public String saveRegistration(@Valid @ModelAttribute("regiForm")RegistrationForm form,BindingResult result,ModelMap map) {
		
		if(result.hasErrors()) {
			var data = classService.findInfoById(form.getClassId());
			map.put("classInfo",data);
			return "registration-edit";
		}
		regService.save(form);
		return "redirect:/classes/registration/%d/%d".formatted(form.getClassId(),form.getStudentId());
	}

	@GetMapping("registration/{classId}/{studentId}")
	public String showRegistrationDetails(@PathVariable int classId, @PathVariable int studentId, ModelMap model) {
		var result = regService.findDetailsById(classId, studentId);
    	model.put("dto", result);
		return "registration-details";
	}

	@ModelAttribute("formSave")
	ClassForm modelClassForm(@RequestParam(required = false) Optional<Integer> id) {
		return id.filter(a -> a > 0).map(classService::findById).orElse(new ClassForm());
	}
	
	
	@ModelAttribute("regiForm")
	 RegistrationForm modelRegistrationForm(@RequestParam(required = false,defaultValue = "0")int studentId,
		                                 @RequestParam(required = false,defaultValue = "0")int classId) {
		 //Edit Process
		 if(studentId > 0) {
			return  regService.getFormById(classId,studentId);
		 }
		 //save
		 var form = new RegistrationForm();
		 form.setClassId(classId);
		 return form;
	 }

}