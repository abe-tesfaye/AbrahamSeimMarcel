package com.Customer.demo;


import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reviewId;

    @Column(nullable = false)
    private String customer;

    @Column(nullable = false)
    private String product;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int rating;


    public Review(String customer, String product, String description, int rating) {
        this.customer = customer;
        this.product = product;
        this.description = description;
        this.rating = rating;
    }


    public Review() {}


    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

