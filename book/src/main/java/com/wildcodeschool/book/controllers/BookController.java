package com.wildcodeschool.book.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wildcodeschool.book.entities.Book;
import com.wildcodeschool.book.repositories.BookRepo;



@RestController
public class BookController {
	
	@Autowired
	BookRepo bookRepo;
	
	@GetMapping("/books")
	public List<Book> index(){
		return bookRepo.findAll();
	}
	
	@GetMapping("/books/{id}")
	public Book show(@PathVariable Long id) {
		return bookRepo.findById(id).get();
	}
	
	@PostMapping("/books")
	public Book create(@RequestBody Book book) {
		return bookRepo.save(book);
	}
	
	@PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepo.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }
	
	@PutMapping("/books/{id}")
	public Book update(@PathVariable Long id, @RequestBody Book book) {
		Book bookToUpdate = bookRepo.findById(id).get();
		if (book.getTitle() != null) {
			bookToUpdate.setTitle(book.getTitle());
		}
		
		if(book.getAuthor() != null) {
			bookToUpdate.setAuthor(book.getAuthor());
		}
		
		if (book.getDescription() != null) {
			bookToUpdate.setDescription(book.getDescription());
		}
		
		return bookRepo.save(bookToUpdate);
	}
	
	@DeleteMapping("books/{id}")
    public boolean delete(@PathVariable Long id){
        bookRepo.deleteById(id);
        return true;
    }
}
