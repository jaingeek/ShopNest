package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UsersRepository;

@Service
public class UserServiceImplementation implements UsersService{

	@Autowired
	UsersRepository urepo;

	@Override
	public String addUser(User user) {
		urepo.save(user);
		return "succesfully added";
	}

	@Override
	public boolean validateUser(String email) {
		User user = urepo.findByEmail(email);
		if(user!=null) {
			return false;
		}
		else return true;
	}

	@Override
	public boolean validateLogin(String email, String password) {
		User user = urepo.findByEmail(email);
		if(user!=null) {
			if(user.getEmail().equals(email)) {
				if(user.getPassword().equals(password)) {
					return true;
				}
				else return false;
			}
			else return false;
		}
		else return false;
	}

	@Override
	public boolean isAdmin(String email) {
		User user = urepo.findByEmail(email);
		if(user!=null) {
			if(user.getEmail().equals("admin@admin.com")) {
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	@Override
	public List<User> getCustomerInfo() {
		List<User> customerList = urepo.findAll();
		return customerList;
	}

	@Override
	public List<User> viewProduct() {
		
		return urepo.findAll();
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return urepo.findByEmail(email);
	}

	@Override
	public void updatePassword(String email, String newPassword) {
		// TODO Auto-generated method stub
		User user = urepo.findByEmail(email);
		if(user!=null) {
			user.setPassword(newPassword);
			urepo.save(user);
		}
	}

	@Override
	public String getNameByEmail(String email) {
		// TODO Auto-generated method stub
		User user = urepo.findByEmail(email);
		return user.getName();
	}
}
