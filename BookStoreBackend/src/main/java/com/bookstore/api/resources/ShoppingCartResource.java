package com.bookstore.api.resources;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.api.domain.Book;
import com.bookstore.api.domain.CartItem;
import com.bookstore.api.domain.ShoppingCart;
import com.bookstore.api.domain.User;
import com.bookstore.api.service.BookService;
import com.bookstore.api.service.CartItemService;
import com.bookstore.api.service.ShoppingCartService;
import com.bookstore.api.service.UserService;

@RestController
@RequestMapping("/cart")
public class ShoppingCartResource {
	
	@Autowired
	private BookService bookService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping("/add")
	public ResponseEntity addItem(@RequestBody HashMap<String, String> mapper, Principal principal) {
		String bookId = mapper.get("bookId");
		String quantity = mapper.get("qty");
		System.out.println(bookId);
		System.out.println(quantity);
		
		User user = userService.findByUsername(principal.getName());
		Book book = bookService.findOne(Long.parseLong(bookId));
		
		if(Integer.parseInt(quantity) > book.getInStockNumber()) {
			return new ResponseEntity("Not Enough Stock", HttpStatus.BAD_REQUEST);
		}
		CartItem cartItem = cartItemService.addBookToCartItem(book, user, Integer.parseInt(quantity));
		return new ResponseEntity("Book added succesfully!", HttpStatus.OK);
	}
	
	@GetMapping("/getCartItemList")
	public List<CartItem> getCartItemList(Principal principal) {
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		
		return cartItemList;
	}
	
	@GetMapping("/getShoppingCart")
	public ShoppingCart getShoppingCart(Principal principal) {
		User user = userService.findByUsername(principal.getName());
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		shoppingCartService.updateShoppingCart(shoppingCart);
		return shoppingCart;
	}
	
	@RequestMapping("/removeItem")
	public ResponseEntity removeCartItem(@RequestBody String id) {
		cartItemService.removeCartItem(cartItemService.findById(Long.parseLong(id)));
		return new ResponseEntity("CartItem removed succesfully!", HttpStatus.OK);
	}
	
	@RequestMapping("/updateCartItem")
	public ResponseEntity updateCartItem(@RequestBody HashMap<String, String> mapper) {
		String cartItemId = mapper.get("cartItemId");
		String quantity = mapper.get("qty");
		
		CartItem cartItem = cartItemService.findById(Long.parseLong(cartItemId));
		cartItem.setQty(Integer.parseInt(quantity));
		cartItemService.updateCartItem(cartItem);
		
		return new ResponseEntity("CartItem updated succesfully!", HttpStatus.OK);
	}
}
