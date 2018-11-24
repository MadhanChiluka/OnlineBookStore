package com.bookstore.api.service;

import com.bookstore.api.domain.UserShipping;

public interface UserShippingService {

	UserShipping findById(Long id);
	void removeById(Long id);
}
