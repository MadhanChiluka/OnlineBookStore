package com.bookstore.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.api.domain.BillingAddress;

public interface BillingAddressRepository extends CrudRepository<BillingAddress, Long>{

}
