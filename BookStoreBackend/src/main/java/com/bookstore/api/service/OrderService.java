package com.bookstore.api.service;

import com.bookstore.api.domain.BillingAddress;
import com.bookstore.api.domain.Order;
import com.bookstore.api.domain.Payment;
import com.bookstore.api.domain.ShippingAddress;
import com.bookstore.api.domain.ShoppingCart;
import com.bookstore.api.domain.User;

public interface OrderService {

	Order createOrder(
			ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			User user
			);
}
