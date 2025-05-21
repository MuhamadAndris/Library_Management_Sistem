package src.model;
import java.time.LocalDate;

public class Loan { 
    private String loanId;          // Id transaksi pinjam/kembalikan
    private User user;              // Detail user yang berisi -> UserID, name, email, address
    private Book book;              // Detail buku yang berisi -> BookId, title, author, year
    private LocalDate borrowDate;   // Tanggal pinjam
    private LocalDate returnDate;   // Tanggal kembalikan
    private String status;          // "Borrowed", "Returned", "Late"
    private double fine;            // Denda

    // Constructor
    public Loan(String loanId, User user, Book book) {
        this.loanId = loanId;
        this.user = user;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.returnDate = borrowDate.plusDays(7); // otomatis tambah +7 hari
        this.status = "Dipinjam";
        this.fine = 0.0;
    }

    // Getters dan Setters
    public String getloanId() {
        return loanId;
    }

    public void setloanId(String loanId) {
        this.loanId = loanId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "ID : " + loanId + ", Judul Buku : " + book.getTitle() + ", Nama Anggota : " + user.getName() + ", Tanggal Pinjam : " + borrowDate + ", Tanggal Pengembalian : " + returnDate + ", Status : " + status;
    }
 }


