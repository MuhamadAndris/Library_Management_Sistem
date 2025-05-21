package src.cli;

import src.model.Loan;
import src.service.LoanService;
import src.util.ConsoleUtils;

import java.util.Arrays;
import java.util.List;

public class ReportCLI {
    LoanService loanService = new LoanService();
    List<Loan> loanList = loanService.getLoanList();
    ConsoleUtils cUtils = new ConsoleUtils();
    List<String> mainMenuItems = Arrays.asList("Tampilkan Semua Buku yang dipinjam", "Telat menggembalikan", "Kembali");

    public void manageReport() {
        while (true) {
            cUtils.format_display("Laporan Transaksi Peminjaman", mainMenuItems);
            String input = cUtils.inputOption();

            if (input.matches("[1-3]")) {
                if (input.equals("3")) break;

                selectManageRiport(input);
            } else {
                cUtils.pauseEnter("Pilihan tidak tersedia.");
            }
        }
    }

    public void selectManageRiport(String menu) {
        switch (menu) {
            case "1" -> handleShowAllLoan();
            case "2" -> handleLateReturn();
        }
    }


    public void handleShowAllLoan() {
        cUtils.clear_screen();
        for (Loan loan : loanList) {
            System.out.println(loan);
        }
        cUtils.input("Tekan Enter untuk Kembali...");

        // ini contohnya bang rozi, sialhkan dikembangkan
    }

    public void handleLateReturn() {
        // sialhkan bang roji
    }
}
