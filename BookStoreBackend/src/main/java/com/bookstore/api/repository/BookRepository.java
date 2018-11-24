package com.bookstore.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.api.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findBookByTitleContaining(String keyword);
}
