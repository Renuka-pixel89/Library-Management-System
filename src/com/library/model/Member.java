package com.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    private String memberId;
    private String name;
    private String email;
    private String phone;
    private List<String> borrowedBookIds;

    public Member(String memberId, String name, String email, String phone) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.borrowedBookIds = new ArrayList<>();
    }

    // Getters and Setters
    public String getMemberId()               { return memberId; }
    public String getName()                   { return name; }
    public String getEmail()                  { return email; }
    public String getPhone()                  { return phone; }
    public List<String> getBorrowedBookIds()  { return borrowedBookIds; }

    public void setName(String name)          { this.name = name; }
    public void setEmail(String email)        { this.email = email; }
    public void setPhone(String phone)        { this.phone = phone; }

    public void borrowBook(String bookId) {
        borrowedBookIds.add(bookId);
    }

    public void returnBook(String bookId) {
        borrowedBookIds.remove(bookId);
    }

    public boolean hasBorrowed(String bookId) {
        return borrowedBookIds.contains(bookId);
    }

    @Override
    public String toString() {
        return String.format("| %-10s | %-20s | %-25s | %-12s | %-5d books |",
                memberId, name, email, phone, borrowedBookIds.size());
    }
}
