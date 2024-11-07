package com.example.nothinbutnet.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Create a new review
    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }

    // Retrieve all reviews
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // Retrieve a review by ID
    @GetMapping("/{id}")
    public Optional<Review> getReviewById(@PathVariable int id) {
        return reviewService.getReviewById(id);
    }

    // Retrieve all reviews for a specific member
    @GetMapping("/member/{memberId}")
    public List<Review> getReviewsByMemberId(@PathVariable int memberId) {
        return reviewService.getReviewsByMemberId(memberId);
    }

    // Delete a review by ID
    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return "Review with ID " + id + " has been deleted.";
    }
}