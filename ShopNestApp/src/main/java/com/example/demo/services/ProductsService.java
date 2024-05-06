package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Products;

public interface ProductsService {

	public String addProduct(Products product);
	public List<Products> viewProduct();
	public Products findBypId(String productId);
}
