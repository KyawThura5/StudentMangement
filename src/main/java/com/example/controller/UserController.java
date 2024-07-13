package com.example.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.dao.UserService;
import com.example.dto.User;
import com.example.model.LoginBean;
import com.example.model.UserBean;
import com.example.model.UsersearchBean;

@Controller
public class UserController {
@Autowired
private UserService userservice;
@ModelAttribute("UserBean")
public UserBean getUserBean() {
return new UserBean();
}
@ModelAttribute("loginBean")
public LoginBean getLoginBean() {
return new LoginBean();
}
@ModelAttribute("search")
public UsersearchBean search() {
return new UsersearchBean();
}

@RequestMapping(value="/", method=RequestMethod.GET)
public String displayView() {
return "LGN001";
}
@RequestMapping(value="menu",method=RequestMethod.GET)
public String menu() {
	return "MNU001";
}
@RequestMapping(value="login",method=RequestMethod.GET)
public String menu(@ModelAttribute("loginBean")@Validated LoginBean bean,ModelMap model,HttpSession session,Date date) {
	List<User>list=userservice.getAllUser();
	Iterator<User> itr=list.iterator();
	while(itr.hasNext()) {
		User user=itr.next();

		if(user.getId().equals(bean.getId()) && user.getPassword().equals(bean.getPassword())) {
			session.setAttribute("user", user);
			session.setAttribute("date", date=new Date());
			return "MNU001";		
		}
	}
	model.addAttribute("error","Check Your Data Again!!");
	return "LGN001";
	
}
@RequestMapping(value="createuser",method=RequestMethod.GET)
public ModelAndView createuser() {
	return new ModelAndView("LGN002","UserBean",new UserBean());
	
}
@RequestMapping(value="CreateUser",method=RequestMethod.POST)
public String Account(@ModelAttribute("UserBean") @Validated UserBean UserBean,
		BindingResult bs,ModelMap model) {
	if(bs.hasErrors()) {
		return "LGN002";
	}
	User dto=new User();
	dto.setId(UserBean.getId());
	dto.setUsername(UserBean.getUsername());
	dto.setUseremail(UserBean.getUseremail());
	dto.setPassword(UserBean.getPassword());
	if(!dto.getPassword().equals(UserBean.getConfirm_password())) {
		model.addAttribute("error","Please Check Your Password Again!");
		return "LGN002";
	}
	dto.setRole(UserBean.getRole());
	var st=userservice.check(UserBean.getId());
	if(st.isPresent()) {
		model.addAttribute("error","User Already Exit!");
		return "LGN002";
	}
	userservice.save(dto);;
	return "redirect:/";
	
}
@RequestMapping(value="userall",method=RequestMethod.GET)
public String displayuserlist(Model model) {
	List<User>list=userservice.getAllUser();
	model.addAttribute("list",list);
	return "USR003";
	
}
@RequestMapping(value="/adduserview",method=RequestMethod.GET)
public ModelAndView insertUser() {
	return new ModelAndView("USR001","UserBean",new UserBean());
	
}
@RequestMapping(value="Adduser",method=RequestMethod.POST)
public String adduser(@ModelAttribute("UserBean") @Validated UserBean UserBean,
		BindingResult bs,ModelMap model) {
	if(bs.hasErrors()) {
		return "USR001";
	}
	User dto=new User();
	dto.setId(UserBean.getId());
	dto.setUsername(UserBean.getUsername());
	dto.setUseremail(UserBean.getUseremail());
	dto.setPassword(UserBean.getPassword());
	if(!dto.getPassword().equals(UserBean.getConfirm_password())) {
		model.addAttribute("error","Please Check Your Password Again!");
		return "USR001";
	}
	dto.setRole(UserBean.getRole());
	var st=userservice.check(UserBean.getId());
	if(st.isPresent()) {
		model.addAttribute("error","Insert Failed!");
		return "USR001";
	}
	userservice.save(dto);
	return "redirect:/userall";
	
}
@RequestMapping(value="/setupupdateuser",method=RequestMethod.GET)
public ModelAndView showupdate(@RequestParam("id") String id){
	return new ModelAndView("USR002","User",userservice.selectbyid(id));	
}
@RequestMapping(value="/Updateuser",method=RequestMethod.POST)
public String update(@ModelAttribute("User") @Validated UserBean UserBean,
		BindingResult bs,ModelMap model,HttpSession session) {
	if(bs.hasErrors()) {
		return "USR002";
	}
	User dto=new User();
	dto.setId(UserBean.getId());
	dto.setUsername(UserBean.getUsername());
	dto.setUseremail(UserBean.getUseremail());
	dto.setPassword(UserBean.getPassword());
	if(!dto.getPassword().equals(UserBean.getConfirm_password())) {
		model.addAttribute("error","Please Check Your Password Again!");
		return "USR002";
	}
	dto.setRole(UserBean.getRole());
	userservice.save(dto);
	 User ses=(User) session.getAttribute("user");
	if(ses.getId().equals(dto.getId())) {
		User bean=new User();
		bean.setId(dto.getId());
		bean.setUsername(dto.getUsername());
		session.setAttribute("user",bean);
	}
	return "redirect:/userall";
	
}
@RequestMapping(value="/setupdeleteuser",method=RequestMethod.GET)
public String deleteuser(@RequestParam ("id") String id,HttpSession session) {
	User user=(User) session.getAttribute("user");
	if(!user.getId().equals(id)) {
	userservice.delete(id);
	}
	return "redirect:/userall";
	
}
@RequestMapping(value="setupsearchuser",method=RequestMethod.POST)
public String search(@ModelAttribute("search") UsersearchBean search,ModelMap model) {
	String id=search.getId();
	String username=search.getUsername();
	List<User>list=userservice.searchByid(id,username);
	model.addAttribute("list",list);
	return "USR003";
	
}
}