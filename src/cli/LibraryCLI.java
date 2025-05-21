package src.cli;
import java.util.Arrays;
import java.util.List;
import src.util.ConsoleUtils;
import src.model.Library;

public class LibraryCLI {
    BookCLI bookCLI = new BookCLI();
    UsersCLI usersCLI = new  UsersCLI();
    LoanCLI brrowingCLI = new LoanCLI();
    ConsoleUtils cUtils = new ConsoleUtils();
    Library moLib = new Library();
    LoanCLI borrowingCLI = new LoanCLI();
    ReportCLI reportCLI = new ReportCLI();


    public void showMainMenu() {
        List<String> items = Arrays.asList("Manajemen Anggota", "Manajemen Buku", "Transaksi Peminjaman" ,"Laporan","Keluar");
        cUtils.header(moLib.getTitle());
        cUtils.menu(items);

        selectMainMenu(cUtils.inputOption());
    }

    public void selectMainMenu(String menu) {
        switch (menu) {
            case "1":
                usersCLI.manageUsers();
                break;

            case "2":
                bookCLI.manageBooks();
                break;
            
           case "3":
                brrowingCLI.manageBorrowing();
                break;
            
            case "4": 
                reportCLI.manageReport();
                break;
            
            case "5":
                System.out.println("\nAnda telah keluar dari aplikasi. Semoga harimu menyenangkan!\n");
                System.exit(0);
                break;

            default:
                cUtils.pauseEnter("Pilihan tidak tersedia");
                showMainMenu();
                break;
        }
    }

    public void run () {
        while (true) {
            showMainMenu();
        }
    }
 
}
