package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.CourseService;
import com.example.dao.StudentService;
import com.example.dto.Course;
import com.example.dto.Student;
import com.example.model.StudentBean;

@Controller
public class StudentController {
@Autowired
CourseService courseservice;
@Autowired
StudentService stuservice;
@RequestMapping(value="showstudentform",method=RequestMethod.GET)
public ModelAndView showstuform() {	
	StudentBean bean = new StudentBean();
	bean.setPhone("+95 ");
	return new ModelAndView("STU001","bean",bean);
	
}
@ModelAttribute("courses")
public List<Course> courses(){
	List<Course>list=courseservice.selectAll();
	return list;
	
}
@RequestMapping(value="Addstudent",method=RequestMethod.POST)
public String Addstudent(@ModelAttribute("bean") @Validated StudentBean bean,BindingResult bs,ModelMap model) {
	if(bs.hasErrors()) {
		return "STU001";
	}
	Student dto=new Student();
	dto.setStudentid(bean.getStudentid());
	dto.setStudentname(bean.getStudentname());
	dto.setDate(bean.getDate());
	dto.setGender(bean.getGender());
	dto.setPhone(bean.getPhone());
	dto.setEducation(bean.getEducation());
	dto.setCourse(bean.getCourse());
	var st=stuservice.check(bean.getStudentid());
	if(st.isPresent()) {
		model.addAttribute("error","Insert Failed!!");
		return "STU001";
	}
	stuservice.save(dto);
	return "redirect:/showstudentform";
	
} 
@RequestMapping(value="/showAll",method=RequestMethod.GET)
public String showstudent(ModelMap model) {
	List<Student>list=stuservice.selectAll();
	model.addAttribute("list",list);
	return "STU003";
}

@RequestMapping(value="setupshowbyid",method=RequestMethod.GET)
public ModelAndView showbyid(@RequestParam("id")String id) {
	return new ModelAndView("STU002","bean",stuservice.selectbyid(id));
	
}
@RequestMapping(value="updateStudent",method=RequestMethod.POST)
public String updateStudent(@ModelAttribute("bean") @Validated StudentBean bean,BindingResult bs,ModelMap model) {
	if(bs.hasErrors()) {
		return "STU002";
	}
	Student dto=new Student();
	dto.setStudentid(bean.getStudentid());
	dto.setStudentname(bean.getStudentname());
	dto.setDate(bean.getDate());
	dto.setGender(bean.getGender());
	dto.setPhone(bean.getPhone());
	dto.setEducation(bean.getEducation());
	dto.setCourse(bean.getCourse());
	stuservice.save(dto);
	return "redirect:/showAll";
	
} 
@RequestMapping(value="deletestudent",method=RequestMethod.GET)
public String delete(@RequestParam("id")String id) {
	stuservice.delete(id);
	return "redirect:/showAll";
	
}
@RequestMapping(value="SearchStudent",method=RequestMethod.POST)
public String search(ModelMap model,@RequestParam("studentid")String studentid,@RequestParam("studentname")String studentname,@RequestParam("course_name")String course_name){
	List<Student> list=stuservice.search(studentid,studentname,course_name);
	model.addAttribute("list",list);
	return "STU003";
	
}
}
