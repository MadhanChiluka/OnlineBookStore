package com.bookstore.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.api.domain.UserPayment;

public interface UserPaymentRepository extends CrudRepository<UserPayment, Long> {

}
