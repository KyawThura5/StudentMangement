package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.CourseService;
import com.example.dto.Course;
import com.example.model.CourseBean;
@Controller
public class CourseController {
@Autowired
private CourseService courseservice;
@ModelAttribute("courseBean")
public CourseBean getCourseBean() {
	return new CourseBean();
}
	@RequestMapping(value="/showcourse",method=RequestMethod.GET)
	public ModelAndView showcourse() {
		return new ModelAndView("BUD003","courseBean",new CourseBean());
	}
	
	@RequestMapping(value="/Addcourse",method=RequestMethod.POST)
	public String addcourse(@ModelAttribute("courseBean") @Validated CourseBean bean,BindingResult bs,ModelMap model) {
		if(bs.hasErrors()) {
			return "BUD003";
		}
		Course course=new Course();
		course.setId(bean.getId());
		course.setCourse_name(bean.getCourse_name());
		var c=courseservice.check(bean.getId());
		if(c.isPresent()) {
			model.addAttribute("error","Insert Failed!");
			return "BUD003";
		}
		courseservice.save(course);
		return "redirect:/showcourse";
	}
}
