package com.bookstore.api.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.api.domain.Book;

public interface BookService {
	List<Book> findAll();
	Book findOne(Long id);
	Book save(Book book);
	List<Book> blurrySearch(String title);
	void removeOne(Long id);

}
