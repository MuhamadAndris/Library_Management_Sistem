package src.cli;

import src.service.Login;
import src.util.ConsoleUtils;

public class LoginCLI {
    ConsoleUtils cUtils = new ConsoleUtils();
    Login servLogin = new Login();

    public Boolean login() {
        cUtils.clear_screen();
        System.out.println("=== Login ===");

        String username = cUtils.input("Username: ");
        String password = cUtils.input("Password: ");


        if (username.isEmpty() || password.isEmpty()) {
            cUtils.pauseEnter("Username dan password tidak boleh kosong");
            return false;
        } else {
            if (servLogin.checkLogin(username, password)) {
                return true;
            } else {
                cUtils.pauseEnter("Username atau password salah");
                return false;
            }
        }
    }
}