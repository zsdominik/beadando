package com.mssql.beadando.controller;

import com.mssql.beadando.entity.Publisher;
import com.mssql.beadando.repository.PublisherRepository;
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
public class PublisherController {

    @Autowired
    private PublisherRepository publisherRepository;

    @GetMapping("/publishers")
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @GetMapping("/publishers/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable(value = "id") Long publisherId) {
        Publisher publisher = findPublisherById(publisherId);
        return ResponseEntity.ok().body(publisher);
    }

    @PostMapping("/publishers")
    public Publisher createPublisher(@Valid @RequestBody Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @PutMapping("/publishers/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable(value = "id") Long publisherId, @Valid @RequestBody Publisher publisherDetails) {
        Publisher publisher = findPublisherById(publisherId);

        publisher.setId(publisherDetails.getId());
        publisher.setName(publisherDetails.getName());
        publisher.setPublishedBooks(publisherDetails.getPublishedBooks());

        return ResponseEntity.ok(publisherRepository.save(publisher));
    }

    @DeleteMapping("/publishers/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable(value = "id") Long publisherId) {
        publisherRepository.delete(findPublisherById(publisherId));
        return ResponseEntity.ok().build();
    }

    private Publisher findPublisherById(Long publisherId) {
        return publisherRepository.findById(publisherId).orElseThrow(() -> new RuntimeException("Publisher not found with id: " + publisherId));
    }

}
