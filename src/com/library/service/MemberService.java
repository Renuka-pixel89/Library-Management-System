package com.library.service;

import com.library.model.Member;

import java.util.*;
import java.util.stream.Collectors;

public class MemberService {

    private Map<String, Member> memberMap = new LinkedHashMap<>();
    private int memberCounter = 1;

    // ── Register ──────────────────────────────────────────────────────────────
    public Member registerMember(String name, String email, String phone) {
        String id = "M" + String.format("%04d", memberCounter++);
        Member member = new Member(id, name, email, phone);
        memberMap.put(id, member);
        return member;
    }

    // ── Get ───────────────────────────────────────────────────────────────────
    public Optional<Member> getMember(String memberId) {
        return Optional.ofNullable(memberMap.get(memberId.toUpperCase()));
    }

    // ── Update ────────────────────────────────────────────────────────────────
    public boolean updateMember(String memberId, String name, String email, String phone) {
        Member m = memberMap.get(memberId.toUpperCase());
        if (m == null) return false;
        if (name  != null && !name.isBlank())  m.setName(name);
        if (email != null && !email.isBlank()) m.setEmail(email);
        if (phone != null && !phone.isBlank()) m.setPhone(phone);
        return true;
    }

    // ── Delete ────────────────────────────────────────────────────────────────
    public boolean deleteMember(String memberId) {
        Member m = memberMap.get(memberId.toUpperCase());
        if (m == null) return false;
        if (!m.getBorrowedBookIds().isEmpty()) {
            System.out.println("  ⚠  Cannot remove — member has unreturned books.");
            return false;
        }
        memberMap.remove(memberId.toUpperCase());
        return true;
    }

    // ── List All ──────────────────────────────────────────────────────────────
    public List<Member> getAllMembers() {
        return new ArrayList<>(memberMap.values());
    }

    // ── Search ────────────────────────────────────────────────────────────────
    public List<Member> searchByName(String keyword) {
        String kw = keyword.toLowerCase();
        return memberMap.values().stream()
                .filter(m -> m.getName().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }
}
