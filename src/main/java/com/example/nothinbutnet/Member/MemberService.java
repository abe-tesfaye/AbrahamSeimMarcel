package com.example.nothinbutnet.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Retrieve all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Retrieve a member by ID
    public Optional<Member> getMemberById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // Retrieve a member by username and memberId
    public Optional<Member> getMemberByUsernameAndId(String username, Long memberId) {
        return Optional.ofNullable(memberRepository.findByUsernameAndMemberId(username, memberId));
    }

    // Retrieve members by status
    public List<Member> getMembersByStatus(String status) {
        return memberRepository.findByStatus(status);
    }

    // Search members by name (firstName or lastName)
    public List<Member> searchMembersByName(String keyword) {
        return memberRepository.searchMembersByName(keyword);
    }

    // Retrieve members who joined after a specific date
    public List<Member> getMembersByJoinDateAfter(LocalDate joinDate) {
        return memberRepository.findMembersByJoinDateAfter(joinDate);
    }

    // Save a new member or update an existing one
    public Member saveMember(Member member) {
        member.setUpdatedDate(LocalDate.now()); // Automatically set the updated date
        return memberRepository.save(member);
    }

    // Update a member's details
    public Member updateMember(Long memberId, Member updatedMember) {
        Optional<Member> existingMember = memberRepository.findById(memberId);
        if (existingMember.isPresent()) {
            Member member = existingMember.get();
            member.setUsername(updatedMember.getUsername());
            member.setEmail(updatedMember.getEmail());
            member.setPassword(updatedMember.getPassword());
            member.setFirstName(updatedMember.getFirstName());
            member.setLastName(updatedMember.getLastName());
            member.setStatus(updatedMember.getStatus());
            member.setUpdatedDate(LocalDate.now()); // Automatically set the updated date
            return memberRepository.save(member);
        } else {
            throw new IllegalArgumentException("Member with ID " + memberId + " not found.");
        }
    }

    // Delete a member by ID
    public void deleteMember(Long memberId) {
        if (memberRepository.existsById(memberId)) {
            memberRepository.deleteById(memberId);
        } else {
            throw new IllegalArgumentException("Member with ID " + memberId + " not found.");
        }
    }
}