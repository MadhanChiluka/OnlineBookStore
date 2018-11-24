package com.bookstore.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.api.domain.BookToCartItem;
import com.bookstore.api.domain.CartItem;

public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long> {
	void deleteByCartItem(CartItem cartItem);
}
