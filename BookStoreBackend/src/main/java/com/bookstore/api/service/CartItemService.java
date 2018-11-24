package com.bookstore.api.service;

import java.util.List;

import com.bookstore.api.domain.Book;
import com.bookstore.api.domain.CartItem;
import com.bookstore.api.domain.ShoppingCart;
import com.bookstore.api.domain.User;

public interface CartItemService {

	CartItem addBookToCartItem(Book book , User user, int quantity );
	
	List <CartItem> findByShoppingCart(ShoppingCart cart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	//List<CartItem> findByOrder(Order order);
	
	void removeCartItem(CartItem cartItem);
	
	CartItem findById(Long id);
	
	CartItem save(CartItem cartItem);
	
	
}
