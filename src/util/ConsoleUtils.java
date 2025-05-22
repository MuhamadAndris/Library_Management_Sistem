package src.util;
import java.util.Scanner;
import java.util.List;

public class ConsoleUtils {
    Scanner scanner = new Scanner(System.in);

    public String input() {
        return scanner.nextLine().trim();
    }
    
    public String input(String massage) {
        System.out.print(massage);
        return scanner.nextLine().trim();
    }
    
    public String inputOption() {
        System.out.print("Masukan Pilihan > ");
        return scanner.nextLine().trim();
    }

    public void clear_screen() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Gagal membersihkan layar.");
        }
    }

    public void pauseEnter(String massage) {
        clear_screen();
        System.out.print(massage  + "\n\nTekan Enter Untuk melanjutkan > ");
        input();
    }

    public String textCenter(String text, int width) {
        int padding = (width - text.length())  / 2;
        if (padding < 0) padding = 0;

        return " ".repeat(padding) + text + " ".repeat(width - padding - text.length());
    }

    public void header(String title) {
        System.out.println("+" + "-".repeat(100) + "+");
        System.out.println("|" + textCenter(title.toUpperCase(), 100) + "|");
        System.out.println("+" + "-".repeat(100) + "+");
    }

    public void menu(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            String row = String.format("| %" + String.valueOf(items.size()).length() + "s." + items.get(i), i + 1);
            System.out.println(row + " ".repeat(100 - row.length()) + " |");
        }
        System.out.println("+" + "-".repeat(100) + "+");
    }
    
    public void format_display (String title, List<String> items) {
        clear_screen();
        header(title);
        menu(items);
    }

    public boolean confirm(String massage) {
        String input = input(massage + " y/t > ");
        if (input.trim().equalsIgnoreCase("y")) {
            return true;
        } else  {
            return false;
        }
    }

    // Book
    public void columnBook() {
        System.out.printf("| %-2s | %-59s | %-15s | %-12s  |\n", "ID", "Judul", "Penulis", "Tahun Terbit");
    }

    public void columnBookIsEmpty() {
        System.out.printf("| %-2s | %-59s | %-15s | %-12s  |\n", "", "Tidak ada", "", "");
    }

    public void line() {
        System.out.println("+" + "-".repeat(100) + "+");
    }

    // User
    public void columnUser() {
        System.out.printf("| %-2s | %-20s | %-32s | %-35s |\n", "ID", "Nama", "Email", "Alamat");
    }

    public void columnUserIsEmpty() {
        System.out.printf("| %-2s | %-20s | %-32s | %-35s |\n", "", "Tidak Ada", "", "");
    }
}   