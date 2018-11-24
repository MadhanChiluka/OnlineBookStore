package com.bookstore.api.service;

import com.bookstore.api.domain.ShoppingCart;

public interface ShoppingCartService {

	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	
	void clearShoppingCart(ShoppingCart shoppingCart);
}
