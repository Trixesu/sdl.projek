package sdl.projek;

public class Main {
    public static void main(String[] args) {
        DaftarPenerima daftar = new DaftarPenerima();

        // Data demo
        daftar.tambahPenerima(new PenerimaBansos("P001", "Siti Aminah", "Jakarta",
                "Sembako", 5, "Aktif"));
        daftar.tambahPenerima(new PenerimaBansos("P002", "Budi Santoso", "Bandung",
                "Uang Tunai", 1000000, "Aktif"));
        daftar.tambahPenerima(new PenerimaBansos("P003", "Rina Marlina", "Surabaya",
                "Sembako", 3, "Nonaktif"));

        System.out.println("=== Data Penerima Bansos ===");
        daftar.tampilkanSemua();

        System.out.println("=== Cari Penerima ID P002 ===");
        PenerimaBansos p = daftar.cariPenerima("P002");
        if (p != null) {
            p.tampilData();
        } else {
            System.out.println("Data tidak ditemukan.");
        }

        System.out.println("=== Update Data Penerima P003 ===");
        boolean berhasilUpdate = daftar.updatePenerima("P003", "Rina Marlina", "Surabaya",
                "Uang Tunai", 500000, "Aktif");
        System.out.println("Update berhasil? " + berhasilUpdate);

        System.out.println("=== Hapus Data Penerima P001 ===");
        boolean berhasilHapus = daftar.hapusPenerima("P001");
        System.out.println("Hapus berhasil? " + berhasilHapus);

        System.out.println("=== Data Penerima Setelah Update dan Hapus ===");
        daftar.tampilkanSemua();
    }
}