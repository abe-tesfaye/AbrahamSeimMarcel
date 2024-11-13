package com.Customer.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/all")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }




    @PostMapping("/new")
    public void addNewReview(@RequestBody Review review) {
        reviewService.addNewReview(review);
    }


    @PutMapping("/update/{reviewId}")
    public Review updateReview(@PathVariable int reviewId, @RequestBody Review review) {
        reviewService.updateReview(reviewId, review);
        return reviewService.getAllReviews().stream()
                .filter(r -> r.getReviewId() == reviewId)
                .findFirst()
                .orElse(null);
    }


    @DeleteMapping("/delete/{reviewId}")
    public void deleteReview(@PathVariable int reviewId) {
        reviewService.deleteReview(reviewId);
    }
}

