package com.example.nothinbutnet.Admin;

import com.example.nothinbutnet.Member.Member;
import com.example.nothinbutnet.Review.Review;

import com.example.nothinbutnet.Member.MemberRepository;
import com.example.nothinbutnet.Review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    // Authenticate admin login
    public Optional<Admin> login(String username, String password) {
        return Optional.ofNullable(adminRepository.findByUsernameAndPassword(username, password));
    }

    // Retrieve all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Search for a member by username and member ID
    public Optional<Member> findMember(String username, int memberId) {
        return memberRepository.findByUsernameAndMemberId(username, memberId);
    }

    // Retrieve all reviews for a member, ordered by review ID
    public List<Review> getMemberReviews(int memberId) {
        return reviewRepository.findByMemberMemberIdOrderByReviewId(memberId);
    }

    // Delete a review by review ID
    public void deleteReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    // Suspend a member's account by setting their status to "suspended"
    public Member suspendMemberAccount(int memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with ID " + memberId));
        member.setStatus("suspended");
        return memberRepository.save(member);
    }
}