package src.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.model.User;
import src.service.UserService;
import src.util.ConsoleUtils;
import src.util.FormUtils;

public class UsersCLI {
    ConsoleUtils cUtils = new ConsoleUtils();
    UserService userService = new UserService();
    FormUtils fUtils = new FormUtils();

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
            case "1" -> showAllUsersScreen();
            case "2" -> handleAddUser();
            case "3" -> searchUserAndManage();
        }
    }

    private void showAllUsersScreen() {
        cUtils.header("DAFTAR ANGGOTA");
        showUsers(userService.getUsers());
        cUtils.input("\n-> Tekan Enter untuk kembali...");
    }

    private void handleAddUser() {
        List<User> users = userService.getUsers();
        int userId = users.isEmpty() ? 1 : users.get(users.size() -1).getUserId() + 1;

        String name = fUtils.addStringField("ANGGOTA", "Nama");
        if (name == null) return;

        String email = fUtils.addStringField("ANGGOTA", "Email");
        if (email == null) return;

        String address = fUtils.addStringField("ANGGOTA", "Alamat");
        if (address == null) return;

        boolean added = userService.addUser(userId, name, email, address);
        cUtils.pauseEnter(added ? "Anggota berhasil ditambahkan" : "Anggota gagal ditambahkan");

        showAllUsersScreen();
    }

    private void searchUserAndManage() {
        List<User> results = new ArrayList<>();
        cUtils.clear_screen();
        cUtils.header("Cari Anggota");
        showUsers(userService.getUsers());

        do {
            String keyword = cUtils.input("Masukan ID/Nama/E-Mail/Alamat (atau ketik 'kembali') > ");
            if (keyword.equalsIgnoreCase("kembali")) break;

            results = userService.searchUser(keyword);

            cUtils.clear_screen();
            cUtils.header("HASIL PENCARIAN ANGGOTA");
            showUsers(results);

            if (results.size() == 1) {
                User selectedUser = results.get(0);
                handleUserAction(selectedUser);
            }

        } while (results.size() != 1);
    }

    private void handleUserAction(User selectedUser) {
        List<String> subMenu = Arrays.asList("Ubah", "Hapus", "Kembali");

        while (true) {
            cUtils.clear_screen();
            cUtils.header("HASIL PENCARIAN ANGGOTA");
            showUserById(selectedUser.getUserId());
            cUtils.menu(subMenu);
            
            String input = cUtils.inputOption();
            if (input.matches("[1-3]")) {
                if (input.equals("3")) break;

                switch (input) {
                    case "1" -> {
                        handleEditUser(selectedUser);
                        cUtils.clear_screen();
                        cUtils.header("Data Anggota Terbaru");
                        showUserById(selectedUser.getUserId());
                    }
                    case "2" -> {
                        handleDeleteUser(selectedUser);
                        break;
                    }
                }
            } else {
                cUtils.pauseEnter("Pilihan tidak valid. Coba lagi.");
            }
        }
    }

    private void handleEditUser(User user) {
        int userId = user.getUserId();

        String newName = fUtils.editStringField("ANGGOTA", "Nama", user.getName());
        if (newName == null) return;

        String newEmail = fUtils.editStringField("ANGGOTA", "Email", user.getEmail());
        if (newEmail == null) return;

        String newAddress = fUtils.editStringField("ANGGOTA", "Alamat", user.getAddress());
        if (newAddress == null) return;

        boolean edited = userService.editUser(userId, newName, newEmail, newAddress);
        cUtils.pauseEnter(edited ? "Anggota berhasil diubah" : "Anggota gagal diubah");
    }

    private void handleDeleteUser(User user) {
        boolean confirm = cUtils.confirm("Yakin ingin menghapus anggota ini?");
        boolean deleted = confirm && userService.deleteUser(user);
        cUtils.pauseEnter(deleted ? "Anggota berhasil dihapus" : "Anggota gagal dihapus");
    }

    private void showUsers(List<User> displayUsers) {
        if (displayUsers.isEmpty()) {
            cUtils.line();
            cUtils.columnUserIsEmpty();
            cUtils.line();
        } else {
            cUtils.columnUser();
            cUtils.line();

            for (User user : displayUsers) {
                System.out.print(user);
            }

            cUtils.line();
        }
    }

    private void showUserById(int userId) {
        List<User> result = new ArrayList<>();
        for (User user : userService.getUsers()) {
            if (user.getUserId() == userId) {
                result.add(user);
                break;
            }
        }
        showUsers(result);
    }
}
