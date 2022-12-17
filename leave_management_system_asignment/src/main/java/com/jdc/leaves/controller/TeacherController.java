package com.jdc.leaves.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.leaves.model.dto.input.TeacherForm;
import com.jdc.leaves.model.dto.output.TeacherListVO;
import com.jdc.leaves.model.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@GetMapping
	public String index(@RequestParam Optional<String> name, @RequestParam Optional<String> phone,
			@RequestParam Optional<String> email, ModelMap model) {
		var result = teacherService.search(name, phone, email);
		model.put("list", result);
		return "teachers";
	}

	@GetMapping("edit")
	public String edit(@RequestParam(required = false) Optional<Integer> id) {
		return "teachers-edit";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute("form") TeacherForm form,BindingResult result) {
		if(result.hasErrors()) {
			return "teachers-edit";
		}
		teacherService.save(form);
		return "redirect:/teachers";
	}

	@ModelAttribute("form")
	public TeacherForm teacherFormBinding(@RequestParam(required = false) Optional<Integer> id) {
		return id.filter(a->a>0).map(teacherService::findById).map(TeacherListVO::teacherForm).orElse(new TeacherForm());
	}

}