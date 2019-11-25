package com.mssql.beadando.controller;

import com.mssql.beadando.entity.Book;
import com.mssql.beadando.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long bookId) {
        Book book = findBookById(bookId);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("/books")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long bookId, @Valid @RequestBody Book bookDetails) {
        Book book = findBookById(bookId);

        book.setAuthor(bookDetails.getAuthor());
        book.setAvailableQuantity(bookDetails.getAvailableQuantity());
        book.setEdition(bookDetails.getEdition());
        book.setGenre(bookDetails.getGenre());
        book.setPrice(bookDetails.getPrice());
        book.setPublicationDate(bookDetails.getPublicationDate());
        book.setPublisher(bookDetails.getPublisher());
        book.setTitle(bookDetails.getTitle());

        return ResponseEntity.ok(bookRepository.save(book));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable(value = "id") Long bookId) {
        bookRepository.delete(findBookById(bookId));
        return ResponseEntity.ok().build();
    }

    private Book findBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
    }
}
