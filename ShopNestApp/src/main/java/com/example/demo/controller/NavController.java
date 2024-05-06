package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavController {

	@GetMapping("/map-register")
	public String registerMapping() {
		return "registration";
	}
	
	@GetMapping("/map-login")
	public String loginMapping() {
		return "index";
	}
	
	@GetMapping("/map-logout")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("email");
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/map-addproduct")
	public String productMapping() {
		return "addproducts";
	}
	
	@GetMapping("/map-forgotPassword")
	public String forgotPassword() {
		return "forgotPassword";
	}
}
