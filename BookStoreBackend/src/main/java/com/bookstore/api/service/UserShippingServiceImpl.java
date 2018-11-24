package com.bookstore.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.api.domain.UserShipping;
import com.bookstore.api.repository.UserShippingRepository;

@Service
public class UserShippingServiceImpl implements UserShippingService{

	@Autowired
	private UserShippingRepository userShippingRepository;
	
	@Override
	public UserShipping findById(Long id) {
		Optional<UserShipping> userShipping = userShippingRepository.findById(id);
		if(userShipping.isPresent()) {
			return userShipping.get();
		}
		else
		return null;
	}

	@Override
	public void removeById(Long id) {
		userShippingRepository.deleteById(id);
	}

}
