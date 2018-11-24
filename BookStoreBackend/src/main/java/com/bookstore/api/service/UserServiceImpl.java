package com.bookstore.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.api.domain.ShoppingCart;
import com.bookstore.api.domain.User;
import com.bookstore.api.domain.UserBilling;
import com.bookstore.api.domain.UserPayment;
import com.bookstore.api.domain.UserShipping;
import com.bookstore.api.domain.security.UserRole;
import com.bookstore.api.repository.RoleRepository;
import com.bookstore.api.repository.UserBillingRepository;
import com.bookstore.api.repository.UserPaymentRepository;
import com.bookstore.api.repository.UserRepository;
import com.bookstore.api.repository.UserShippingRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserBillingRepository userBillingRepository;
	
	@Autowired 
	private UserPaymentRepository userPaymentRepository;
	
	@Autowired
	private UserShippingRepository userShippingRepository;
	
	@Override
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			LOG.info(("User with username {}  already exists. Nothing will be done. "), user.getUsername());
		}
		else {
			
			for(UserRole ur : userRoles) {
				System.out.println("role" +ur.getRole());
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			
			ShoppingCart cart = new ShoppingCart();
			cart.setUser(user);
			
			user.setShoppingCart(cart);
			
			user.setUserPaymentList(new ArrayList<UserPayment>());
			user.setUserShippingList(new ArrayList<UserShipping>());
			
			localUser = userRepository.save(user);
		}
		return localUser;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user) {
		save(user);
		userBillingRepository.save(userBilling);
		userPaymentRepository.save(userPayment);
	}
	
	@Override
	public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
		userPayment.setUser(user);
		userPayment.setUserBilling(userBilling);
		userPayment.setDefaultPayment(true);
		userBilling.setUserPayment(userPayment);
		user.getUserPaymentList().add(userPayment);
		save(user);
	}
	
	@Override
	public void setUserDefaultPayment(Long userPaymentId, User user) {
		List<UserPayment> userPaymentList = (List<UserPayment>) userPaymentRepository.findAll();
		for(UserPayment userPayment : userPaymentList) {
			if(userPayment.getId()== userPaymentId) {
				userPayment.setDefaultPayment(true);
				userPaymentRepository.save(userPayment);
			}else {
				userPayment.setDefaultPayment(false);
				userPaymentRepository.save(userPayment);
			}
		}
	}

	@Override
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		return null;
	}
	
	@Override 
	public void updateUserShipping(UserShipping userShipping, User user) {
		userShipping.setUser(user);
		userShipping.setUserShippingDefault(true);
		user.getUserShippingList().add(userShipping);
		save(user);
	}
	
	@Override
	public void setUserDefaultShipping(Long userShippingId, User user) {
		List<UserShipping> userShippingList = (List<UserShipping>) userShippingRepository.findAll();
		for(UserShipping userShipping : userShippingList) {
			if(userShipping.getId()== userShippingId) {
				userShipping.setUserShippingDefault(true);
				userShippingRepository.save(userShipping);
			}
			else {
				userShipping.setUserShippingDefault(false);
				userShippingRepository.save(userShipping);
			}
		}
	}

}
