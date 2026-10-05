public class Book {

    int id;
    String title;
    String author;
    String category;
    boolean available;
    int issuedToUserId;

    Book(int id, String title, String author, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = true;
        this.issuedToUserId = -1;
    }
}
