package com.bookstore.api.repository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.bookstore.api.domain.CartItem;
import com.bookstore.api.domain.ShoppingCart;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {
	List <CartItem> findByShoppingCart(ShoppingCart cart);
	//List<CartItem> findByOrder(Order order);

}
