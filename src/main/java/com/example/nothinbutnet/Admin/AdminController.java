package com.example.nothinbutnet.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.nothinbutnet.Member.Member;
import com.example.nothinbutnet.Review.Review;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Admin login
    @PostMapping("/login")
    public Optional<Admin> login(@RequestParam String username, @RequestParam String password) {
        return adminService.login(username, password);
    }

    // Get all members
    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return adminService.getAllMembers();
    }

    // Search for a member by username and member ID
    @GetMapping("/member/search")
    public Optional<Member> findMember(@RequestParam String username, @RequestParam int memberId) {
        return adminService.findMember(username, memberId);
    }

    // Get all reviews for a member, ordered by review ID
    @GetMapping("/member/{memberId}/reviews")
    public List<Review> getMemberReviews(@PathVariable int memberId) {
        return adminService.getMemberReviews(memberId);
    }

    // Delete a specific review by review ID
    @DeleteMapping("/review/{reviewId}")
    public String deleteReview(@PathVariable int reviewId) {
        adminService.deleteReview(reviewId);
        return "Review with ID " + reviewId + " has been deleted.";
    }

    // Suspend a member account
    @PutMapping("/member/{memberId}/suspend")
    public Member suspendMemberAccount(@PathVariable int memberId) {
        return adminService.suspendMemberAccount(memberId);
    }
}