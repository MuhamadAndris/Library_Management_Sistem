package src.service;

import java.util.List;

import src.model.User;
import src.util.ConsoleUtils;

public class Brrowing {
    UserService userService = new UserService();
    BookService bookService = new BookService();
    ConsoleUtils cUtils = new ConsoleUtils();

    public void brrowingBook() {
        String input = cUtils.input("Masukan ID/Judul/Penulis/Tahun > ");
        List<User> results = userService.searchUser(input);
        System.out.println(results);
    }
}
