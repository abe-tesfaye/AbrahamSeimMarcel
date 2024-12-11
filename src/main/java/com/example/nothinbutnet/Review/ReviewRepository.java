package com.example.nothinbutnet.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Find reviews by member ID
    List<Review> findByMemberMemberId(Long memberId);

    // Find reviews by product ID
    List<Review> findByProductId(Long productId);

    // Custom query to search for reviews with a rating above a certain value
    @Query("SELECT r FROM Review r WHERE r.rating > :rating")
    List<Review> findReviewsWithRatingAbove(int rating);

    // Custom query to fetch reviews containing a specific keyword in content
    @Query("SELECT r FROM Review r WHERE LOWER(r.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Review> searchReviewsByContent(String keyword);
}