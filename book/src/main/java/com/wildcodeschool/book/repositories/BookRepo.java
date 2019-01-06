package com.wildcodeschool.book.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wildcodeschool.book.entities.Book;

public interface BookRepo extends JpaRepository<Book, Long>{
	
	List<Book> findByTitleContainingOrDescriptionContaining(String text, String textAgain);
}
