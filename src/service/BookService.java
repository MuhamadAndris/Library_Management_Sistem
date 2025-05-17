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

    public void showBook(List<Book> books) {
        if (books.isEmpty()) {
            System.out.printf("| %-2s | %-" + (library.getWidth()-41) + "s | %-15s | %-12s  |\n", "", "Tidak ada", "", "");
            System.out.println("+" + "-".repeat(library.getWidth()) + "+");
        } else {
            // Colomn
            System.out.printf("| %-2s | %-" + (library.getWidth()-41) + "s | %-15s | %-12s  |\n", "ID", "Judul", "Penulis", "Tahun Terbit");
            System.out.println("+" + "-".repeat(library.getWidth()) + "+");
            
            for (Book book : books) {
                System.out.printf("| %-2s | %-" + (library.getWidth()-41) + "s | %-15s | %-12s  |\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getYear());
            }
            System.out.println("+" + "-".repeat(library.getWidth()) + "+");
        }
    }

    public void showAllBooks() {
        cUtils.header("Daftar Buku");
        showBook(getBooks());
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

    public void addBook() {
        Integer bookId;
        Integer year;
        
        while (true) {
            cUtils.clear_screen();
            try {
                bookId = Integer.parseInt(cUtils.input("Masukan ID : "));
                for (Book book : books) {
                    if (book.getBookId() == bookId) {
                        cUtils.pauseEnter("ID yang dimasukan sudah ada, harap masukan ID lain...");
                        addBook();
                    }
                }
                break;
            } catch (NumberFormatException e) {
                cUtils.pauseEnter("Harap masuakn angka/Nomor...");
            }
        }

        cUtils.clear_screen();
        String title = cUtils.input("Masukan judul Buku  : ");
        cUtils.clear_screen();
        String author = cUtils.input("Masukan Penulis: ");
        
        while (true) {
            cUtils.clear_screen();
            try {
                year = Integer.parseInt(cUtils.input("Masukan Tahun Rilis   : "));
                break;
            } catch (NumberFormatException e) {
                cUtils.pauseEnter("Harap masuakn angka/Nomor...");
            }
        }

        Book newBok = new Book(bookId, title, author, year);
        books.add(newBok);
        
        cUtils.header("New Books Added");
        showBook(searchBook(Integer.toString(bookId)));

            
    }

    public boolean editBook() {
        return true;
    }

    public boolean deleteBook() {
        return true;
    }

}
