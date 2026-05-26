package com.library.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Type { BORROW, RETURN }

    private String transactionId;
    private String memberId;
    private String bookId;
    private Type type;
    private LocalDate date;

    public Transaction(String transactionId, String memberId, String bookId, Type type) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.type = type;
        this.date = LocalDate.now();
    }

    public String getTransactionId()  { return transactionId; }
    public String getMemberId()       { return memberId; }
    public String getBookId()         { return bookId; }
    public Type getType()             { return type; }
    public LocalDate getDate()        { return date; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("| %-12s | %-10s | %-8s | %-8s | %s |",
                transactionId, memberId, bookId, type, date.format(fmt));
    }
}
