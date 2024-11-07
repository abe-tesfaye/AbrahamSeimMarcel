package com.example.nothinbutnet.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // Create a new member
    @PostMapping
    public Member createMember(@RequestBody Member member) {
        // Set default status if not provided
        if (member.getStatus() == null) {
            member.setStatus("active"); // Default to "active"
        }
        member.setJoinDate(new Date()); // Set current date as join date
        member.setUpdatedDate(new Date()); // Set current date as updated date
        return memberService.saveMember(member);
    }

    // Retrieve all members
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    // Retrieve a member by ID
    @GetMapping("/{id}")
    public Optional<Member> getMemberById(@PathVariable int id) {
        return memberService.getMemberById(id);
    }

    // Retrieve a member by username and member ID
    @GetMapping("/search")
    public Optional<Member> findMemberByUsernameAndId(
            @RequestParam String username,
            @RequestParam int memberId) {
        return memberService.findMemberByUsernameAndId(username, memberId);
    }

    // Update a member's details
    @PutMapping("/{id}")
    public Member updateMember(@PathVariable int id, @RequestBody Member memberDetails) {
        Member existingMember = memberService.getMemberById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));
        existingMember.setEmail(memberDetails.getEmail());
        existingMember.setUsername(memberDetails.getUsername());
        existingMember.setPassword(memberDetails.getPassword());
        existingMember.updateLastModified(); // Update the updatedDate to now
        return memberService.updateMember(id, existingMember);
    }

    // Delete a member by ID
    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable int id) {
        memberService.deleteMember(id);
        return "Member with ID " + id + " has been deleted.";
    }
}