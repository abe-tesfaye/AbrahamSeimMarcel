package com.example.nothinbutnet.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Create or save a new member
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    // Retrieve all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // Retrieve a member by ID
    public Optional<Member> getMemberById(int memberId) {
        return memberRepository.findById(memberId);
    }

    // Retrieve a member by username and memberId
    public Optional<Member> findMemberByUsernameAndId(String username, int memberId) {
        return memberRepository.findByUsernameAndMemberId(username, memberId);
    }

    // Update a member's details
    public Member updateMember(int memberId, Member memberDetails) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with ID " + memberId));
        member.setEmail(memberDetails.getEmail());
        member.setUsername(memberDetails.getUsername());
        member.setPassword(memberDetails.getPassword());
        member.setUpdatedDate(memberDetails.getUpdatedDate());
        member.setStatus(memberDetails.getStatus());
        return memberRepository.save(member);
    }

    // Delete a member by ID
    public void deleteMember(int memberId) {
        memberRepository.deleteById(memberId);
    }
}