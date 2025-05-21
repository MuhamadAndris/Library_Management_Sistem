package src.service;
import src.model.Book;
import src.model.Library;
import src.util.ConsoleUtils;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private List<Book> books = new ArrayList<>();
    Library library = new Library();
    ConsoleUtils cUtils = new ConsoleUtils();

    public BookService() {
        books = new ArrayList<>();

        books.add(new Book(1, "Java Programming", "John Doe", 2004));
        books.add(new Book(2, "Python for Beginners", "Jane Smith", 2010));
        books.add(new Book(3, "Web Development with HTML/CSS", "Mark Johnson", 2015));
        books.add(new Book(4, "Data Structures and Algorithms", "Emily Brown", 2018));
        books.add(new Book(5, "Database Management Systems", "David Wilson", 2021));
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Book> searchBook(String keyWord) {
        List<Book> results = new ArrayList<>();

        try {
            // search with Books ID
            int key = Integer.parseInt(keyWord);
            for (Book book : books) {
                if (book.getBookId() == key) {
                    results.add(book);
                }
            }

            // Search with Year
            if (results.isEmpty()) {
                for(Book book : books) {
                    if (book.getYear() == key) {
                        results.add(book);
                    }
                }
            }

        } catch (NumberFormatException e) {
            // search with title
            for (Book book : books) {
                if (book.getTitle().toLowerCase().contains(keyWord.toLowerCase())) {
                    results.add(book);
                }
            }

            // Search with autor
            if (results.isEmpty()) {
                for (Book book : books) {
                    if (book.getAuthor().toLowerCase().contains(keyWord.toLowerCase())) {
                        results.add(book);
                    }
                }
            }
        }

        return results;
    }

    public boolean addBook() { // Bang Rozi
        return true; 
    }

    public boolean editBook() { // Tomo
        return true;
    }

    public boolean deleteBook() { // Nepi
        return true;
    }

}
