package com.library.util;

import com.library.service.BookService;
import com.library.service.MemberService;

public class DataSeeder {

    public static void seed(BookService books, MemberService members) {
        // Books
        books.addBook("Clean Code",                   "Robert C. Martin",  "Technology",  5);
        books.addBook("The Pragmatic Programmer",      "Andrew Hunt",       "Technology",  3);
        books.addBook("Effective Java",                "Joshua Bloch",      "Technology",  4);
        books.addBook("Design Patterns",               "Gang of Four",      "Technology",  2);
        books.addBook("Head First Java",               "Kathy Sierra",      "Technology",  6);
        books.addBook("Atomic Habits",                 "James Clear",       "Self-Help",   8);
        books.addBook("The Alchemist",                 "Paulo Coelho",      "Fiction",     10);
        books.addBook("To Kill a Mockingbird",         "Harper Lee",        "Fiction",     7);
        books.addBook("Sapiens",                       "Yuval Noah Harari", "History",     5);
        books.addBook("Rich Dad Poor Dad",             "Robert Kiyosaki",   "Finance",     6);

        // Members
        members.registerMember("Arjun Sharma",   "arjun@email.com",  "9876543210");
        members.registerMember("Priya Nair",     "priya@email.com",  "9876543211");
        members.registerMember("Ravi Kumar",     "ravi@email.com",   "9876543212");
    }
}
