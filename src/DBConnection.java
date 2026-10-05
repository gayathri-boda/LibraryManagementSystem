import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/library_db";
        String username = "root";
        String password = "root";

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully!");
            return con;

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }
    public static boolean addUser(int id, String name) {

    String sql = "INSERT INTO users (id, name) VALUES (?, ?)";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        ps.setString(2, name);

        ps.executeUpdate();

        System.out.println("User saved to database!");
    return true;

    } catch (Exception e) {
        System.out.println("Failed to save user to database.");
        e.printStackTrace();
        return false;
    }
}
public static boolean addBook(int id, String title, String author, String category) {

    String sql = "INSERT INTO books (id, title, author, category, available) VALUES (?, ?, ?, ?, ?)";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        ps.setString(2, title);
        ps.setString(3, author);
        ps.setString(4, category);
        ps.setBoolean(5, true);

        ps.executeUpdate();

        System.out.println("Book saved to database!");
    return true;

    } catch (Exception e) {
        System.out.println("Failed to save book to database.");
        e.printStackTrace();
        return false;
    }
}
public static void deleteUser(int id) {

    String sql = "DELETE FROM users WHERE id = ?";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);

        ps.executeUpdate();

        System.out.println("User deleted from database!");

    } catch (Exception e) {
        System.out.println("Failed to delete user from database.");
        e.printStackTrace();
    }
}
public static void deleteBook(int id) {

    String sql = "DELETE FROM books WHERE id = ?";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);

        ps.executeUpdate();

        System.out.println("Book deleted from database!");

    } catch (Exception e) {
        System.out.println("Failed to delete book from database.");
        e.printStackTrace();
    }
}
public static void issueBook(int bookId, int userId) {

    String sql = "UPDATE books SET available = FALSE, issued_to_user_id = ? WHERE id = ?";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, userId);
        ps.setInt(2, bookId);

        ps.executeUpdate();

        System.out.println("Book issue details saved to database!");

    } catch (Exception e) {
        System.out.println("Failed to save issue details.");
        e.printStackTrace();
    }
}
public static void returnBook(int bookId) {

    String sql = "UPDATE books SET available = TRUE, issued_to_user_id = NULL WHERE id = ?";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, bookId);

        ps.executeUpdate();

        System.out.println("Book return details saved to database!");

    } catch (Exception e) {
        System.out.println("Failed to save return details.");
        e.printStackTrace();
    }
}
public static void updateBook(int id, String title, String author, String category) {

    String sql = "UPDATE books SET title = ?, author = ?, category = ? WHERE id = ?";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, title);
        ps.setString(2, author);
        ps.setString(3, category);
        ps.setInt(4, id);

        ps.executeUpdate();

        System.out.println("Book updated in database!");

    } catch (Exception e) {
        System.out.println("Failed to update book in database.");
        e.printStackTrace();
    }
}
public static void viewBooks() {

    String sql = "SELECT * FROM books";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql);
         java.sql.ResultSet rs = ps.executeQuery()) {

        System.out.println("\n--- Books from Database ---");

        while (rs.next()) {
            System.out.println("Book ID: " + rs.getInt("id"));
            System.out.println("Title: " + rs.getString("title"));
            System.out.println("Author: " + rs.getString("author"));
            System.out.println("Category: " + rs.getString("category"));
            System.out.println("Available: " + rs.getBoolean("available"));
            System.out.println("--------------------------");
        }

    } catch (Exception e) {
        System.out.println("Failed to retrieve books from database.");
        e.printStackTrace();
    }
}
public static void viewUsers() {

    String sql = "SELECT * FROM users";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql);
         java.sql.ResultSet rs = ps.executeQuery()) {

        System.out.println("\n--- Users from Database ---");

        while (rs.next()) {
            System.out.println("User ID: " + rs.getInt("id"));
            System.out.println("User Name: " + rs.getString("name"));
            System.out.println("--------------------------");
        }

    } catch (Exception e) {
        System.out.println("Failed to retrieve users from database.");
        e.printStackTrace();
    }
}
public static void searchUser(int id) {

    String sql = "SELECT * FROM users WHERE id = ?";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);

        java.sql.ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("\nUser Found!");
            System.out.println("User ID: " + rs.getInt("id"));
            System.out.println("User Name: " + rs.getString("name"));
        } else {
            System.out.println("User not found.");
        }

    } catch (Exception e) {
        System.out.println("Failed to search user.");
        e.printStackTrace();
    }
}
public static void searchUserByName(String name) {

    String sql = "SELECT * FROM users WHERE name = ?";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, name);

        java.sql.ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("\nUser Found!");
            System.out.println("User ID: " + rs.getInt("id"));
            System.out.println("User Name: " + rs.getString("name"));
        } else {
            System.out.println("User not found.");
        }

    } catch (Exception e) {
        System.out.println("Failed to search user by name.");
        e.printStackTrace();
    }
}
public static void searchBook(int id) {

    String sql = "SELECT * FROM books WHERE id = ?";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);

        java.sql.ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("\nBook Found!");
            System.out.println("Book ID: " + rs.getInt("id"));
            System.out.println("Title: " + rs.getString("title"));
            System.out.println("Author: " + rs.getString("author"));
            System.out.println("Category: " + rs.getString("category"));
            System.out.println("Available: " + rs.getBoolean("available"));
        } else {
            System.out.println("Book not found.");
        }

    } catch (Exception e) {
        System.out.println("Failed to search book.");
        e.printStackTrace();
    }
}
public static void searchBookByTitle(String title) {

    String sql = "SELECT * FROM books WHERE title = ?";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, title);

        java.sql.ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("\nBook Found!");
            System.out.println("Book ID: " + rs.getInt("id"));
            System.out.println("Title: " + rs.getString("title"));
            System.out.println("Author: " + rs.getString("author"));
            System.out.println("Category: " + rs.getString("category"));
            System.out.println("Available: " + rs.getBoolean("available"));
        } else {
            System.out.println("Book not found.");
        }

    } catch (Exception e) {
        System.out.println("Failed to search book by title.");
        e.printStackTrace();
    }
}
public static void searchBookByAuthor(String author) {

    String sql = "SELECT * FROM books WHERE author = ?";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, author);

        java.sql.ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("\nBook Found!");
            System.out.println("Book ID: " + rs.getInt("id"));
            System.out.println("Title: " + rs.getString("title"));
            System.out.println("Author: " + rs.getString("author"));
            System.out.println("Category: " + rs.getString("category"));
            System.out.println("Available: " + rs.getBoolean("available"));
        } else {
            System.out.println("Book not found.");
        }

    } catch (Exception e) {
        System.out.println("Failed to search book by author.");
        e.printStackTrace();
    }
}
public static void searchBookByCategory(String category) {

    String sql = "SELECT * FROM books WHERE category = ?";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, category);

        java.sql.ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("\nBook Found!");
            System.out.println("Book ID: " + rs.getInt("id"));
            System.out.println("Title: " + rs.getString("title"));
            System.out.println("Author: " + rs.getString("author"));
            System.out.println("Category: " + rs.getString("category"));
            System.out.println("Available: " + rs.getBoolean("available"));
        } else {
            System.out.println("Book not found.");
        }

    } catch (Exception e) {
        System.out.println("Failed to search book by category.");
        e.printStackTrace();
    }
}
public static void viewIssuedBooks() {

    String sql = "SELECT * FROM books WHERE available = FALSE";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql);
         java.sql.ResultSet rs = ps.executeQuery()) {

        System.out.println("\n--- Issued Books ---");

        while (rs.next()) {
            System.out.println("Book ID: " + rs.getInt("id"));
            System.out.println("Title: " + rs.getString("title"));
            System.out.println("Author: " + rs.getString("author"));
            System.out.println("Category: " + rs.getString("category"));
            System.out.println("Issued To User ID: " + rs.getInt("issued_to_user_id"));
            System.out.println("--------------------------");
        }

    } catch (Exception e) {
        System.out.println("Failed to retrieve issued books.");
        e.printStackTrace();
    }
}
public static void libraryStatistics() {

    String sql = "SELECT COUNT(*) AS total, " +
                 "SUM(CASE WHEN available = TRUE THEN 1 ELSE 0 END) AS available, " +
                 "SUM(CASE WHEN available = FALSE THEN 1 ELSE 0 END) AS issued " +
                 "FROM books";

    try (Connection con = getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql);
         java.sql.ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            System.out.println("\n--- Library Statistics ---");
            System.out.println("Total Books: " + rs.getInt("total"));
            System.out.println("Available Books: " + rs.getInt("available"));
            System.out.println("Issued Books: " + rs.getInt("issued"));
        }

    } catch (Exception e) {
        System.out.println("Failed to retrieve library statistics.");
        e.printStackTrace();
    }
}

}