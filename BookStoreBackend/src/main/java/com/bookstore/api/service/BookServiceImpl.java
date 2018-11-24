package com.bookstore.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.api.domain.Book;
import com.bookstore.api.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired 
	private BookRepository bookRepository;

	@Override
	public List<Book> findAll() {
		List<Book> bookList = (List<Book>) bookRepository.findAll();
		
		List<Book> activeBookList = new ArrayList<>();
		
		for(Book book : bookList) {
			if(book.isActive()) {
				activeBookList.add(book);
			}
		}
		return activeBookList;
		
	}

	@Override
	public Book findOne(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		if(book.isPresent()) {
			return book.get();
		}
		return null;
	}

	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> blurrySearch(String keyword) {
		
		List<Book> bookList = (List<Book>) bookRepository.findBookByTitleContaining(keyword);
		
		List<Book> activeBookList = new ArrayList<>();
		
		for(Book book : bookList) {
			activeBookList.add(book);
		}
		 return activeBookList;
	}

	@Override
	public void removeOne(Long id) {
		bookRepository.deleteById(id);
		
	}

}
