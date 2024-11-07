package com.example.nothinbutnet.Review;

import jakarta.persistence.*;
import com.example.nothinbutnet.Member.Member;  // Import Member entity
import java.util.Date;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    private String content;
    private int rating;

    @Temporal(TemporalType.DATE)  // Specify temporal type for Date
    private Date reviewDate;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // No-argument constructor
    public Review() {}

    // Parameterized constructor
    public Review(String content, int rating, Member member) {
        this.content = content;
        this.rating = rating;
        this.reviewDate = new Date(); // Set the review date to now when the review is created
        this.member = member;
    }

    // Getters and Setters
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}