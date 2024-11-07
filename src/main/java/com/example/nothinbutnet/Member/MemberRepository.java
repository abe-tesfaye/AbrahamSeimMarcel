package com.example.nothinbutnet.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByUsernameAndMemberId(String username, int memberId); // For searching by username and memberId
}
