package com.example.demo.services;

import java.util.List;
import com.example.demo.entity.User;

public interface UsersService{

	public String addUser(User user);
	public boolean validateUser(String email);
	public boolean validateLogin(String email,String password);
	public boolean isAdmin(String email);
	public List<User> getCustomerInfo();
	public List<User> viewProduct();
	public User findByEmail(String email);
	public void updatePassword(String email, String newPassword);
	public String getNameByEmail(String email);
}
