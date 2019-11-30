package com.mssql.beadando.controller;

import com.mssql.beadando.entity.Author;
import com.mssql.beadando.repository.AuthorRepository;
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
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable(value = "id") Long authorId) {
        Author author = findAuthorById(authorId);
        return ResponseEntity.ok().body(author);
    }

    @PostMapping("/authors")
    public Author createAuthor(@Valid @RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable(value = "id") Long authorId, @Valid @RequestBody Author authorDetails) {
        Author author = findAuthorById(authorId);

        author.setFirstName(authorDetails.getFirstName());
        author.setId(authorDetails.getId());
        author.setLastName(authorDetails.getLastName());

        return ResponseEntity.ok(authorRepository.save(author));
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable(value = "id") Long authorId) {
        authorRepository.delete(findAuthorById(authorId));
        return ResponseEntity.ok().build();
    }

    private Author findAuthorById(Long authorId) {
        return authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("Author not found with id: " + authorId));
    }
}
