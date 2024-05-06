package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Products;
import com.example.demo.entity.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductsRepository;

@Service
public class CartServiceImplementation implements CartService{

	@Autowired
	CartRepository cartrepo;
	@Autowired
	ProductsRepository prepo;
	
	@Override
	public String addToCart(String productId,User user) {
		Products product = prepo.findBypid(productId);
		Cart cart = new Cart();
		cart.setpId(product.getPid());
		cart.setpName(product.getpName());
		cart.setpPrice(product.getpPrice());
		cart.getUser().add(user);
		cartrepo.save(cart);
		return "succesfull";
	}

	@Override
	public List<Cart> getProducts(int user_id) {
		return cartrepo.findDistinctByUserId(user_id);
	}

	@Override
	public double getTotalPrice(int user_id) {
		double totalPrice = 0.0;
		List<Cart> cartList = cartrepo.findByUserId(user_id);
		for(Cart cart:cartList) {
			totalPrice= totalPrice+Double.parseDouble(cart.getpPrice());
		}
		return totalPrice;
	}

	@Override
	public List<Cart> findByProductId(String productId) {
	    return cartrepo.findBypId(productId);
	}

}
