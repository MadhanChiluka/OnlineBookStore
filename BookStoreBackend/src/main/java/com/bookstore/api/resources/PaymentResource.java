package com.bookstore.api.resources;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.domain.User;
import com.bookstore.api.domain.UserBilling;
import com.bookstore.api.domain.UserPayment;
import com.bookstore.api.service.UserPaymentService;
import com.bookstore.api.service.UserService;

@RestController
@RequestMapping("/payment")
public class PaymentResource {
	
	@Autowired
	private UserService userService;

	@Autowired 
	UserPaymentService userPaymentService;
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity addNewCreditCardPost(
			@RequestBody UserPayment userPayment,
			Principal principal
			) {
		User user = userService.findByUsername(principal.getName());
		UserBilling userBilling = userPayment.getUserBilling();
		userService.updateUserBilling(userBilling, userPayment, user);
		
		return new ResponseEntity("Payment Added/Updated Successfully!", HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public ResponseEntity removePaymentPost(
			@RequestBody  String id,
			Principal principal
			) {
		//User user = userService.findByUsername(principal.getName());

		userPaymentService.removeById(Long.valueOf(id));
		return new ResponseEntity("Payment removed succesfully!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/setDefault", method = RequestMethod.POST)
	public ResponseEntity setDefaultPaymentPost(
			@RequestBody String id,
			Principal principal
			) {
		User user = userService.findByUsername(principal.getName());
		userService.setUserDefaultPayment(Long.parseLong(id), user);
		return new ResponseEntity("Payment removed succesfully!", HttpStatus.OK);
	}
	
	@GetMapping("/getUserPaymentList")
	public List<UserPayment> getUserPaymentList(Principal principal) {
		User user = userService.findByUsername(principal.getName());
		List<UserPayment> userPaymentList = user.getUserPaymentList();
		return userPaymentList;
	}
}
