package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Cart;
import com.example.demo.entity.User;
import com.example.demo.services.CartService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	
	@Autowired
	CartService cartserv;
	@Autowired
	UsersService userv;
	
	@PostMapping("/map-addtocart")
	public String productMapping(@RequestParam String productId,HttpSession session) {
		String email = (String)session.getAttribute("email");
		User user = userv.findByEmail(email);
		
		cartserv.addToCart(productId,user);
		
		List<Cart> cart = cartserv.findByProductId(productId);
		user.setCart(cart);
		
		return "cartsuccess";
	}
	
	@GetMapping("/map-cart")
	public String viewCart(Model model,HttpSession session) {
		String email = (String)session.getAttribute("email");
		User user = userv.findByEmail(email);
		int id = user.getId();
		List<Cart> listProducts = cartserv.getProducts(id);
		Set<Cart> uniqueProductsSet = new HashSet<>(listProducts);
		    
		    // Creating a new List with unique elements
		List<Cart> uniqueProductsList = new ArrayList<>(uniqueProductsSet);

		model.addAttribute("listProducts", uniqueProductsList);
		double totalPrice = cartserv.getTotalPrice(id);
		model.addAttribute("totalPrice", totalPrice);
		return "cart";
	}
}
