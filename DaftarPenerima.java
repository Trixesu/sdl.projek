public class DaftarPenerima {
    private final PenerimaBansos[] daftar;
    private int size;

    public DaftarPenerima(int kapasitas) {
        daftar = new PenerimaBansos[kapasitas];
        size = 0;
    }

    public boolean isFull() {
        return size >= daftar.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void tambah(PenerimaBansos p) {
        if (isFull()) {
            System.out.println("Daftar sudah penuh, tidak bisa menambahkan penerima baru.");
            return;
        }
        daftar[size++] = p;
        System.out.println("Penerima berhasil ditambahkan.");
    }

    public boolean hapus(String kartuKeluarga) {
        for (int i = 0; i < size; i++) {
            if (daftar[i].getkk().equalsIgnoreCase(kartuKeluarga)) {
                for (int j = i; j < size - 1; j++) {
                    daftar[j] = daftar[j + 1];
                }
                daftar[--size] = null;
                System.out.println("Penerima dengan No. Kartu Keluarga " + kartuKeluarga + " berhasil dihapus.");
                return true;
            }
        }
        System.out.println("Penerima dengan No. Kartu Keluarga " + kartuKeluarga + " tidak ditemukan.");
        return false;
    }

    public boolean updateStatusPenerimaan(String kartuKeluarga, String status) {
        PenerimaBansos p = searchByKK(kartuKeluarga);
        if (p != null) {
            if (status.equalsIgnoreCase("sudah")) {
                p.setAktif(true);
                System.out.println("Status penerimaan bansos untuk No. Kartu Keluarga " + kartuKeluarga + " berhasil diupdate menjadi SUDAH menerima.");
                return true;
            } else if (status.equalsIgnoreCase("belum")) {
                p.setAktif(false);
                System.out.println("Status penerimaan bansos untuk No. Kartu Keluarga " + kartuKeluarga + " berhasil diupdate menjadi BELUM menerima.");
                return true;
            } else {
                System.out.println("Status tidak valid. Gunakan 'sudah' atau 'belum'.");
                return false;
            }
        }
        System.out.println("Penerima dengan No. Kartu Keluarga " + kartuKeluarga + " tidak ditemukan.");
        return false;
    }

    // Binary search, pastikan daftar sudah diurutkan berdasarkan KK sebelum pemanggilan
    public PenerimaBansos searchByKK(String kartuKeluarga) {
        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = daftar[mid].getkk().compareToIgnoreCase(kartuKeluarga);
            if (cmp == 0) {
                return daftar[mid];
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public void sortByJenisBansos() {
        quickSortJenis(0, size - 1);
        System.out.println("Daftar sudah diurutkan berdasarkan jenis bansos.");
    }

    private void quickSortJenis(int low, int high) {
        if (low < high) {
            int pi = partitionJenis(low, high);
            quickSortJenis(low, pi - 1);
            quickSortJenis(pi + 1, high);
        }
    }

    private int partitionJenis(int low, int high) {
        String pivot = daftar[high].getJenisBansos();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (daftar[j].getJenisBansos().compareToIgnoreCase(pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    public void sortByKartuKeluarga() {
        quickSortKK(0, size - 1);
        System.out.println("Daftar sudah diurutkan berdasarkan Kartu Keluarga.");
    }

    private void quickSortKK(int low, int high) {
        if (low < high) {
            int pi = partitionKK(low, high);
            quickSortKK(low, pi - 1);
            quickSortKK(pi + 1, high);
        }
    }

    private int partitionKK(int low, int high) {
        String pivot = daftar[high].getkk();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (daftar[j].getkk().compareToIgnoreCase(pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    public void sortByStatusPenerimaan() {
        quickSortStatus(0, size - 1);
        System.out.println("Daftar sudah diurutkan berdasarkan status penerimaan bansos (sudah menerima di atas).");
    }

    private void quickSortStatus(int low, int high) {
        if (low < high) {
            int pi = partitionStatus(low, high);
            quickSortStatus(low, pi - 1);
            quickSortStatus(pi + 1, high);
        }
    }

    private int partitionStatus(int low, int high) {
        boolean pivot = daftar[high].isAktif();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            // Kita ingin data dengan isAktif=true (Sudah menerima) di posisi lebih kiri
            // Jadi data[j].isAktif() == true dianggap lebih besar dari pivot jika pivot false
            if (daftar[j].isAktif() && !pivot || (!daftar[j].isAktif() && !pivot && j < high)) {
                i++;
                swap(i, j);
            } else if (daftar[j].isAktif() == pivot && daftar[j].isAktif()) {
                // Jika sama-sama true, ikut urutan biasa
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        PenerimaBansos temp = daftar[i];
        daftar[i] = daftar[j];
        daftar[j] = temp;
    }

    public void tampilkanSemua() {
        if (size == 0) {
            System.out.println("\nDaftar penerima kosong.");
        } else {
            System.out.println("Daftar Penerima Bansos:");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("No   | Kartu Keluarga       | Nama                      | Alamat                    | Jenis Bansos              | Status");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < size; i++) {
                PenerimaBansos p = daftar[i];
                String status = p.isAktif() ? "Sudah Menerima" : "Belum Menerima";

                System.out.printf("%-4d | %-20s | %-25s | %-25s | %-25s | %-13s%n",
                        i + 1,
                        p.getkk(),
                        p.getNama(),
                        p.getAlamat(),
                        p.getJenisBansos(),
                        status);
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        }
    }
}
