package src.service;
import src.model.Library;
import src.model.User;
import src.util.ConsoleUtils;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;
    Library library = new Library();
    ConsoleUtils cUtils = new ConsoleUtils();

    public UserService() {
        users = new ArrayList<>();

        users.add(new User(1, "Muhamad Andris", "muhamad335andris@gmail.com", "Kp. Wangoon, Bogor"));
        users.add(new User(2, "Tomo Nugraha", "tomo.nugraha@example.com", "Jl. Mawar No. 12, Bogor"));
        users.add(new User(3, "Aqil Ramadhan", "aqil.ramadhan@example.com", "Perum Cileungsi Indah, Bogor"));
        users.add(new User(4, "Andre Hartanto", "andre.hartanto@example.com", "Kp. Bojonggede, Bogor"));
        users.add(new User(5, "Helmi Rahman", "helmi.rahman@example.com", "Jl. Cibinong Raya, Bogor"));
    }

    public List<User> getUsers () {
        return users;
    }

    public List<User> searchUser(String keyword) {
        List<User> results = new ArrayList<>();

        try {
            // Search with Id
            Integer key = Integer.parseInt(keyword);
            for (User user : users) {
                if (user.getUserId() == key) {
                    results.add(user);
                }
            }

        } catch (NumberFormatException e) {
            // Search with Name
            for (User user : users) {
                if (user.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    results.add(user);
                }
            }            
            
            // Search with E-mail
            if (results.isEmpty()) {
                for (User user : users) {
                    if (user.getEmail().toLowerCase().contains(keyword.toLowerCase())) {
                        results.add(user);
                    }
                }
            }            

            // Search with Address
            if (results.isEmpty()) {
                // Search with E-mail
                for (User user : users) {
                    if (user.getAddress().toLowerCase().contains(keyword.toLowerCase())) {
                        results.add(user);
                    }
                }
            }            
        }

        return results;
    }

    public boolean addUser() { //  Bg Rozi
        return true;
    }

    public boolean editUser(int userId) { // Bg Tomo
        return true;
    }
    
    public boolean deleteUser(int userId) { // Nepi
        return true;
    }

}
