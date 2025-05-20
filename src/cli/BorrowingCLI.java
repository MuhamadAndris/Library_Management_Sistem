package src.cli;

import java.util.Arrays;
import java.util.List;

import src.model.User;
import src.service.UserService;
import src.util.ConsoleUtils;

public class BorrowingCLI {
    private final ConsoleUtils cUtils = new ConsoleUtils();
    private final List<String> mainMenuItems = Arrays.asList("Pinjam Buku", "Kembalikan Buku", "Kembali");
    private final UserService userService = new UserService();
    private final UsersCLI usersCLI = new UsersCLI();

    public void manageBorrowing() {
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
        cUtils.clear_screen();
        System.out.println("=== MENU PEMINJAMAN BUKU ===");
        System.out.println("Cari anggota dengan ID, Nama, Email, atau Alamat.");
        System.out.println("Ketik 'kembali' untuk membatalkan.\n");

        User user = searchUser();
        if (user == null) return;

        System.out.println("\nAnggota ditemukan:");
        System.out.println(user);

        // TODO: Tambahkan proses peminjaman buku di sini
        System.out.println("\n[Mock] Lanjutkan ke peminjaman buku untuk user di atas...");
        cUtils.pauseEnter("Tekan Enter untuk kembali ke menu utama.");
    }

    private void handleReturnBook() {
        cUtils.clear_screen();
        System.out.println("=== MENU PENGEMBALIAN BUKU ===");
        System.out.println("Cari anggota yang akan mengembalikan buku.");
        System.out.println("Ketik 'kembali' untuk membatalkan.\n");

        User user = searchUser();
        if (user == null) return;

        System.out.println("\nAnggota ditemukan:");
        System.out.println(user);

        // TODO: Tambahkan proses pengembalian buku di sini
        System.out.println("\n[Mock] Lanjutkan ke proses pengembalian buku...");
        cUtils.pauseEnter("Tekan Enter untuk kembali ke menu utama.");
    }

    private User searchUser() {
        while (true) {
            String input = cUtils.input("> ");
            if (input.equalsIgnoreCase("kembali")) return null;

            List<User> results = userService.searchUser(input);

            if (results.size() == 1) {
                return results.get(0);
            } else if (results.size() > 1) {
                cUtils.clear_screen();
                usersCLI.showUsers(results);
                System.out.println("\nTerdapat beberapa hasil, mohon perjelas pencarian Anda.\n");
            } else {
                cUtils.pauseEnter("Anggota tidak ditemukan");
            }
        }
    }
}
