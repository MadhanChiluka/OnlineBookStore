package com.bookstore.api.resources;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.domain.Order;
import com.bookstore.api.domain.User;
import com.bookstore.api.service.UserService;

@RestController
@RequestMapping("/order")
public class OrderResource {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/getOrderList")
	public List<Order> getOrderList(Principal principal) {
		User user = userService.findByUsername(principal.getName());
		List<Order> orderList = user.getOrderList();
		return orderList;
	}
}
