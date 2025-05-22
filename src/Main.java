package src;
import src.cli.LibraryCLI;
import src.cli.LoginCLI;
import src.util.ConsoleUtils;


public class Main {
    public static void main(String[] args) {
        LoginCLI loginCLI = new LoginCLI();
        ConsoleUtils cUtils = new ConsoleUtils();

        LibraryCLI libraryCLI = new LibraryCLI();

        for (int i = 0; i < 3; i++) {
            Boolean status = loginCLI.login();
            if (status) {
                libraryCLI.run();
                break;
            }

            if (i < 3) {
                System.out.println();
                System.out.println(cUtils.textCenter("Batas percobaan Login habis, Silahkan coba lagi nanti.", 100));
                System.out.println();
            }
        }
        
    }

}
