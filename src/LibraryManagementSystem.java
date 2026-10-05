import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Book> books = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();

        while (true) {

            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Search by Title");
            System.out.println("5. Search by Author");
            System.out.println("6. Issue Book");
            System.out.println("7. Return Book");
            System.out.println("8. Delete Book");
            System.out.println("9. Update Book");
            System.out.println("10. Count Books");
            System.out.println("11. Add User");
            System.out.println("12. View Users");
            System.out.println("13. Search User");
            System.out.println("14. Search User by Name");
            System.out.println("15. Delete User");
            System.out.println("16. View Issued Books");
            System.out.println("17. Library Statistics");
            System.out.println("18. Search by Category");
            System.out.println("19. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Book ID: ");
int id = sc.nextInt();
sc.nextLine();

if (id <= 0) {
    System.out.println("Book ID must be greater than 0.");
    break;
}

boolean idExists = false;

for (Book b : books) {
    if (b.id == id) {
        idExists = true;
        break;
    }
}

if (idExists) {
    System.out.println("Book ID already exists.");
    break;
}

                    System.out.print("Enter Book Title: ");
String title = sc.nextLine();

if (title.trim().isEmpty()) {
    System.out.println("Book title cannot be empty.");
    break;
}

System.out.print("Enter Author: ");
String author = sc.nextLine();

if (author.trim().isEmpty()) {
    System.out.println("Author name cannot be empty.");
    break;
}

System.out.print("Enter Category: ");
String category = sc.nextLine();

if (category.trim().isEmpty()) {
    System.out.println("Category cannot be empty.");
    break;
}

                    Book book = new Book(id, title, author, category);

                    books.add(book);
                    if (DBConnection.addBook(id, title, author, category)) {
                         System.out.println("Book added successfully!");
}
                    break;

                case 2:
                    DBConnection.viewBooks();

                    if (books.isEmpty()) {

                        System.out.println("No books available.");

                    } else {

                        System.out.println("\n===== BOOK LIST =====");

                        for (Book b : books) {

                            System.out.println("ID: " + b.id);
                            System.out.println("Title: " + b.title);
                            System.out.println("Author: " + b.author);
                            System.out.println("Category: " + b.category);
                            System.out.println("Status: " +
                                    (b.available ? "Available" : "Issued"));
                                    if (!b.available) {

    System.out.println("Issued To User ID: " + b.issuedToUserId);

    for (User u : users) {

        if (u.id == b.issuedToUserId) {

            System.out.println("Issued To: " + u.name);
            break;
        }
    }
}
                            System.out.println("-------------------------");
                        }
                    }

                    break;

                case 3:
    System.out.print("Enter Book ID to search: ");
    int searchBookId = sc.nextInt();
    sc.nextLine();

    DBConnection.searchBook(searchBookId);

    break;

                case 4:

    System.out.print("Enter Book Title: ");
    String searchTitle = sc.nextLine();

    DBConnection.searchBookByTitle(searchTitle);

    break;

                case 5:

    System.out.print("Enter Book Author: ");
    String searchAuthor = sc.nextLine();

    DBConnection.searchBookByAuthor(searchAuthor);

    break;
                case 6:

    System.out.print("Enter Book ID: ");
int issueBookId = sc.nextInt();
sc.nextLine();

if (issueBookId <= 0) {
    System.out.println("Book ID must be greater than 0.");
    break;
}

System.out.print("Enter User ID: ");
int issueUserId = sc.nextInt();
sc.nextLine();

if (issueUserId <= 0) {
    System.out.println("User ID must be greater than 0.");
    break;
}

    boolean bookFound = false;
    boolean userFound = false;

    // Check whether user exists
    for (User u : users) {

        if (u.id == issueUserId) {
            userFound = true;
            break;
        }
    }

    if (!userFound) {
        System.out.println("User not found.");
        break;
    }

    // Find and issue the book
    for (Book b : books) {

        if (b.id == issueBookId) {

            bookFound = true;

            if (b.available) {

                b.available = false;
                b.issuedToUserId = issueUserId;
                DBConnection.issueBook(issueBookId, issueUserId);

                System.out.println("Book issued successfully!");

            } else {

                System.out.println("Book is already issued.");
            }

            break;
        }
    }

    if (!bookFound) {
        System.out.println("Book not found.");
    }

    break;

                case 7:

    System.out.print("Enter Book ID to return: ");
    int returnId = sc.nextInt();

    boolean returned = false;

    for (Book b : books) {

        if (b.id == returnId) {

            if (!b.available) {
                b.available = true;
                b.issuedToUserId = -1;
                DBConnection.returnBook(returnId);
                System.out.println("Book returned successfully!");
            } else {
                System.out.println("Book is already available.");
            }

            returned = true;
            break;
        }
    }

    if (!returned) {
        System.out.println("Book not found.");
    }

    break;
   
               case 8:

    System.out.print("Enter Book ID to delete: ");
    int deleteId = sc.nextInt();

    boolean deleted = false;

    for (int i = 0; i < books.size(); i++) {

        if (books.get(i).id == deleteId) {

            books.remove(i);
            DBConnection.deleteBook(deleteId);

            System.out.println("Book deleted successfully!");

            deleted = true;
            break;
        }
    }

    if (!deleted) {
        System.out.println("Book not found.");
    }

    break;

               case 9:

    System.out.print("Enter Book ID to update: ");
    int updateId = sc.nextInt();
    sc.nextLine();

    boolean updated = false;

    for (Book b : books) {

        if (b.id == updateId) {

            System.out.print("Enter New Title: ");
            b.title = sc.nextLine();

            System.out.print("Enter New Author: ");
            b.author = sc.nextLine();

            System.out.print("Enter New Category: ");
            b.category = sc.nextLine();
            DBConnection.updateBook(updateId, b.title, b.author, b.category);

            System.out.println("Book updated successfully!");

            updated = true;
            break;
        }
    }

    if (!updated) {
        System.out.println("Book not found.");
    }

    break;

               case 10:

    System.out.println("Total number of books: " + books.size());

    break;

            case 11:

    System.out.print("Enter User ID: ");
int userId = sc.nextInt();
sc.nextLine();

if (userId <= 0) {
    System.out.println("User ID must be greater than 0.");
    break;
}

boolean userIdExists = false;

for (User u : users) {

    if (u.id == userId) {
        userIdExists = true;
        break;
    }
}

if (userIdExists) {
    System.out.println("User ID already exists.");
    break;
}

System.out.print("Enter User Name: ");
String userName = sc.nextLine();
if (userName.trim().isEmpty()) {
    System.out.println("User name cannot be empty.");
    break;
}

    User user = new User(userId, userName);

    users.add(user);
    if (DBConnection.addUser(userId, userName)) {
    System.out.println("User added successfully!");
}

    break;

            case 12:

    DBConnection.viewUsers();

    break;

               case 13:

    System.out.print("Enter User ID to search: ");
    int searchUserId = sc.nextInt();
    sc.nextLine();

    DBConnection.searchUser(searchUserId);

    break;

               case 14:

    System.out.print("Enter User ID to delete: ");
    int deleteUserId = sc.nextInt();

    boolean userExists = false;
    boolean hasBook = false;

    // Check if user exists
    for (User u : users) {

        if (u.id == deleteUserId) {
            userExists = true;
            break;
        }
    }

    if (!userExists) {
        System.out.println("User not found.");
        break;
    }

    // Check if user has an issued book
    for (Book b : books) {

        if (b.issuedToUserId == deleteUserId) {
            hasBook = true;
            break;
        }
    }

    if (hasBook) {

        System.out.println("User cannot be deleted.");
        System.out.println("User has an issued book. Return the book first.");

    } else {

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).id == deleteUserId) {

                users.remove(i);
                DBConnection.deleteUser(deleteUserId);

                System.out.println("User deleted successfully!");
                break;
            }
        }
    }

    break;

               case 15:

    System.out.print("Enter User Name: ");
    String searchName = sc.nextLine();

    DBConnection.searchUserByName(searchName);

    break;

               case 16:

    DBConnection.viewIssuedBooks();

    break;

               case 17:

    DBConnection.libraryStatistics();

    break;

               case 18:

    System.out.print("Enter Book Category: ");
    String searchCategory = sc.nextLine();

    DBConnection.searchBookByCategory(searchCategory);

    break;
               case 19:

                    System.out.println("Thank you for using Library Management System.");

                    sc.close();
                    return;

                default:
    System.out.println("Invalid choice. Please enter a number from 1 to 19.");
            }
        }
    }
}