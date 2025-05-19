package src.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.model.Library;
import src.model.User;
import src.service.UserService;
import src.util.ConsoleUtils;

public class UsersCLI {
    ConsoleUtils cUtils = new ConsoleUtils();
    Library library = new Library();
    UserService userService = new UserService();
    List<User> results = new ArrayList<>();
    List<String> mainMenuItems = Arrays.asList("Lihat Daftar Anggota", "Tambah Anggota", "Cari Anggota", "Kembali");

    public void manageUsers() {
        while (true) {
            cUtils.format_display("Manajemen Anggota", mainMenuItems);
            String input = cUtils.inputOption();

            if (input.matches("[1-4]")) {
                if (input.equals("4")) break;
                selectManageUsers(input);
            } else {
                cUtils.pauseEnter("Pilihan tidak valid. Coba lagi.");
            }
        }
    }

    private void selectManageUsers(String menu) {
        cUtils.clear_screen();
        switch (menu.trim()) {
            case "1": // Show All Users
                showUserMenu();
                break;
            
            case "2": // Add user
                addUserMenu();
                break;
            
            case "3": // Search User
                searchUserMenu();
                break;
        }
    }

    private void showUserMenu() {
        showAllUsers();
        cUtils.menu(mainMenuItems);
        selectManageUsers(cUtils.inputOption());
    }

    private void addUserMenu() {
        boolean added = userService.addUser();
        cUtils.pauseEnter(added ? "Anggota berhasil ditambahkan" : "Anggota gagal ditambahkan");
    }

    private void searchUserMenu() {
        cUtils.header("Cari Anggota");
        showAllUsers();

        do {
            String keyWord = cUtils.input("Masukan ID/Nama/E-Mail/Alamat (atau ketik 'kembali') > ");
            if (keyWord.equalsIgnoreCase("kembali")) break;

            results = userService.searchUser(keyWord);
            
            cUtils.header("Cari Anggota");
            showUsers(results);

            if (results.size() == 1) {

                int userId = results.get(0).getUserId();
                List<String> sub_menu = Arrays.asList("Ubah", "Hapus", "kembali");
                
                while (true) {
                    cUtils.menu(sub_menu);
                    String input = cUtils.inputOption();
                    if (input.matches("[1-3]")) {
                        if (input.equals("3")) break;
                        
                        manageSingleUser(input, userId);
                    } else {
                        cUtils.pauseEnter("Pilihan tidak valid. Coba lagi.");
                    }
                    
                }
            }

        } while (results.size() != 1);
    }

    private void manageSingleUser(String menu, int userId) {
        cUtils.clear_screen();
        switch (menu) {
            case "1" : // Edit
                boolean edited = userService.editUser(userId);
                cUtils.pauseEnter(edited ? "Anggota berhasil diubah" : "Anggota gagal diubah");
                break;

            case "2": // Delete
                boolean deleted = userService.deleteUser(userId);
                cUtils.pauseEnter(deleted ? "Anggota berhasil dihapus" : "Anggota gagal dihapus");
                break;
        }

        cUtils.header("Data Anggota Terbaru");
        showUserWithId(userId);
    }

    private void showUsers(List<User> displayUsers) {
        cUtils.header("Daftar Anggota");
        int width = ((library.getWidth() -33) / 2);
        if (displayUsers.isEmpty()) {
            System.out.printf("| %-2s | %-20s | %-" + width + "s | %-" + width + "s  |\n", "", "Tidak ada", "", "");
        } else {
            // Colomn
            System.out.printf("| %-2s | %-20s | %-" + width + "s | %-" + width + "s  |\n", "ID", "Username", "E-mail", "Alamat");
            System.out.println("+" + "-".repeat(library.getWidth()) + "+");
            
            for (User user : displayUsers) {
                // Rows
                System.out.printf("| %-2s | %-20s | %-" + width + "s | %-" + width + "s  |\n", user.getUserId(), user.getName(), user.getEmail(), user.getAddress());
            }
            System.out.println("+" + "-".repeat(library.getWidth()) + "+");
        }
    }

    private void showAllUsers() {
        showUsers(userService.getUsers());
    }

    private void showUserWithId(int userId) {
        List<User> results = new ArrayList<>();
        List<User> users = userService.getUsers();
        for(User user : users) {
            if (user.getUserId() == userId) {
                results.add(user);
                break;
            }
        }

        showUsers(results);
    }

}
