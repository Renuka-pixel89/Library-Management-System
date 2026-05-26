package com.library.service;

import com.library.model.Book;

import java.util.*;
import java.util.stream.Collectors;

public class BookService {

    private Map<String, Book> bookMap = new LinkedHashMap<>();
    private int bookCounter = 1;

    // ── Add Book ──────────────────────────────────────────────────────────────
    public Book addBook(String title, String author, String genre, int copies) {
        String id = "B" + String.format("%03d", bookCounter++);
        Book book = new Book(id, title, author, genre, copies);
        bookMap.put(id, book);
        return book;
    }

    // ── Get Book ──────────────────────────────────────────────────────────────
    public Optional<Book> getBook(String bookId) {
        return Optional.ofNullable(bookMap.get(bookId.toUpperCase()));
    }

    // ── Update Book ───────────────────────────────────────────────────────────
    public boolean updateBook(String bookId, String title, String author, String genre) {
        Book book = bookMap.get(bookId.toUpperCase());
        if (book == null) return false;
        if (title  != null && !title.isBlank())  book.setTitle(title);
        if (author != null && !author.isBlank()) book.setAuthor(author);
        if (genre  != null && !genre.isBlank())  book.setGenre(genre);
        return true;
    }

    // ── Delete Book ───────────────────────────────────────────────────────────
    public boolean deleteBook(String bookId) {
        Book book = bookMap.get(bookId.toUpperCase());
        if (book == null) return false;
        if (book.getAvailableCopies() < book.getTotalCopies()) {
            System.out.println("  ⚠  Cannot delete — some copies are still borrowed.");
            return false;
        }
        bookMap.remove(bookId.toUpperCase());
        return true;
    }

    // ── List All ──────────────────────────────────────────────────────────────
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookMap.values());
    }

    // ── Search ────────────────────────────────────────────────────────────────
    public List<Book> searchByTitle(String keyword) {
        String kw = keyword.toLowerCase();
        return bookMap.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String keyword) {
        String kw = keyword.toLowerCase();
        return bookMap.values().stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(kw))
                .collect(Collectors.toList());
    }

    public List<Book> getAvailableBooks() {
        return bookMap.values().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    // ── Borrow / Return helpers ───────────────────────────────────────────────
    public boolean decrementCopy(String bookId) {
        Book book = bookMap.get(bookId.toUpperCase());
        if (book == null || !book.isAvailable()) return false;
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        return true;
    }

    public boolean incrementCopy(String bookId) {
        Book book = bookMap.get(bookId.toUpperCase());
        if (book == null) return false;
        if (book.getAvailableCopies() < book.getTotalCopies()) {
            book.setAvailableCopies(book.getAvailableCopies() + 1);
            return true;
        }
        return false;
    }
}
