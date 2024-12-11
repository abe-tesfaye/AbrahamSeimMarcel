package com.example.nothinbutnet.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Find a member by username and memberId
    Member findByUsernameAndMemberId(String username, Long memberId);

    // Find a member by username
    Member findByUsername(String username);

    // Custom query to search for members by status (e.g., "active", "inactive")
    List<Member> findByStatus(String status);

    // Search for members whose first or last name contains a keyword (case-insensitive)
    @Query("SELECT m FROM Member m WHERE LOWER(m.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(m.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Member> searchMembersByName(String keyword);

    // Custom query to find all members who joined after a specific date
    @Query("SELECT m FROM Member m WHERE m.joinDate > :joinDate")
    List<Member> findMembersByJoinDateAfter(java.time.LocalDate joinDate);
}