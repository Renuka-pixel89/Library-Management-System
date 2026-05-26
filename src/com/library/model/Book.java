package com.library.model;

import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bookId;
    private String title;
    private String author;
    private String genre;
    private int totalCopies;
    private int availableCopies;

    public Book(String bookId, String title, String author, String genre, int totalCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    // Getters and Setters
    public String getBookId()                     { return bookId; }
    public String getTitle()                      { return title; }
    public String getAuthor()                     { return author; }
    public String getGenre()                      { return genre; }
    public int getTotalCopies()                   { return totalCopies; }
    public int getAvailableCopies()               { return availableCopies; }

    public void setTitle(String title)            { this.title = title; }
    public void setAuthor(String author)          { this.author = author; }
    public void setGenre(String genre)            { this.genre = genre; }
    public void setTotalCopies(int totalCopies)   { this.totalCopies = totalCopies; }
    public void setAvailableCopies(int count)     { this.availableCopies = count; }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    @Override
    public String toString() {
        return String.format("| %-8s | %-30s | %-20s | %-12s | %5d | %9d |",
                bookId, title, author, genre, totalCopies, availableCopies);
    }
}
