package com.bookstore.api.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.api.domain.Book;
import com.bookstore.api.domain.BookToCartItem;
import com.bookstore.api.domain.CartItem;
import com.bookstore.api.domain.ShoppingCart;
import com.bookstore.api.domain.User;
import com.bookstore.api.repository.BookToCartItemRepository;
import com.bookstore.api.repository.CartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private CartItemRepository cartItemRepo;
	
	@Autowired
	private BookToCartItemRepository bookToCartItemRepo;

	@Override
	public CartItem addBookToCartItem(Book book, User user, int quantity) {
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
		
		for(CartItem cartItem : cartItemList ) {
			if(book.getId()==cartItem.getBook().getId()) {
				cartItem.setQty(cartItem.getQty()+quantity);
				cartItem.setSubTotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(quantity)));
				cartItemRepo.save(cartItem);
				return cartItem;
			}
		}
		
		CartItem cartItem =  new CartItem();
		cartItem.setQty(quantity);
		cartItem.setBook(book);
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setSubTotal(new BigDecimal(book.getOurPrice()).multiply(new BigDecimal(quantity)));
		
		cartItem = cartItemRepo.save(cartItem);
		
		BookToCartItem bookToCartItem = new BookToCartItem();
		bookToCartItem.setBook(book);
		bookToCartItem.setCartItem(cartItem);
		
		return cartItem;
	}

	@Override
	public List<CartItem> findByShoppingCart(ShoppingCart cart) {
		return cartItemRepo.findByShoppingCart(cart);
	}

	@Override
	public CartItem updateCartItem(CartItem cartItem) {
		BigDecimal bigDecimal = new BigDecimal(cartItem.getBook().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		cartItem.setSubTotal(bigDecimal);
		return cartItem;
		
	}

	@Override
	@Transactional
	public void removeCartItem(CartItem cartItem) {
		bookToCartItemRepo.deleteByCartItem(cartItem);
		cartItemRepo.delete(cartItem);
	}

	@Override
	public CartItem findById(Long id) {
		Optional<CartItem> cartItem =  cartItemRepo.findById(id);
		if(cartItem.isPresent()) {
			return cartItem.get();
		}
		return null;
	}

	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemRepo.save(cartItem);
	}
	
	/*@Override
	public List<CartItem> findByOrder(Order order) {
		return cartITemRepo.findByOrder(order);
	}*/

}
