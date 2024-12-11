package com.example.nothinbutnet.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    // Fetch all reviews
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Fetch reviews by member ID
    public List<Review> getReviewsByMemberId(Long memberId) {
        return reviewRepository.findByMemberMemberId(memberId);
    }

    // Fetch reviews by product ID
    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    // Fetch a specific review by ID
    public Optional<Review> getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    // Add a new review
    public Review addNewReview(Review review) {
        return reviewRepository.save(review);
    }

    // Update an existing review
    public Review updateReview(Long reviewId, Review updatedReview) {
        return reviewRepository.findById(reviewId)
                .map(existingReview -> {
                    existingReview.setContent(updatedReview.getContent());
                    existingReview.setRating(updatedReview.getRating());
                    existingReview.setReviewDate(updatedReview.getReviewDate());
                    existingReview.setProductId(updatedReview.getProductId());
                    existingReview.setMember(updatedReview.getMember());
                    return reviewRepository.save(existingReview);
                })
                .orElseThrow(() -> new IllegalArgumentException("Review with ID " + reviewId + " not found."));
    }

    // Delete a review by ID
    public void deleteReview(Long reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new IllegalArgumentException("Review with ID " + reviewId + " not found.");
        }
    }

    // Fetch reviews with a rating above a specific value
    public List<Review> getReviewsWithRatingAbove(int rating) {
        return reviewRepository.findReviewsWithRatingAbove(rating);
    }

    // Search reviews by content keyword
    public List<Review> searchReviewsByContent(String keyword) {
        return reviewRepository.searchReviewsByContent(keyword);
    }
}