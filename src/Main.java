package src;
import src.cli.LibraryCLI;
import src.cli.LoginCLI;


public class Main {
    public static void main(String[] args) {
        LoginCLI loginCLI = new LoginCLI();

        LibraryCLI libraryCLI = new LibraryCLI();

        for (int i = 0; i < 3; i++) {
            Boolean status = loginCLI.login();
            if (status) {
                libraryCLI.run();
                break;
            }

            if (i < 3) {
                System.out.println("\nBatas percobaan Login habis, Silahkan coba lagi nanti.");
            }
        }
        
    }

}
