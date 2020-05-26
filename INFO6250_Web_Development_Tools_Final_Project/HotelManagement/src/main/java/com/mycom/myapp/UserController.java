package com.mycom.myapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycom.dao.UserDao;
import com.mycom.pojo.User;
import com.mycom.validator.UserValidator;

@Controller
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserValidator userValidator;
	
	@RequestMapping(value = "/user.htm", method = RequestMethod.GET)
	protected String handleRequest(HttpServletRequest request){
		// TODO Auto-generated method stub
		User user = (User) userDao.getUser(1L);
		
		request.setAttribute("user", user);
		return "user-view";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String toRegisterView(Model model)
	{
		model.addAttribute("user", new User());
		return "register";
	}
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,BindingResult result,UserDao userDao, HttpServletRequest request,Model model) throws IllegalStateException{
		
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "register";
		}
		try {
			User u = userDao.registerUser(user);
			if(user!=null) {
				return "redirect:/login";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String toLoginView() {
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String loginUser(UserDao userDao, HttpServletRequest request) throws IllegalStateException{
		String userEmail = request.getParameter("userEmail");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		
		try {
			User user = userDao.loginUser(userEmail, password, type);
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("curUser", user);
				//return "user-view";
				System.out.println("Inside login");
				return "redirect:/hotels";
			}else {
				System.out.println("not logged");
				request.setAttribute("getAlert", "yes");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutUser(HttpServletRequest request) throws IllegalStateException{
		HttpSession session = request.getSession(false);
		session.removeAttribute("curUser");
		return "redirect:/hotels";
		
	}
}
