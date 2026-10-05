# Library Management System

## 📚 Project Overview

The Library Management System is a Java-based console application integrated with MySQL. It helps manage books and users in a library and supports common library operations such as adding, searching, updating, deleting, issuing, and returning books.

## 🛠️ Technologies Used

* Java
* MySQL
* JDBC
* VS Code
* MySQL Connector/J

## ✨ Features

### Book Management

* Add a new book
* View all books
* Search for a book
* Update book details
* Delete a book

### User Management

* Add a new user
* View all users
* Search for a user
* Update user details
* Delete a user

### Library Operations

* Issue a book to a user
* Return an issued book
* View issued books
* Search and manage library records

## 🗄️ Database

The application uses **MySQL** to store and manage library data.

Database name:

```text
library_db
```

The Java application connects to MySQL using **JDBC**.

## 📁 Project Structure

```text
LibraryManagementSystem/
│
├── src/
│   ├── Book.java
│   ├── User.java
│   ├── DBConnection.java
│   └── LibraryManagementSystem.java
│
├── lib/
│   └── MySQL Connector/J
│
└── .vscode/
```

## ▶️ How to Run

1. Install Java JDK.
2. Install MySQL Server and MySQL Workbench.
3. Create the `library_db` database in MySQL.
4. Configure the MySQL username and password in `DBConnection.java`.
5. Make sure MySQL Connector/J is added to the project.
6. Compile and run `LibraryManagementSystem.java`.
7. Use the console menu to perform library operations.

## 🎯 Learning Outcomes

This project helped in understanding:

* Core Java and Object-Oriented Programming
* Java classes and objects
* JDBC database connectivity
* SQL and MySQL
* CRUD operations
* Exception handling
* Console-based application development

## 👩‍💻 Author

**Gayathri**
