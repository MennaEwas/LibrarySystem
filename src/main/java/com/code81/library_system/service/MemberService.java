package com.code81.library_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.code81.library_system.repository.MemberRepository;
import com.code81.library_system.entities.Member;

@Service
public class MemberService {

    private final MemberRepository memberRepository; 

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //CRUD
    public Optional<Member> getMemberById(Long id){
        return memberRepository.findById(id);
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
    
    public Optional<Member> getMemberByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }
    
    public List<Member> getActiveMembers() {
        return memberRepository.findByActiveTrue();
    }

    public Member createMember(Member member){
        return memberRepository.save(member);
    }

    public Member updateMember(Member member){
        return memberRepository.save(member);
    }

    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
}
