package com.example.nothinbutnet.Member;

import com.example.nothinbutnet.Review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ReviewService reviewService;

    // Render Member List Page (Thymeleaf)
    @GetMapping
    public String getAllMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "members"; // Corresponds to a Thymeleaf template named "members.html"
    }

    // Render Add Member Form (Thymeleaf)
    @GetMapping("/add")
    public String renderAddMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "add-member"; // Corresponds to a Thymeleaf template named "add-member.html"
    }

    // Handle Add Member Form Submission (Thymeleaf)
    @PostMapping("/new")
    public String addNewMember(@ModelAttribute("member") Member member) {
        memberService.saveMember(member);
        return "redirect:/members";
    }

    // Render Edit Member Form (Thymeleaf)
    @GetMapping("/edit/{memberId}")
    public String renderEditMemberForm(@PathVariable Long memberId, Model model) {
        Optional<Member> member = memberService.getMemberById(memberId);
        if (member.isPresent()) {
            model.addAttribute("member", member.get());
            return "edit-member"; // Corresponds to a Thymeleaf template named "edit-member.html"
        } else {
            model.addAttribute("errorMessage", "Member with ID " + memberId + " not found.");
            return "error-page";
        }
    }

    // Handle Update Member Form Submission (Thymeleaf)
    @PostMapping("/update")
    public String updateMember(@ModelAttribute("member") Member member) {
        memberService.saveMember(member);
        return "redirect:/members";
    }

    // Handle Delete Member Request (Thymeleaf)
    @GetMapping("/delete/{memberId}")
    public String deleteMember(@PathVariable Long memberId, Model model) {
        if (memberService.getMemberById(memberId).isPresent()) {
            memberService.deleteMember(memberId);
            return "redirect:/members";
        } else {
            model.addAttribute("errorMessage", "Member with ID " + memberId + " not found.");
            return "error-page";
        }
    }

    // REST API Endpoint: Get all members (JSON)
    @ResponseBody
    @GetMapping("/api")
    public List<Member> getAllMembersAsJson() {
        return memberService.getAllMembers();
    }

    // REST API Endpoint: Get member by ID (JSON)
    @ResponseBody
    @GetMapping("/api/{memberId}")
    public Optional<Member> getMemberByIdAsJson(@PathVariable Long memberId) {
        return memberService.getMemberById(memberId);
    }

    // REST API Endpoint: Add a new member (JSON)
    @ResponseBody
    @PostMapping("/api/new")
    public Member addNewMemberAsJson(@RequestBody Member member) {
        return memberService.saveMember(member);
    }

    // REST API Endpoint: Update a member by ID (JSON)
    @ResponseBody
    @PutMapping("/api/update/{memberId}")
    public Member updateMemberAsJson(@PathVariable Long memberId, @RequestBody Member updatedMember) {
        Optional<Member> existingMember = memberService.getMemberById(memberId);
        if (existingMember.isPresent()) {
            Member member = existingMember.get();

            // Log for debugging
            System.out.println("Updating Member with ID: " + memberId);

            // Update fields
            member.setUsername(updatedMember.getUsername());
            member.setEmail(updatedMember.getEmail());
            member.setPassword(updatedMember.getPassword());
            member.setFirstName(updatedMember.getFirstName());
            member.setLastName(updatedMember.getLastName());

            // Check and update status
            String status = updatedMember.getStatus().toLowerCase();
            if (status.equals("active") || status.equals("inactive") || status.equals("suspended")) {
                member.setStatus(updatedMember.getStatus());
            } else {
                throw new IllegalArgumentException("Invalid status: " + updatedMember.getStatus());
            }

            member.setUpdatedDate(LocalDate.now());

            // Save updated member
            return memberService.saveMember(member);
        } else {
            throw new IllegalArgumentException("Member with ID " + memberId + " not found.");
        }
    }

    // REST API Endpoint: Delete a member by ID (JSON)
    @ResponseBody
    @DeleteMapping("/api/delete/{memberId}")
    public String deleteMemberAsJson(@PathVariable Long memberId) {
        Optional<Member> existingMember = memberService.getMemberById(memberId);
        if (existingMember.isPresent()) {
            memberService.deleteMember(memberId);
            return "Member with ID " + memberId + " has been deleted.";
        } else {
            throw new IllegalArgumentException("Member with ID " + memberId + " not found.");
        }
    }
}