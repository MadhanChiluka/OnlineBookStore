package com.bookstore.api.resources;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.domain.User;
import com.bookstore.api.domain.UserShipping;
import com.bookstore.api.service.UserService;
import com.bookstore.api.service.UserShippingService;

@RestController
@RequestMapping("/shipping")
public class ShippingResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserShippingService userShippingService;
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ResponseEntity addNewUserShippingPost(
			@RequestBody UserShipping userShipping, Principal principal
			) {
		User user = userService.findByUsername(principal.getName());
		
		userService.updateUserShipping(userShipping, user);
		
		return new ResponseEntity("Shipping Added/Updated sucessfully!", HttpStatus.OK);
		
	}
	
	@GetMapping("/getUserShippingList")
	public List<UserShipping> getUserShippingList(Principal principal) {
		User user = userService.findByUsername(principal.getName());
		List<UserShipping> userShippingList = user.getUserShippingList();
		return userShippingList;
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST) 
	public ResponseEntity removeUserShippingPost(@RequestBody String id, Principal principal) {
		userShippingService.removeById(Long.parseLong(id));
		return new ResponseEntity("Shipping Removed successfully!", HttpStatus.OK);
	}
	
	public ResponseEntity setDefaultUserShippingPost(@RequestBody String id, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		userService.setUserDefaultShipping(Long.parseLong(id), user);
		
		return new ResponseEntity("Set Default Shipping successful", HttpStatus.OK);
	}
	
}
