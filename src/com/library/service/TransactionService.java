package com.library.service;

import com.library.model.Transaction;
import com.library.model.Transaction.Type;
import com.library.model.Member;
import com.library.model.Book;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionService {

    private List<Transaction> transactions = new ArrayList<>();
    private int txCounter = 1;

    private BookService   bookService;
    private MemberService memberService;

    public TransactionService(BookService bookService, MemberService memberService) {
        this.bookService   = bookService;
        this.memberService = memberService;
    }

    // ── Borrow ────────────────────────────────────────────────────────────────
    public boolean borrowBook(String memberId, String bookId) {
        Optional<Member> optMember = memberService.getMember(memberId);
        Optional<Book>   optBook   = bookService.getBook(bookId);

        if (optMember.isEmpty()) { System.out.println("  ✗  Member not found."); return false; }
        if (optBook.isEmpty())   { System.out.println("  ✗  Book not found.");   return false; }

        Member member = optMember.get();
        Book   book   = optBook.get();

        if (member.hasBorrowed(bookId.toUpperCase())) {
            System.out.println("  ✗  Member has already borrowed this book.");
            return false;
        }
        if (!bookService.decrementCopy(bookId)) {
            System.out.println("  ✗  No available copies right now.");
            return false;
        }

        member.borrowBook(book.getBookId());
        String txId = "TX" + String.format("%05d", txCounter++);
        transactions.add(new Transaction(txId, memberId.toUpperCase(), book.getBookId(), Type.BORROW));
        System.out.println("  ✔  Book borrowed successfully! Transaction ID: " + txId);
        return true;
    }

    // ── Return ────────────────────────────────────────────────────────────────
    public boolean returnBook(String memberId, String bookId) {
        Optional<Member> optMember = memberService.getMember(memberId);
        Optional<Book>   optBook   = bookService.getBook(bookId);

        if (optMember.isEmpty()) { System.out.println("  ✗  Member not found."); return false; }
        if (optBook.isEmpty())   { System.out.println("  ✗  Book not found.");   return false; }

        Member member = optMember.get();
        Book   book   = optBook.get();

        if (!member.hasBorrowed(book.getBookId())) {
            System.out.println("  ✗  This member did not borrow this book.");
            return false;
        }

        member.returnBook(book.getBookId());
        bookService.incrementCopy(book.getBookId());
        String txId = "TX" + String.format("%05d", txCounter++);
        transactions.add(new Transaction(txId, memberId.toUpperCase(), book.getBookId(), Type.RETURN));
        System.out.println("  ✔  Book returned successfully! Transaction ID: " + txId);
        return true;
    }

    // ── History ───────────────────────────────────────────────────────────────
    public List<Transaction> getAllTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public List<Transaction> getTransactionsByMember(String memberId) {
        return transactions.stream()
                .filter(t -> t.getMemberId().equalsIgnoreCase(memberId))
                .collect(Collectors.toList());
    }
}
