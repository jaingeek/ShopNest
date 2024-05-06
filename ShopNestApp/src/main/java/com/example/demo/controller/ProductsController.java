package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Products;
import com.example.demo.services.ProductsService;

@Controller
public class ProductsController {

	@Autowired
	ProductsService pserv;
	
	@PostMapping("/addproduct")
	public String addProduct(@ModelAttribute Products product) {
		pserv.addProduct(product);
		return "addproductsuccess";
	}
	
	@GetMapping("/map-viewproduct")
	public String viewProductMapping(Model model) {
		List<Products> productList = pserv.viewProduct();
		model.addAttribute("productList", productList);
		return "viewproducts";
	}
}
