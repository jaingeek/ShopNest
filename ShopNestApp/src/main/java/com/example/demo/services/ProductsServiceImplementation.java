package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Products;
import com.example.demo.repository.ProductsRepository;

@Service
public class ProductsServiceImplementation implements ProductsService{

	@Autowired
	ProductsRepository prepo;
	
	@Override
	public String addProduct(Products product) {
		prepo.save(product);
		return "product added successfully";
	}

	@Override
	public List<Products> viewProduct() {
		return prepo.findAll();
		
	}

	@Override
	public Products findBypId(String productId) {
		
		return prepo.findBypid(productId);
	}

}
