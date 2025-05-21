package src.service;

import java.util.ArrayList;

import src.model.Loan;
import java.util.List;;

public class LoanService {
    // Note : Bang rozi, Nevi, tomo untuk tempat menyimpan transaksi peminjaman bukunya ada di sini ya, jadi sudah saling terhubung.
    List<Loan> loanList = new ArrayList<>();        // tempat menyimpan transaksi peminjaman/pengembalian buku
    
    BookService bookService = new BookService();
    UserService userService = new UserService();
    
    public LoanService() {
        loanList.add(new Loan("1", userService.getUsers().get(0), bookService.getBooks().get(0)));
        loanList.add(new Loan("2", userService.getUsers().get(2), bookService.getBooks().get(0)));
        loanList.add(new Loan("3", userService.getUsers().get(3), bookService.getBooks().get(1)));
        // note bang tomo ini contoh ketika kita mau menambahkan pinjaman buku. cuman nanti bedanya itu prosesnya pakai inputan, tapi untuk proses input oputpuna jangan di sini ya, dia untuk input out put itu hatus ada id LoanCLI
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public boolean addLoan() { // tomo -  ini function untuk meminjam buku, sialhkan dikembangkan
        return true;
    }

    public boolean returnLoan() { // nepi - ini function untuk mengemablikan buku yang dipinjam, sialhkan dikembangkan
        return true;
    }

    // Note bang tomo, nepi, kalo mau liat atau coba untuk proes peminjaman atau pengembaliannya berhasil bisa dijalankan untuk programnya di bagian menu. 4. Laporan -> 1. Tampikan semua buku yang dipinjam
}
