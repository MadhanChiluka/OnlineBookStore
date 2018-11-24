package com.bookstore.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.api.domain.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long>{

}
