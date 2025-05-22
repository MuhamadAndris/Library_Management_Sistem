package src.model;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private int year;

    public Book(int bookId, String title, String author, int year) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // Getter dan setter
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String value) {
        this.author = value;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("| %-2s | %-59s | %-15s | %-12s  |\n", bookId, title, author, year);
    }
}
