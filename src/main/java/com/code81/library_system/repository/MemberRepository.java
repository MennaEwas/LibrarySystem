package com.code81.library_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.code81.library_system.entities.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByMemberId(String memberId);
    List<Member> findByActiveTrue();
    boolean existsByEmail(String email);
    boolean existsByMemberId(String memberId);

}
