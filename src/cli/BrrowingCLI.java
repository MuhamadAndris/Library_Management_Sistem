package src.cli;

import java.util.Arrays;
import java.util.List;

import src.util.ConsoleUtils;

public class BrrowingCLI {
    ConsoleUtils cUtils = new ConsoleUtils();
    List<String> items = Arrays.asList("Pinjam Buku", "Kembalikan Buku", "kembali");

    public void manageBrrowing() {
        cUtils.format_display("Transaksi Peminjaman", items);
        while (true) {
            String input = cUtils.inputOption();
            if (input.equals("3")) { // Back
                break;
            }

            selectMenuBrrowing(cUtils.inputOption());
        }
    }

    public void selectMenuBrrowing(String menu) {
        switch (menu) {
            case "1": // Brrowing book
                break;
            
            case "2": // Reurn Book
                break;
        
            default:
                cUtils.clear_screen();
                cUtils.pauseEnter("Pilihan tidak tersedia");
                break;
        }
    }
}
