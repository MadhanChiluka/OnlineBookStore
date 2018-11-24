package com.bookstore.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.api.domain.UserPayment;
import com.bookstore.api.repository.UserPaymentRepository;

@Service
public class UserPaymentServiceImpl implements UserPaymentService{

	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	@Override
	public UserPayment findById(Long id) {
		Optional<UserPayment> userPayment = userPaymentRepository.findById(id);
		if(userPayment.isPresent()) {
			return userPayment.get();
		}
		return null;
	}

	@Override
	public void removeById(Long id) {
		 userPaymentRepository.deleteById(id);
	}

}
