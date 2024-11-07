package com.example.nothinbutnet.Review;

import com.example.nothinbutnet.Member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberService memberService; // Add MemberService to find the member

    // Create a new review
    public Review saveReview(Review review) {
        // Ensure the member exists before saving the review
        if (review.getMember() == null || review.getMember().getMemberId() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member must be provided");
        }
        return reviewRepository.save(review);
    }

    // Retrieve all reviews
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Retrieve a review by ID
    public Optional<Review> getReviewById(int reviewId) {
        return reviewRepository.findById(reviewId);
    }

    // Retrieve all reviews for a specific member, ordered by review ID
    public List<Review> getReviewsByMemberId(int memberId) {
        return reviewRepository.findByMemberMemberIdOrderByReviewId(memberId);
    }

    // Delete a review by ID
    public void deleteReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}