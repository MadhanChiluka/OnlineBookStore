package com.bookstore.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.api.domain.Order;
import com.bookstore.api.domain.User;

public interface OrderRepository extends CrudRepository<Order, Long>{
	List<Order> findByUser(User user);

}
