package com.bookstore.api.service;

import java.util.Optional;
import java.util.Set;

import com.bookstore.api.domain.User;
import com.bookstore.api.domain.UserBilling;
import com.bookstore.api.domain.UserPayment;
import com.bookstore.api.domain.UserShipping;
import com.bookstore.api.domain.security.UserRole;

public interface UserService {
	User createUser(User user, Set<UserRole> userRoles);
	User findByUsername(String username);
	User findByEmail(String email);
	User save(User user);
	User findById(Long id);
	void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user);
	void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);
	void setUserDefaultPayment(Long userPaymentId, User user);
	void updateUserShipping(UserShipping userShipping, User user);
	void setUserDefaultShipping(Long userShippingId, User user);
}
