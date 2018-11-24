package com.bookstore.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.api.domain.ShippingAddress;

public interface ShippingAddressRepository extends CrudRepository<ShippingAddress, Long> {

}
