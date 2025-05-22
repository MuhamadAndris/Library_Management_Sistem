package src.cli;
import java.util.Arrays;
import java.util.List;
import src.util.ConsoleUtils;

public class LibraryCLI {
    BookCLI bookCLI = new BookCLI();
    UsersCLI usersCLI = new  UsersCLI();
    LoanCLI brrowingCLI = new LoanCLI();
    ConsoleUtils cUtils = new ConsoleUtils();
    LoanCLI borrowingCLI = new LoanCLI();
    ReportCLI reportCLI = new ReportCLI();


    public void showMainMenu() {
        List<String> items = Arrays.asList("Manajemen Anggota", "Manajemen Buku", "Transaksi Peminjaman" ,"Laporan","Keluar");
        
        while (true) {
            cUtils.format_display("SISTEM MANAJEMEN PERPUSTAKAAN", items);
            String input = cUtils.inputOption();
            if (input.matches("[1-5]")) {

                if (input.equals("5")) {
                    System.out.println("\nAnda telah keluar dari aplikasi. Semoga harimu menyenangkan!\n");
                    System.exit(0);
                }
                
                selectMainMenu(input);
            } else {
                cUtils.pauseEnter("Pilihan tidak tersedia");
            }
        }

    }

    public void selectMainMenu(String menu) {
        switch (menu) {
            case "1" -> usersCLI.manageUsers();
            case "2" -> bookCLI.handleBookManagement();
            case "3" ->  brrowingCLI.manageLoan();
            case "4" ->  reportCLI.manageReport();
        }
    }

    public void run () {
        while (true) {
            showMainMenu();
        }
    }
 
}
