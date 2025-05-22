package src.util;

public class FormUtils {
    ConsoleUtils cUtils = new ConsoleUtils();

    public String editStringField(String entiti, String label, String oldValue) {
        cUtils.clear_screen();
        System.out.println("============= UBAH DATA " + entiti + " =============");
        System.out.println("[Ketik 'batal' untuk membatalkan]\n");
        System.out.println("Ubah " + label);
        System.out.println("Sebelumnya (" + oldValue + ")");
        String input = cUtils.input("Jadi : ");

        if (input.trim().equalsIgnoreCase("batal")) return null;
        if (input.isBlank()) return oldValue;
        return input.trim();
    }

    public Integer editIntegerField(String entiti, String label, int oldValue) {
        while (true) {
            cUtils.clear_screen();
            System.out.println("============= UBAH DATA " + entiti + " =============");
            System.out.println("[Ketik 'batal' untuk membatalkan]\n");
            System.out.println("Ubah " + label);
            System.out.println("Sebelumnya (" + oldValue + ")");
            String input = cUtils.input("Jadi : ");

            if (input.trim().equalsIgnoreCase("batal")) return null;
            if (input.isBlank()) return oldValue;

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                cUtils.pauseEnter("Harap masukkan angka yang valid.");
            }
        }
    }

    public String addStringField(String entiti, String label) {
        while (true) {
            cUtils.clear_screen();
            System.out.println("============= TAMBAH " + entiti + " =============");
            System.out.println("[Ketik 'batal' untuk membatalkan]\n");
            String input = cUtils.input("Masukan " + label + " : ");

            if (input.trim().equalsIgnoreCase("batal")) return null;
            if (input.isBlank()) {
                cUtils.pauseEnter("Input tidak boleh kosong!");;
            } else {
                return input.trim();
            }
        }
    }
    
    public Integer addIntegerField(String entiti, String label) {
        while (true) {
            cUtils.clear_screen();
            System.out.println("============= TAMBAH " + entiti + " =============");
            System.out.println("[Ketik 'batal' untuk membatalkan]\n");
            String input = cUtils.input("Masukan " + label + " : ");

            if (input.trim().equalsIgnoreCase("batal")) return null;
            if (input.isBlank()) {
                cUtils.pauseEnter("Input tidak boleh kosong!");
            } else {
                try {
                    return Integer.parseInt(input.trim());
                } catch (NumberFormatException e) {
                    cUtils.pauseEnter("Harap masukan angka yang valid.");
                }
            }
        }
    }

}
