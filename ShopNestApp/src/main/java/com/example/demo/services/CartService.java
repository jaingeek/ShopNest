package com.example.demo.services;

import java.util.List;

import com.example.demo.entity.Cart;
import com.example.demo.entity.User;

public interface CartService {
	
	public String addToCart(String productId,User user);

	public List<Cart> findByProductId(String productId);

	List<Cart> getProducts(int user_id);

	public double getTotalPrice(int id);
}
