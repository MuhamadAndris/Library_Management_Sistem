package src.cli;

import src.util.ConsoleUtils;

public class LoginCLI {
    ConsoleUtils cUtils = new ConsoleUtils();

    public Boolean login() {
        cUtils.clear_screen();
        cUtils.header("SISTEM MANAJEMEN PERPUSTAKAAN - LOGIN PANEL");
        System.out.println();
        System.out.println(cUtils.textCenter("Silakan masukkan username dan password Anda", 100));
        System.out.println();
        
        String username = cUtils.input(" ".repeat(40) + "Username : ");
        
        String password = cUtils.input(" ".repeat(40) + "Password : ");
        
        System.out.println();
        System.out.print(cUtils.textCenter("Memverifikasi data, mohon tunggu...", 100));
        System.out.println();
        System.out.println();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(cUtils.textCenter("Terjadi kesalahan saat memproses.", 100));
        }


        if (username.equals("admin") && password.equals("adm123")) {
            return  true;
        } else {
            System.out.println(cUtils.textCenter("Login gagal! Username atau password salah.", 100));
            System.out.println(cUtils.textCenter("Tekan Enter untuk mencoba lagi...", 100));
            cUtils.input(" ".repeat(50));
            return false;
        }
    }
}