package com.Customer.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;


    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }


    public void addNewReview(Review review) {
        reviewRepository.save(review);
    }


    public void updateReview(int reviewId, Review review) {
        Review existing = reviewRepository.findById(reviewId).orElse(null);
        if (existing != null) {
            existing.setDescription(review.getDescription());
            existing.setRating(review.getRating());
            reviewRepository.save(existing);
        }
    }


    public void deleteReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}

