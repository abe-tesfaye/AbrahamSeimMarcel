package com.example.nothinbutnet.Review;

import com.example.nothinbutnet.Member.Member;
import com.example.nothinbutnet.Member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MemberService memberService;

    // Get all reviews (Thymeleaf)
    @GetMapping
    public String getAllReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviews"; // Corresponds to "reviews.html"
    }

    // Get a specific review by ID (Thymeleaf)
    @GetMapping("/{reviewId}")
    public String getReviewById(@PathVariable Long reviewId, Model model) {
        Optional<Review> review = reviewService.getReviewById(reviewId);
        if (review.isPresent()) {
            model.addAttribute("review", review.get());
            return "review-details"; // Corresponds to "review-details.html"
        } else {
            model.addAttribute("errorMessage", "Review with ID " + reviewId + " not found.");
            return "error-page"; // Redirect to a Thymeleaf error page
        }
    }

    // Render Add Review Form (Thymeleaf)
    @GetMapping("/add")
    public String renderAddReviewForm(Model model) {
        model.addAttribute("review", new Review());
        return "add-review"; // Corresponds to "add-review.html"
    }

    // Handle Add Review Form Submission (Thymeleaf)
    @PostMapping("/new")
    public String addNewReview(@ModelAttribute("review") Review review) {
        reviewService.addNewReview(review);
        return "redirect:/reviews";
    }

    // Render Edit Review Form (Thymeleaf)
    @GetMapping("/edit/{reviewId}")
    public String renderEditReviewForm(@PathVariable Long reviewId, Model model) {
        Optional<Review> review = reviewService.getReviewById(reviewId);
        if (review.isPresent()) {
            model.addAttribute("review", review.get());
            return "edit-review"; // Corresponds to "edit-review.html"
        } else {
            model.addAttribute("errorMessage", "Review with ID " + reviewId + " not found.");
            return "error-page"; // Redirect to a Thymeleaf error page
        }
    }

    // Handle Edit Review Form Submission (Thymeleaf)
    @PostMapping("/update")
    public String updateReview(@ModelAttribute("review") Review review) {
        reviewService.updateReview(review.getReviewId(), review);
        return "redirect:/reviews";
    }

    // Handle Delete Review Request (Thymeleaf)
    @GetMapping("/delete/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId, Model model) {
        if (reviewService.getReviewById(reviewId).isPresent()) {
            reviewService.deleteReview(reviewId);
            return "redirect:/reviews";
        } else {
            model.addAttribute("errorMessage", "Review with ID " + reviewId + " not found.");
            return "error-page"; // Redirect to a Thymeleaf error page
        }
    }

    // REST API: Get all reviews
    @ResponseBody
    @GetMapping("/api")
    public List<Review> getAllReviewsAsJson() {
        return reviewService.getAllReviews();
    }

    // REST API: Get a specific review by ID
    @ResponseBody
    @GetMapping("/api/{reviewId}")
    public Optional<Review> getReviewByIdAsJson(@PathVariable Long reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    // REST API: Add a new review
    @ResponseBody
    @PostMapping("/api/new")
    public Review addNewReviewAsJson(@RequestBody Review review) {
        // Ensure the member exists before associating the review
        Optional<Member> member = memberService.getMemberById(review.getMember().getMemberId());
        if (member.isPresent()) {
            review.setMember(member.get());
            return reviewService.addNewReview(review);
        } else {
            throw new IllegalArgumentException("Member with ID " + review.getMember().getMemberId() + " not found.");
        }
    }

    // REST API: Update a review by ID
    @ResponseBody
    @PutMapping("/api/update/{reviewId}")
    public Review updateReviewAsJson(@PathVariable Long reviewId, @RequestBody Review updatedReview) {
        return reviewService.updateReview(reviewId, updatedReview);
    }

    // REST API: Delete a review by ID
    @ResponseBody
    @DeleteMapping("/api/delete/{reviewId}")
    public String deleteReviewAsJson(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return "Review with ID " + reviewId + " has been deleted.";
    }
}