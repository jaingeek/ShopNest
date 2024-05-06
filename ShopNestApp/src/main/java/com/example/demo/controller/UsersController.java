package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Products;
import com.example.demo.entity.User;
import com.example.demo.services.ProductsService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {

	@Autowired
	UsersService userv;
	@Autowired
	ProductsService pserv;
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user) {
		boolean userstatus = userv.validateUser(user.getEmail());
		if(userstatus ==true) {
			userv.addUser(user);
			return "index";
		}
		else return "registerfail";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam String email,@RequestParam String password,HttpSession session,Model model) {
		
		boolean loginstatus = userv.validateLogin(email, password);
		session.setAttribute("email", email);
		if(loginstatus == true) {
			
			if(userv.isAdmin(email)) return "adminhome";
			else {
				return "loginsuccess";
			}
		}
		else {
			return "loginfail";
		}
	}
	
	@GetMapping("/map-viewcustomer")
	public String viewCustomerInfoMapping(Model model,HttpSession session) {
		if(session!=null && session.getAttribute("email")!=null) {
			List<User> customerList = userv.getCustomerInfo();
			model.addAttribute("customerList", customerList);
			return "viewcustomer";
		}
		else return "loginsuccess";
	}
	
	@GetMapping("/homepage")
	public String showUserHomePage(Model model,HttpSession session,HttpServletRequest request) {
		if(session!=null && session.getAttribute("email")!=null) {
		    List<Products> productList = pserv.viewProduct();
		    model.addAttribute("productList", productList);

		    String name = userv.getNameByEmail((String)session.getAttribute("email"));
		    model.addAttribute("name", name);
		    return "home";
		} else return "index";
		
	}
	
	@PostMapping("/updatePassword")
	public String updatePassword(@RequestParam String email, @RequestParam String newPassword, @RequestParam String confirmPassword) {
		if(newPassword.equals(confirmPassword)) {
			userv.updatePassword(email,newPassword);
		}
		else {
			return "forgotPasswordfail";
		}
		return "index";
	}
}
