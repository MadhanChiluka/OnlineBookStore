package com.bookstore.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.api.domain.Payment;

public interface PaymentRepository extends CrudRepository<Payment , Long> {

}
