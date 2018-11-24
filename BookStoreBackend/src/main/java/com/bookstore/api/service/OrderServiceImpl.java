package com.bookstore.api.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.api.domain.BillingAddress;
import com.bookstore.api.domain.Book;
import com.bookstore.api.domain.CartItem;
import com.bookstore.api.domain.Order;
import com.bookstore.api.domain.Payment;
import com.bookstore.api.domain.ShippingAddress;
import com.bookstore.api.domain.ShoppingCart;
import com.bookstore.api.domain.User;
import com.bookstore.api.repository.BillingAddressRepository;
import com.bookstore.api.repository.OrderRepository;
import com.bookstore.api.repository.PaymentRepository;
import com.bookstore.api.repository.ShippingAddressRepository;
import com.bookstore.api.utility.MailConstructor;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ShippingAddressRepository shippingAddressRepository;
	
	@Autowired
	private BillingAddressRepository billingAddressRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired 
	private BookService bookService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private MailConstructor mailConstructor;

	@Override
	public synchronized Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress,
			Payment payment, String shippingMethod, User user) {
		
		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList) {
			Book book = cartItem.getBook();
			cartItem.setOrder(order);
			book.setInStockNumber(book.getInStockNumber() - cartItem.getQty());
		}
		order.setCartItemList(cartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shippingAddress.setOrder(order);
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		
		return order;
	}
	
	public Order findOne(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		if(order.isPresent()) {
			return order.get();
		}
		else return null;
	}
}
