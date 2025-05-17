package src.cli;

import java.util.Arrays;
import java.util.List;

import src.model.Library;
import src.model.User;
import src.service.UserService;
import src.util.ConsoleUtils;

public class UsersCLI {
    ConsoleUtils cUtils = new ConsoleUtils();
    Library moLibrary = new Library();
    UserService userService = new UserService();
    List<String> items = Arrays.asList("Lihat Daftar Anggota", "Tambah Anggota", "Cari Anggota", "Kembali");

    public void manageUsers() {
        while (true) {
            cUtils.format_display("Manajemen Anggota", items);
            String input = cUtils.inputOption();

            if (input.equals("4")) { // kembali
                break;
            }

            selectManageUsers(input);
        }
    }

    public void selectManageUsers(String menu) {
        switch (menu.trim()) {
            case "1": // Show All Users
                cUtils.header("Daftar Anggota");
                userService.showUsers();
                cUtils.menu(items);
                selectManageUsers(cUtils.inputOption());
                break;
            
            case "2": // Add user
                boolean added = userService.addUser();
                cUtils.clear_screen();
                cUtils.pauseEnter(added ? "Anggota berhasil ditambahkan" : "Anggota gagall ditambahkan");
                selectManageUsers("1");
                break;
            
            case "3": // Search User
                searchUserMenu();
                break;

            default:
                cUtils.clear_screen();
                cUtils.pauseEnter("Pilihan tidak tersedia");
                break;
        }
    }

    public void manageSinggleUser(String menu) {
        cUtils.clear_screen();
        switch (menu) {
            case "1" : // Edit
                boolean edited = userService.editUser();
                cUtils.pauseEnter(edited ? "Buku berhasil diubah" : "Buku gagal diubah");
                break;

            case "2": // Delete
                boolean deleted = userService.deleteUser();
                cUtils.pauseEnter(deleted ? "Buku berhasil dihapus" : "Buku gagal dihapus");
                selectManageUsers("1");
                break;

            default:
                cUtils.pauseEnter("Pilihan tidak tersedia");
                break;
        }
    }

    private void searchUserMenu() {
        cUtils.header("Cari Anggota");
        userService.showUsers();
        List<User> results = userService.searchUser(cUtils.input("Masukan ID/Nama/Alamat > "));
        cUtils.header("Cari Anggota");
        userService.showUsers(results);
        if (results.size() == 1) {
            List<String> sub_menu = Arrays.asList("Ubah", "Hapus", "kembali");
            cUtils.menu(sub_menu);

            while (true) {
                String input = cUtils.inputOption();
                if (input.equals("3")) { // back
                    break;
                }

                manageSinggleUser(input);
            }
        } else {
            cUtils.menu(items);
            selectManageUsers(cUtils.inputOption());
        }
    }
}
