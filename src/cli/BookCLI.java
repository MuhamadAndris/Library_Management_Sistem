package src.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import src.service.BookService;
import src.util.ConsoleUtils;
import src.util.FormUtils;
import src.model.Book;
public class BookCLI {
    BookService bookService = new BookService();
    ConsoleUtils cUtils = new ConsoleUtils();
    FormUtils fUtils = new FormUtils();
    List<String> items = Arrays.asList("Lihat Daftar Buku", "Tambah Buku", "Cari Buku", "Kembali");

    public void handleBookManagement() {
        while (true) {
            cUtils.format_display("Manajemen Buku", items);
            String input = cUtils.inputOption();

            if (input.matches("[1-4]")) {
                if (input.equals("4")) break;
    
                handleBookMenuOption(input);

            } else {
                cUtils.pauseEnter("Pilihan tidak tersedia");
            }
        }
    }

    public void handleBookMenuOption(String menu) {
        switch (menu.trim()) {
            case "1" -> displayAllBookScreen();
            case "2" -> handleAddBook();           
            case "3" -> searchBookAndManage();   
        }
    }

    private void displayAllBookScreen() {
        cUtils.clear_screen();
        cUtils.header("DAFTAR BUKU");
        showAllBooks();
        cUtils.input("\n-> Tekan Enter untuk kembali...");
    }

    private void searchBookAndManage() {
        List<Book> results = new ArrayList<>();
        cUtils.clear_screen();
        cUtils.header("Cari Buku");
        showAllBooks();

        do {
            String keyWord = cUtils.input("Masukan ID/Judul/Penulis/Tahun (atau ketik 'kembali') > ");
            if (keyWord.equalsIgnoreCase("kembali")) break;

            results = bookService.searchBook(keyWord);
            
            cUtils.clear_screen();
            cUtils.header("BUKU HASIL PENCARIAN");
            showBook(results);

            if (results.size() == 1) {
                Book selectedBook = results.get(0);

                handleBookAction(selectedBook);
            }

        } while (results.size() != 1);
    }

    public void handleBookAction(Book selectedBook) {
        List<String> sub_menu = Arrays.asList("Ubah", "Hapus", "kembali");
        
        while (true) {
            cUtils.clear_screen();
            cUtils.header("BUKU HASIL PENCARIAN");
            showBookById(selectedBook.getBookId());
            cUtils.menu(sub_menu);
            
            String input = cUtils.inputOption();
            if (input.matches("[1-3]")) {
                if (input.equals("3")) break;
                
                switch (input) {
                    case "1" :
                        handleEditBook(selectedBook);
                        cUtils.header("Data Buku Terbaru");
                        showBookById(selectedBook.getBookId());
                        break;
                    case "2" : 
                        handleDeleteBook(selectedBook);
                        break;
                    }
            } else {
                cUtils.pauseEnter("Pilihan tidak valid. Coba lagi.");
            }
            
        }
    }

    public void handleAddBook() {
        List<Book> books = bookService.getBooks();
        int bookId = books.isEmpty() ? 1 : books.get(books.size() -1).getBookId() + 1;

        String title = fUtils.addStringField("BUKU", "Judul");
        if (title == null) return;
        
        String author = fUtils.addStringField("BUKU", "Penulis");
        if (author == null) return;

        Integer year = fUtils.addIntegerField("BUKU", "Tahun Rilis");
        if (year == null) return;

        boolean added = bookService.addBook(bookId, title, author, year);
        cUtils.pauseEnter(added ? "Buku Berhasil ditambahkan" : "Buku Gagal ditambahkan");

        displayAllBookScreen();
    }

    public void handleEditBook(Book book) {
        int bookId = book.getBookId();

        String newTitle = fUtils.editStringField("BUKU", "Judul Buku", book.getTitle());
        if (newTitle == null) return;

        String newAuthor = fUtils.editStringField("BUKU", "Nama Penulis Buku", book.getAuthor());
        if (newAuthor == null) return;

        Integer newYear = fUtils.editIntegerField("BUKU", "Tahun Rilis Buku", book.getYear());
        if (newYear == null) return;

        boolean edited = bookService.editBook(bookId, newTitle, newAuthor, newYear);
        cUtils.pauseEnter(edited ? "Data Buku berhasil diubah" : "Buku gagal diubah");
    }

    public void handleDeleteBook(Book selectedBook) {
        boolean confirm = cUtils.confirm("Yakin mau menghapus Buku ini?");
        boolean deleted = confirm && bookService.deleteBook(selectedBook);
        cUtils.pauseEnter(deleted ? "Buku berhasil dihapus" : "Buku gagal dihapus");
    }

    // Metode data
    public void showBook(List<Book> displayBooks) {
        if (displayBooks.isEmpty()) {
            cUtils.columnBookIsEmpty();
            cUtils.line();
        } else {
            // Colomn
            cUtils.columnBook();
            cUtils.line();
            
            for (Book book : displayBooks) {
                System.out.print(book);
            }
            cUtils.line();
        }
    }

    public void showAllBooks() {
        showBook(bookService.getBooks());
    }

    public void showBookById(int bookId) {
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