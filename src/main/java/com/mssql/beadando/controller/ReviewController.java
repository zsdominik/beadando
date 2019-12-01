package com.mssql.beadando.controller;

import com.mssql.beadando.entity.Review;
import com.mssql.beadando.repository.ReviewRepository;
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
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/reviews")
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable(value = "id") Long reviewId) {
        Review review = findReviewById(reviewId);
        return ResponseEntity.ok().body(review);
    }

    @PostMapping("/reviews")
    public Review createReview(@Valid @RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable(value = "id") Long reviewId, @Valid @RequestBody Review reviewDetails) {
        Review review = findReviewById(reviewId);

        review.setBook(reviewDetails.getBook());
        review.setId(reviewDetails.getId());
        review.setReviewText(reviewDetails.getReviewText());

        return ResponseEntity.ok(reviewRepository.save(review));
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable(value = "id") Long reviewId) {
        reviewRepository.delete(findReviewById(reviewId));
        return ResponseEntity.ok().build();
    }

    private Review findReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));
    }
}
