package src.cli;

import java.util.Arrays;
import java.util.List;

import src.service.LoanService;
import src.util.ConsoleUtils;

public class LoanCLI {
    ConsoleUtils cUtils = new ConsoleUtils();
    List<String> mainMenuItems = Arrays.asList("Pinjam Buku", "Kembalikan Buku", "Kembali");
    LoanService loanService = new LoanService();



    public void manageLoan() {
        while (true) {
            cUtils.format_display("Menu Transaksi Peminjaman", mainMenuItems);
            String input = cUtils.inputOption();

            switch (input) {
                case "1":
                    handleBorrowBook();
                    break;
                case "2":
                    handleReturnBook();
                    break;
                case "3":
                    return;
                default:
                    cUtils.pauseEnter("Pilihan tidak tersedia. Tekan Enter untuk melanjutkan...");
            }
        }
    }

    private void handleBorrowBook() {
        // tomo untuk pinjamannya itu seperti ini ya prosessnya
        // 1. cari user yang mau pinjam buku
        // 2. cari buku yang mau di pinjam
        // 3. jalankan fucntion/metode dibawah ini

        loanService.addLoan();
    }

    private void handleReturnBook() {
        // nepi untuk pengembaliannya itu seperti ini ya prosessnya
        // 1. cari user yang mau mengembalikan buku
        // 2. cari buku yang mau di kembalikan
        // 3. jalankan fucntion/metode dibawah ini
        // 4. prosesnya tinggal ganti status dari loan ya, yang tadi nya "dipinjam" jadi "dikembalikan"

        loanService.returnLoan();
    }
}
