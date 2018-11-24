package com.bookstore.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.api.domain.UserBilling;

public interface UserBillingRepository extends CrudRepository<UserBilling, Long>{

}
