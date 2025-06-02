import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DaftarPenerima daftar = new DaftarPenerima(100);

        // Tambah data awal
        daftar.tambah(new PenerimaBansos("5315031911150001", "TITI KUSMIATI", "KOLONG", "Bantuan Langsun Tunai", true));
        daftar.tambah(new PenerimaBansos("5315032910090008", "WAHYUDI", "PONGTOPAK", "Bantuan Langsun Tunai", false));
        daftar.tambah(new PenerimaBansos("5315030509080004", "SITI HALIMA ", "WATU LENDO", "Bantuan pangan Non Tunai", true));

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                tampilkanMenu();
                String pilihan = scanner.nextLine().trim();

                try {
                    switch (pilihan) {
                        case "1" -> tambahPenerima(scanner, daftar);
                        case "2" -> hapusPenerima(scanner, daftar);
                        case "3" -> updateStatusPenerima(scanner, daftar);
                        case "4" -> cariPenerima(scanner, daftar);
                        case "5" -> daftar.tampilkanSemua();
                        case "6" -> {
                            if (cekKosong(daftar)) break;
                            daftar.sortByJenisBansos();
                            daftar.tampilkanSemua();
                        }
                        case "7" -> {
                            if (cekKosong(daftar)) break;
                            daftar.sortByKartuKeluarga();
                            daftar.tampilkanSemua();
                        }
                        case "8" -> {
                            if (cekKosong(daftar)) break;
                            daftar.sortByStatusPenerimaan();
                            daftar.tampilkanSemua();
                        }
                        case "9" -> {
                            running = false;
                            System.out.println("Terima kasih, program selesai.");
                        }
                        default -> System.out.println("Pilihan tidak valid, coba lagi.");
                    }
                } catch (Exception e) {
                    System.out.println("Terjadi kesalahan: " + e.getMessage());
                    System.out.println("Silakan coba lagi.");
                }
            }
        }
    }

    private static void tampilkanMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Tambah Penerima Bansos");
        System.out.println("2. Hapus Penerima Bansos");
        System.out.println("3. Update Status Penerimaan Bansos");
        System.out.println("4. Cari Penerima Bansos berdasarkan No. KK");
        System.out.println("5. Tampilkan Semua Penerima Bansos");
        System.out.println("6. Sorting berdasarkan Jenis Bansos");
        System.out.println("7. Sorting berdasarkan Kartu Keluarga");
        System.out.println("8. Sorting berdasarkan Status Penerimaan Bansos");
        System.out.println("9. Keluar");
        System.out.print("Pilih menu (1-9) : ");
    }

    private static boolean cekKosong(DaftarPenerima daftar) {
        if (daftar.isEmpty()) {
            System.out.println("Data penerima kosong.");
            return true;
        }
        return false;
    }

    private static void tambahPenerima(Scanner scanner, DaftarPenerima daftar) {
        if (daftar.isFull()) {
            System.out.println("Data penerima sudah penuh. Tidak bisa menambah data baru.");
            return;
        }

        String kk = inputKK(scanner);
        daftar.sortByKartuKeluarga();
        if (daftar.searchByKK(kk) != null) {
            System.out.println("Nomor Kartu Keluarga sudah terdaftar. Tidak bisa menambah data duplikat.");
            return;
        }

        String nama = inputNama(scanner);
        String alamat = inputAlamat(scanner);
        String jenis = inputJenisBansos(scanner);
        Boolean aktif = inputStatus(scanner);

        daftar.tambah(new PenerimaBansos(kk, nama, alamat, jenis, aktif));
    }

    private static void hapusPenerima(Scanner scanner, DaftarPenerima daftar) {
        if (cekKosong(daftar)) return;

        daftar.tampilkanSemua();
        System.out.print("Masukkan Nomor Kartu Keluarga yang ingin dihapus : ");
        String kkHapus = scanner.nextLine().trim();

        if (kkHapus.isEmpty()) {
            System.out.println("Input Nomor Kartu Keluarga tidak boleh kosong.");
            return;
        }

        daftar.hapus(kkHapus);
    }

    private static void updateStatusPenerima(Scanner scanner, DaftarPenerima daftar) {
        if (cekKosong(daftar)) return;

        daftar.tampilkanSemua();
        System.out.print("Masukkan Nomor Kartu Keluarga penerima yang ingin diupdate status penerimaannya: ");
        String kkUpdate = scanner.nextLine().trim();

        if (kkUpdate.isEmpty()) {
            System.out.println("Input Nomor Kartu Keluarga tidak boleh kosong.");
            return;
        }

        daftar.sortByKartuKeluarga();
        PenerimaBansos penerima = daftar.searchByKK(kkUpdate);
        if (penerima == null) {
            System.out.println("Penerima dengan No. Kartu Keluarga " + kkUpdate + " tidak ditemukan.");
            return;
        }

        System.out.println("Status penerimaan bansos saat ini : " + (penerima.isAktif() ? "Sudah menerima" : "Belum menerima"));
        Boolean statusBaru = null;
        while (statusBaru == null) {
            System.out.print("Update status penerimaan bansos (Sudah/Belum) : ");
            String inputStatus = scanner.nextLine().trim().toLowerCase();
            switch (inputStatus) {
                case "sudah" -> statusBaru = true;
                case "belum" -> statusBaru = false;
                default -> System.out.println("Status tidak valid. Masukkan 'Sudah' atau 'Belum'.");
            }
        }
        daftar.updateStatusPenerimaan(kkUpdate, statusBaru ? "Sudah" : "Belum");
    }

    private static void cariPenerima(Scanner scanner, DaftarPenerima daftar) {
        if (cekKosong(daftar)) return;

        System.out.print("Masukkan Nomor Kartu Keluarga yang dicari\t: ");
        String kkCari = scanner.nextLine().trim();

        if (kkCari.isEmpty()) {
            System.out.println("Input Nomor Kartu Keluarga tidak boleh kosong.");
            return;
        }

        daftar.sortByKartuKeluarga();
        PenerimaBansos hasil = daftar.searchByKK(kkCari);
        if (hasil != null) {
            System.out.println("Ditemukan : " + hasil);
        } else {
            System.out.println("Penerima dengan No. Kartu Keluarga " + kkCari + " tidak ditemukan.");
        }
    }

    // Input helper methods with validation
    private static String inputKK(Scanner scanner) {
        while (true) {
            System.out.print("Masukkan Nomor Kartu Keluarga\t: ");
            String kk = scanner.nextLine().trim();
            if (kk.isEmpty()) {
                System.out.println("Nomor Kartu Keluarga tidak boleh kosong.");
                continue;
            }
            if (kk.length() > 16) {
                System.out.println("Nomor Kartu Keluarga maksimal 16 karakter.");
                continue;
            }
            if (!kk.matches("[a-zA-Z0-9]+")) {
                System.out.println("Nomor Kartu Keluarga hanya boleh mengandung huruf dan angka tanpa spasi.");
                continue;
            }
            return kk;
        }
    }

    private static String inputNama(Scanner scanner) {
        while (true) {
            System.out.print("Masukkan Nama Perwakilan\t\t: ");
            String nama = scanner.nextLine().trim();
            if (nama.isEmpty()) {
                System.out.println("Nama tidak boleh kosong.");
                continue;
            }
            if (nama.length() > 50) {
                System.out.println("Nama maksimal 50 karakter.");
                continue;
            }
            if (!nama.matches("[a-zA-Z\\s\\-']+")) {
                System.out.println("Nama hanya boleh mengandung huruf, spasi, tanda - dan '.");
                continue;
            }
            return nama;
        }
    }

    private static String inputAlamat(Scanner scanner) {
        while (true) {
            System.out.print("Masukkan Alamat\t\t\t: ");
            String alamat = scanner.nextLine().trim();
            if (alamat.isEmpty()) {
                System.out.println("Alamat tidak boleh kosong.");
                continue;
            }
            if (alamat.length() > 100) {
                System.out.println("Alamat maksimal 100 karakter.");
                continue;
            }
            if (!alamat.matches("[\\w\\s\\.,\\-/#]+")) {
                System.out.println("Alamat mengandung karakter tidak valid.");
                continue;
            }
            return alamat;
        }
    }

    private static String inputJenisBansos(Scanner scanner) {
        while (true) {
            System.out.print("Masukkan Jenis Bansos\t\t: ");
            String jenis = scanner.nextLine().trim();
            if (jenis.isEmpty()) {
                System.out.println("Jenis bansos tidak boleh kosong.");
                continue;
            }
            if (jenis.length() > 30) {
                System.out.println("Jenis bansos maksimal 30 karakter.");
                continue;
            }
            if (!jenis.matches("[a-zA-Z\\s]+")) {
                System.out.println("Jenis bansos hanya boleh mengandung huruf dan spasi.");
                continue;
            }
            return jenis;
        }
    }

    private static Boolean inputStatus(Scanner scanner) {
        while (true) {
            System.out.print("Status Menerima Bansos? (Sudah/Belum)\t: ");
            String statusInput = scanner.nextLine().trim().toLowerCase();
            if (statusInput.equals("sudah")) return true;
            if (statusInput.equals("belum")) return false;
            System.out.println("Input status tidak valid. Masukkan 'Sudah' atau 'Belum'.");
        }
    }
}
