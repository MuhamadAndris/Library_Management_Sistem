package src.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import src.model.Library;
import src.service.BookService;
import src.util.ConsoleUtils;
import src.model.Book;

public class BookCLI {
    Library library = new Library();
    BookService bookService = new BookService();
    ConsoleUtils cUtils = new ConsoleUtils();
    List<String> items = Arrays.asList("Lihat Daftar Buku", "Tambah Buku", "Cari Buku", "Kembali");
    List<Book> results = new ArrayList<>();

    public void manageBooks() {
        while (true) {
            cUtils.format_display("Manajemen Buku", items);
            String input = cUtils.inputOption();

            if (input.equals("4")) { // Back
                break;
            }

            selectManageBooks(input);
        }
    }

    public void selectManageBooks(String menu) {
        switch (menu.trim()) {
            case "1": // Show All Books
                showBookMenu();
                break;

            case "2": // Add book
                addBookMenu();
                break;

            case "3": // Search Book
                searchBookMenu();
                break;

            default:
                cUtils.clear_screen();
                cUtils.pauseEnter("Pilihan tidak tersedia");
                break;
        }
    }

    private void showBookMenu() {
        showAllBooks();
        cUtils.menu(items);
        selectManageBooks(cUtils.inputOption());
    }
    
    private void addBookMenu() {
        cUtils.clear_screen();
        boolean added = bookService.addBook();
        cUtils.pauseEnter(added ? "Buku berhasil ditambahkan" : "Buku Gagal ditambahkan");
    }

    private void searchBookMenu() {
        cUtils.header("Cari Buku");
        showAllBooks();

        do {
            String keyWord = cUtils.input("Masukan ID/Judul/Penulis/Tahun (atau ketik 'kembali') > ");
            if (keyWord.equalsIgnoreCase("kembali")) break;

            results = bookService.searchBook(keyWord);
            
            cUtils.header("Cari Buku");
            showBook(results);

            if (results.size() == 1) {

                int bookId = results.get(0).getBookId();
                List<String> sub_menu = Arrays.asList("Ubah", "Hapus", "kembali");
                
                while (true) {
                    cUtils.menu(sub_menu);
                    String input = cUtils.inputOption();
                    if (input.matches("[1-3]")) {
                        if (input.equals("3")) break;
                        
                        manageSiggleBook(input, bookId);
                    } else {
                        cUtils.pauseEnter("Pilihan tidak valid. Coba lagi.");
                    }
                    
                }
            }

        } while (results.size() != 1);
    }

    public void manageSiggleBook(String menu, int bookId) {
        cUtils.clear_screen();
        switch (menu) {
            case "1" : // Edit
                boolean edited = bookService.editBook();
                cUtils.pauseEnter(edited ? "Data Buku berhasil diubah" : "Buku gagal diubah");
                selectManageBooks("1");
                break;

            case "2": // Delete
                boolean deleted = bookService.deleteBook();
                cUtils.pauseEnter(deleted ? "Buku berhasil dihapus" : "Buku gagal dihapus");
                selectManageBooks("1");
                break;
            }

        cUtils.header("Data Anggota Terbaru");
        showUserWithId(bookId);
    }

    public void showBook(List<Book> displayBooks) {
        cUtils.header("Daftar Buku");
        if (displayBooks.isEmpty()) {
            System.out.printf("| %-2s | %-" + (library.getWidth()-41) + "s | %-15s | %-12s  |\n", "", "Tidak ada", "", "");
            System.out.println("+" + "-".repeat(library.getWidth()) + "+");
        } else {
            // Colomn
            System.out.printf("| %-2s | %-" + (library.getWidth()-41) + "s | %-15s | %-12s  |\n", "ID", "Judul", "Penulis", "Tahun Terbit");
            System.out.println("+" + "-".repeat(library.getWidth()) + "+");
            
            for (Book book : displayBooks) {
                System.out.printf("| %-2s | %-" + (library.getWidth()-41) + "s | %-15s | %-12s  |\n", book.getBookId(), book.getTitle(), book.getAuthor(), book.getYear());
            }
            System.out.println("+" + "-".repeat(library.getWidth()) + "+");
        }
    }

    public void showAllBooks() {
        showBook(bookService.getBooks());
    }

    private void showUserWithId(int bookId) {
        List<Book> results = new ArrayList<>();
        List<Book> books = bookService.getBooks();
        for(Book book : books) {
            if (book.getBookId() == bookId) {
                results.add(book);
                break;
            }
        }

        showBook(results);
    }

}