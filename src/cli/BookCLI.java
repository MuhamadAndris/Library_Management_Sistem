package src.cli;

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
                bookService.showAllBooks();
                cUtils.menu(items);
                selectManageBooks(cUtils.inputOption());
                break;

            case "2": // Add book
                bookService.addBook();
                cUtils.menu(items);
                selectManageBooks(cUtils.inputOption());
                break;

            case "3": // Search Book
                searchBook();
                break;

            default:
                cUtils.clear_screen();
                cUtils.pauseEnter("Pilihan tidak tersedia");
                break;
        }
    }

    public void manageSiggleBook(String menu) {
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

            default:
                cUtils.pauseEnter("Pilihan tidak tersedia!");
                break;
        }
    }

    private void searchBook() {
        cUtils.header("Cari Buku");
        bookService.showBook(bookService.getBooks());
        List<Book> results = bookService.searchBook(cUtils.input("Masukan ID/Judul/Penulis/Tahun > "));
        cUtils.header("Cari Buku");
        bookService.showBook(results);
        if (results.size() == 1) {
            List<String> sub_menu = Arrays.asList("Ubah", "Hapus", "Kembali");
            cUtils.menu(sub_menu);
            while (true) {
                String input = cUtils.inputOption();
                if (input.equals("3")) {  // Back
                    break;
                }
                manageSiggleBook(input);
            }
        } else {
            cUtils.menu(items);
            selectManageBooks(cUtils.inputOption());
        }
    }
}
