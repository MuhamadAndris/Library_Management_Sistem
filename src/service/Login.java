package src.service;

import java.util.HashMap;
import java.util.Map;

import src.util.ConsoleUtils;

public class Login {
    ConsoleUtils cUtils = new ConsoleUtils();
    String username = "admin";
    String password = "adm123";
    Map<String, String> acount = new HashMap<>();

    public Boolean checkLogin(String usname, String pass) {
        acount.put("admin", "adm123");
        acount.put("user", "user123");
        if (usname.equals(username) && pass.equals(password)) {
            return true;
        } else {
            cUtils.clear_screen();
            return false;
        }
    }
}