package com.library.main;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;
import com.library.service.BookService;
import com.library.service.MemberService;
import com.library.service.TransactionService;
import com.library.util.ConsoleUtil;
import com.library.util.DataSeeder;

import java.util.List;

public class LibraryApp {

    private static BookService        bookService;
    private static MemberService      memberService;
    private static TransactionService transactionService;

    public static void main(String[] args) {
        bookService        = new BookService();
        memberService      = new MemberService();
        transactionService = new TransactionService(bookService, memberService);

        DataSeeder.seed(bookService, memberService);

        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║     LIBRARY MANAGEMENT SYSTEM  v1.0         ║");
        System.out.println("║     Built with Java  |  Console Application  ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = ConsoleUtil.input("\n  Enter your choice: ");
            switch (choice) {
                case "1"  -> bookMenu();
                case "2"  -> memberMenu();
                case "3"  -> transactionMenu();
                case "4"  -> reportsMenu();
                case "0"  -> { running = false; System.out.println("\n  Goodbye! 👋\n"); }
                default   -> System.out.println("  ⚠  Invalid option.");
            }
        }
    }

    // ── Main Menu ─────────────────────────────────────────────────────────────
    private static void printMainMenu() {
        System.out.println("\n┌──────────────────────────────┐");
        System.out.println("│          MAIN MENU           │");
        System.out.println("├──────────────────────────────┤");
        System.out.println("│  1. Book Management          │");
        System.out.println("│  2. Member Management        │");
        System.out.println("│  3. Borrow / Return          │");
        System.out.println("│  4. Reports                  │");
        System.out.println("│  0. Exit                     │");
        System.out.println("└──────────────────────────────┘");
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  BOOK MENU
    // ══════════════════════════════════════════════════════════════════════════
    private static void bookMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌──────────────────────────────┐");
            System.out.println("│        BOOK MANAGEMENT       │");
            System.out.println("├──────────────────────────────┤");
            System.out.println("│  1. Add Book                 │");
            System.out.println("│  2. View All Books           │");
            System.out.println("│  3. Search by Title          │");
            System.out.println("│  4. Search by Author         │");
            System.out.println("│  5. Update Book              │");
            System.out.println("│  6. Delete Book              │");
            System.out.println("│  0. Back                     │");
            System.out.println("└──────────────────────────────┘");

            String ch = ConsoleUtil.input("\n  Enter your choice: ");
            switch (ch) {
                case "1" -> addBook();
                case "2" -> listBooks(bookService.getAllBooks());
                case "3" -> {
                    String kw = ConsoleUtil.input("  Search title: ");
                    listBooks(bookService.searchByTitle(kw));
                }
                case "4" -> {
                    String kw = ConsoleUtil.input("  Search author: ");
                    listBooks(bookService.searchByAuthor(kw));
                }
                case "5" -> updateBook();
                case "6" -> deleteBook();
                case "0" -> back = true;
                default  -> System.out.println("  ⚠  Invalid option.");
            }
        }
    }

    private static void addBook() {
        ConsoleUtil.printHeader("Add New Book");
        String title  = ConsoleUtil.input("  Title  : ");
        String author = ConsoleUtil.input("  Author : ");
        String genre  = ConsoleUtil.input("  Genre  : ");
        int    copies = ConsoleUtil.inputInt("  Copies : ");
        Book book = bookService.addBook(title, author, genre, copies);
        System.out.println("  ✔  Book added! ID: " + book.getBookId());
    }

    private static void updateBook() {
        ConsoleUtil.printHeader("Update Book");
        String id = ConsoleUtil.input("  Book ID to update: ");
        bookService.getBook(id).ifPresentOrElse(b -> {
            System.out.println("  (Leave blank to keep current value)");
            String title  = ConsoleUtil.input("  New Title  [" + b.getTitle()  + "]: ");
            String author = ConsoleUtil.input("  New Author [" + b.getAuthor() + "]: ");
            String genre  = ConsoleUtil.input("  New Genre  [" + b.getGenre()  + "]: ");
            bookService.updateBook(id, title, author, genre);
            System.out.println("  ✔  Book updated successfully.");
        }, () -> System.out.println("  ✗  Book not found."));
    }

    private static void deleteBook() {
        ConsoleUtil.printHeader("Delete Book");
        String id = ConsoleUtil.input("  Book ID to delete: ");
        if (bookService.deleteBook(id)) System.out.println("  ✔  Book deleted.");
        else System.out.println("  ✗  Deletion failed.");
    }

    private static void listBooks(List<Book> books) {
        ConsoleUtil.printHeader("Book List");
        if (books.isEmpty()) { System.out.println("  No books found."); return; }
        System.out.printf("| %-8s | %-30s | %-20s | %-12s | Total | Available |%n",
                "ID", "Title", "Author", "Genre");
        ConsoleUtil.printDivider();
        books.forEach(System.out::println);
        ConsoleUtil.printDivider();
        System.out.println("  Total: " + books.size() + " book(s).");
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  MEMBER MENU
    // ══════════════════════════════════════════════════════════════════════════
    private static void memberMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌──────────────────────────────┐");
            System.out.println("│      MEMBER MANAGEMENT       │");
            System.out.println("├──────────────────────────────┤");
            System.out.println("│  1. Register Member          │");
            System.out.println("│  2. View All Members         │");
            System.out.println("│  3. Search by Name           │");
            System.out.println("│  4. Update Member            │");
            System.out.println("│  5. Remove Member            │");
            System.out.println("│  0. Back                     │");
            System.out.println("└──────────────────────────────┘");

            String ch = ConsoleUtil.input("\n  Enter your choice: ");
            switch (ch) {
                case "1" -> registerMember();
                case "2" -> listMembers(memberService.getAllMembers());
                case "3" -> {
                    String kw = ConsoleUtil.input("  Search name: ");
                    listMembers(memberService.searchByName(kw));
                }
                case "4" -> updateMember();
                case "5" -> deleteMember();
                case "0" -> back = true;
                default  -> System.out.println("  ⚠  Invalid option.");
            }
        }
    }

    private static void registerMember() {
        ConsoleUtil.printHeader("Register Member");
        String name  = ConsoleUtil.input("  Name  : ");
        String email = ConsoleUtil.input("  Email : ");
        String phone = ConsoleUtil.input("  Phone : ");
        Member m = memberService.registerMember(name, email, phone);
        System.out.println("  ✔  Member registered! ID: " + m.getMemberId());
    }

    private static void updateMember() {
        ConsoleUtil.printHeader("Update Member");
        String id = ConsoleUtil.input("  Member ID: ");
        memberService.getMember(id).ifPresentOrElse(m -> {
            System.out.println("  (Leave blank to keep current value)");
            String name  = ConsoleUtil.input("  New Name  [" + m.getName()  + "]: ");
            String email = ConsoleUtil.input("  New Email [" + m.getEmail() + "]: ");
            String phone = ConsoleUtil.input("  New Phone [" + m.getPhone() + "]: ");
            memberService.updateMember(id, name, email, phone);
            System.out.println("  ✔  Member updated.");
        }, () -> System.out.println("  ✗  Member not found."));
    }

    private static void deleteMember() {
        ConsoleUtil.printHeader("Remove Member");
        String id = ConsoleUtil.input("  Member ID: ");
        if (memberService.deleteMember(id)) System.out.println("  ✔  Member removed.");
        else System.out.println("  ✗  Removal failed.");
    }

    private static void listMembers(List<Member> members) {
        ConsoleUtil.printHeader("Member List");
        if (members.isEmpty()) { System.out.println("  No members found."); return; }
        System.out.printf("| %-10s | %-20s | %-25s | %-12s | Borrowed |%n",
                "ID", "Name", "Email", "Phone");
        ConsoleUtil.printDivider();
        members.forEach(System.out::println);
        ConsoleUtil.printDivider();
        System.out.println("  Total: " + members.size() + " member(s).");
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  TRANSACTION MENU
    // ══════════════════════════════════════════════════════════════════════════
    private static void transactionMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌──────────────────────────────┐");
            System.out.println("│       BORROW / RETURN        │");
            System.out.println("├──────────────────────────────┤");
            System.out.println("│  1. Borrow a Book            │");
            System.out.println("│  2. Return a Book            │");
            System.out.println("│  3. View All Transactions    │");
            System.out.println("│  4. Transactions by Member   │");
            System.out.println("│  0. Back                     │");
            System.out.println("└──────────────────────────────┘");

            String ch = ConsoleUtil.input("\n  Enter your choice: ");
            switch (ch) {
                case "1" -> {
                    String mId = ConsoleUtil.input("  Member ID: ");
                    String bId = ConsoleUtil.input("  Book ID  : ");
                    transactionService.borrowBook(mId, bId);
                }
                case "2" -> {
                    String mId = ConsoleUtil.input("  Member ID: ");
                    String bId = ConsoleUtil.input("  Book ID  : ");
                    transactionService.returnBook(mId, bId);
                }
                case "3" -> listTransactions(transactionService.getAllTransactions());
                case "4" -> {
                    String mId = ConsoleUtil.input("  Member ID: ");
                    listTransactions(transactionService.getTransactionsByMember(mId));
                }
                case "0" -> back = true;
                default  -> System.out.println("  ⚠  Invalid option.");
            }
        }
    }

    private static void listTransactions(List<Transaction> txs) {
        ConsoleUtil.printHeader("Transaction History");
        if (txs.isEmpty()) { System.out.println("  No transactions found."); return; }
        System.out.printf("| %-12s | %-10s | %-8s | %-8s | Date       |%n",
                "Txn ID", "Member", "Book", "Type");
        ConsoleUtil.printDivider();
        txs.forEach(System.out::println);
        ConsoleUtil.printDivider();
        System.out.println("  Total: " + txs.size() + " transaction(s).");
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  REPORTS MENU
    // ══════════════════════════════════════════════════════════════════════════
    private static void reportsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n┌──────────────────────────────┐");
            System.out.println("│           REPORTS            │");
            System.out.println("├──────────────────────────────┤");
            System.out.println("│  1. Available Books          │");
            System.out.println("│  2. Library Summary          │");
            System.out.println("│  0. Back                     │");
            System.out.println("└──────────────────────────────┘");

            String ch = ConsoleUtil.input("\n  Enter your choice: ");
            switch (ch) {
                case "1" -> listBooks(bookService.getAvailableBooks());
                case "2" -> printSummary();
                case "0" -> back = true;
                default  -> System.out.println("  ⚠  Invalid option.");
            }
        }
    }

    private static void printSummary() {
        ConsoleUtil.printHeader("Library Summary");
        List<Book>        allBooks   = bookService.getAllBooks();
        List<Member>      allMembers = memberService.getAllMembers();
        List<Transaction> allTx      = transactionService.getAllTransactions();

        long totalCopies     = allBooks.stream().mapToLong(Book::getTotalCopies).sum();
        long availableCopies = allBooks.stream().mapToLong(Book::getAvailableCopies).sum();
        long borrowedCopies  = totalCopies - availableCopies;

        System.out.printf("  %-25s : %d%n", "Total Books (Titles)",   allBooks.size());
        System.out.printf("  %-25s : %d%n", "Total Copies",           totalCopies);
        System.out.printf("  %-25s : %d%n", "Available Copies",       availableCopies);
        System.out.printf("  %-25s : %d%n", "Currently Borrowed",     borrowedCopies);
        System.out.printf("  %-25s : %d%n", "Registered Members",     allMembers.size());
        System.out.printf("  %-25s : %d%n", "Total Transactions",     allTx.size());
        ConsoleUtil.printDivider();
    }
}
