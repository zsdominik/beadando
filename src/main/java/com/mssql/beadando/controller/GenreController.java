package com.mssql.beadando.controller;

import com.mssql.beadando.entity.Genre;
import com.mssql.beadando.repository.GenreRepository;
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
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/genres")
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable(value = "id") Long genreId) {
        Genre genre = findGenreById(genreId);
        return ResponseEntity.ok().body(genre);
    }

    @PostMapping("/genres")
    public Genre createGenre(@Valid @RequestBody Genre genre) {
        return genreRepository.save(genre);
    }

    @PutMapping("/genres/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable(value = "id") Long genreId, @Valid @RequestBody Genre genreDetails) {
        Genre genre = findGenreById(genreId);

        genre.setId(genreDetails.getId());
        genre.setName(genreDetails.getName());

        return ResponseEntity.ok(genreRepository.save(genre));
    }

    @DeleteMapping("/genres/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable(value = "id") Long genreId) {
        genreRepository.delete(findGenreById(genreId));
        return ResponseEntity.ok().build();
    }

    private Genre findGenreById(Long genreId) {
        return genreRepository.findById(genreId).orElseThrow(() -> new RuntimeException("Genre not found with id: " + genreId));
    }

}
