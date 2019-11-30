package com.mssql.beadando.controller;

import com.mssql.beadando.entity.Reviewer;
import com.mssql.beadando.repository.ReviewerRepository;
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
public class ReviewerController {

    @Autowired
    private ReviewerRepository reviewerRepository;

    @GetMapping("/reviewers")
    public List<Reviewer> getAllReviewers() {
        return reviewerRepository.findAll();
    }

    @GetMapping("/reviewers/{id}")
    public ResponseEntity<Reviewer> getReviewerById(@PathVariable(value = "id") Long reviewerId) {
        Reviewer reviewer = findReviewerById(reviewerId);
        return ResponseEntity.ok().body(reviewer);
    }

    @PostMapping("/reviewers")
    public Reviewer createReviewer(@Valid @RequestBody Reviewer reviewer) {
        return reviewerRepository.save(reviewer);
    }

    @PutMapping("/reviewers/{id}")
    public ResponseEntity<Reviewer> updateReviewer(@PathVariable(value = "id") Long reviewerId, @Valid @RequestBody Reviewer reviewerDetails) {
        Reviewer reviewer = findReviewerById(reviewerId);

        reviewer.setId(reviewerDetails.getId());
        reviewer.setUsername(reviewerDetails.getUsername());

        return ResponseEntity.ok(reviewerRepository.save(reviewer));
    }

    @DeleteMapping("/reviewers/{id}")
    public ResponseEntity<Void> deleteReviewer(@PathVariable(value = "id") Long reviewerId) {
        reviewerRepository.delete(findReviewerById(reviewerId));
        return ResponseEntity.ok().build();
    }

    private Reviewer findReviewerById(Long reviewerId) {
        return reviewerRepository.findById(reviewerId).orElseThrow(() -> new RuntimeException("Reviewer not found with id: " + reviewerId));
    }

}
