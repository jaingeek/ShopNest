package com.example.demo.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.services.UsersService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

@Controller
@ResponseBody
public class PaymentController {

	@Autowired
	UsersService userv;
	
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder(@RequestParam("totalPrice") String totalPrice) {
	    Order order = null;
	    try {
	        // Convert totalPrice to an double (assuming it's an decimal value)
	    	double totalPriceDouble = Double.parseDouble(totalPrice);

	        // Convert the double value to paise (assuming the currency is in INR)
	        int totalPriceInt = (int) (totalPriceDouble * 100);
	        // Your existing RazorpayClient code
	        RazorpayClient razorpay = new RazorpayClient("rzp_test_OWQcoR8UCIctXh", "i388M6UuJ1p5E3qL9eaihzYf");
	        JSONObject orderRequest = new JSONObject();
	        orderRequest.put("amount", totalPriceInt);  // Convert amount to paise
	        orderRequest.put("currency", "INR");
	        orderRequest.put("receipt", "receipt#1");
	        JSONObject notes = new JSONObject();
	        notes.put("notes_key_1", "Tea, Earl Grey, Hot");
	        orderRequest.put("notes", notes);
	        order = razorpay.orders.create(orderRequest);

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Exception occurred");
	    }
	    if (order != null) {
	        return order.toString();
	    } else {
	        return "Failed to create order";
	    }

	}
	
	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam  String orderId, @RequestParam String paymentId, @RequestParam String signature) {
	    try {
	        // Initialize Razorpay client with your API key and secret
	        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_OWQcoR8UCIctXh", "i388M6UuJ1p5E3qL9eaihzYf");
	        // Create a signature verification data string
	        String verificationData = orderId + "|" + paymentId;

	        // Use Razorpay's utility function to verify the signature
	        boolean isValidSignature = Utils.verifySignature(verificationData, signature, "i388M6UuJ1p5E3qL9eaihzYf");

	        return isValidSignature;
	    } catch (RazorpayException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	@GetMapping("/payment-success")
	public String paymentSuccess(HttpSession session) {
		return "paymentsuccess";
	}
	
	@GetMapping("/payment-failure")
	@ResponseBody
	public String paymentFailure() {
		return "paymentfail";
	}
}

