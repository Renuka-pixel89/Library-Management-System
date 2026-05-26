# 📚 Library Management System

A clean, well-structured **Java console application** demonstrating core OOP concepts — ideal for fresher resumes.

---

## 🛠 Tech Stack
- **Language**: Java 11+
- **Concepts**: OOP, Collections (HashMap, ArrayList, List), Streams, Optional, Enums, Serializable, Generics
- **Architecture**: Layered (Model → Service → Main)
- **Build**: No external dependencies (plain Java)

---

## 📁 Project Structure

```
LibraryManagementSystem/
├── src/
│   └── com/library/
│       ├── model/
│       │   ├── Book.java          # Entity class with Serializable
│       │   ├── Member.java        # Entity class with borrowing logic
│       │   └── Transaction.java   # Entity with Enum (BORROW/RETURN)
│       ├── service/
│       │   ├── BookService.java   # CRUD + Search using Streams
│       │   ├── MemberService.java # Member CRUD operations
│       │   └── TransactionService.java  # Borrow/Return business logic
│       ├── util/
│       │   ├── ConsoleUtil.java   # Input/output helper
│       │   └── DataSeeder.java    # Pre-loaded sample data
│       └── main/
│           └── LibraryApp.java    # Entry point with full menu system
└── README.md
```

---

## ✅ Features
- **Book Management** — Add, View, Search (by title/author), Update, Delete
- **Member Management** — Register, View, Search, Update, Remove
- **Borrow & Return** — With validations (availability, duplicate borrow check)
- **Reports** — Available books, Library summary stats
- **Transaction History** — All transactions or filtered by member
- **Sample Data** — Pre-loaded 10 books and 3 members on startup

---

## ▶ How to Run

### Compile
```bash
cd LibraryManagementSystem
javac -d out $(find src -name "*.java")
```

### Run
```bash
java -cp out com.library.main.LibraryApp
```

---

## 💡 Java Concepts Demonstrated

| Concept | Where Used |
|---|---|
| OOP (Encapsulation, Abstraction) | All Model + Service classes |
| Collections (Map, List) | BookService, MemberService |
| Java Streams + Lambda | Search methods, Reports |
| Optional | getMember(), getBook() |
| Enum | Transaction.Type |
| Serializable | All Model classes |
| Layered Architecture | Model → Service → Main |
| Method Overloading | ConsoleUtil |

---

## 📝 Resume Points
- Built a **console-based Library Management System** in Java
- Implemented **CRUD operations** for Books and Members using HashMap
- Designed **layered architecture** separating model, service, and UI layers
- Used **Java Streams and Lambda** for search and filter operations
- Handled **business logic** for book borrowing/returning with validations
