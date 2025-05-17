package src.util;
import java.util.Scanner;
import java.util.List;

import src.model.Library;

public class ConsoleUtils {
    Scanner scanner = new Scanner(System.in);
    Library lib = new Library();

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
        System.out.print(massage  + "\n\nTekan Enter Untuk melanjutkan > ");
        input();
    }

    public String textCenter(String text, int width) {
        int padding = (width - text.length())  / 2;
        if (padding < 0) padding = 0;

        return " ".repeat(padding) + text + " ".repeat(width - padding - text.length());
    }

    public void header(String title) {
        clear_screen();
        System.out.println("+" + "-".repeat(lib.getWidth()) + "+");
        System.out.println("|" + textCenter(title, lib.getWidth()) + "|");
        System.out.println("+" + "-".repeat(lib.getWidth()) + "+");
    }

    public void menu(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            String row = String.format("| %" + String.valueOf(items.size()).length() + "s." + items.get(i), i + 1);
            System.out.println(row + " ".repeat(lib.getWidth() - row.length()) + " |");
        }
        System.out.println("+" + "-".repeat(lib.getWidth()) + "+");
    }
    
    public void format_display (String title, List<String> items) {
        header(title);
        menu(items);
    }
}   