package com.code81.library_system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.code81.library_system.service.MemberService;
import com.code81.library_system.entities.Member;



@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<Member>> getMembers(){
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Member>> getActiveMember(){
        List<Member> members = memberService.getActiveMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id){
        Optional<Member> member = memberService.getMemberById(id);
        return member.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member){
        Member dummyMember = memberService.createMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(dummyMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member member){
        member.setId(id);
        Member dummyMember = memberService.updateMember(member);
        return ResponseEntity.ok(dummyMember);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }

}
