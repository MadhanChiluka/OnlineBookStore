package com.bookstore.api.service;

import com.bookstore.api.domain.UserPayment;

public interface UserPaymentService {
	
	UserPayment findById(Long id);
	void removeById(Long id);
}
