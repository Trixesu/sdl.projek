package sdl.projek;

public class DaftarPenerima {
    private PenerimaBansos head;

    public DaftarPenerima() {
        head = null;
    }

    // Tambah data di depan linked list
    public void tambahPenerima(PenerimaBansos penerima) {
        penerima.next = head;
        head = penerima;
    }

    // Hapus data berdasarkan ID
    public boolean hapusPenerima(String id) {
        if (head == null) return false;

        if (head.id.equals(id)) {
            head = head.next;
            return true;
        }

        PenerimaBansos current = head;
        while (current.next != null && !current.next.id.equals(id)) {
            current = current.next;
        }

        if (current.next == null) {
            return false; // tidak ketemu
        } else {
            current.next = current.next.next;
            return true;
        }
    }

    // Cari data berdasarkan ID
    public PenerimaBansos cariPenerima(String id) {
        PenerimaBansos current = head;
        while (current != null) {
            if (current.id.equals(id)) {
                return current;
            }
            current = current.next;
        }
        return null; // tidak ketemu
    }

    // Update data penerima berdasarkan ID
    public boolean updatePenerima(String id, String nama, String alamat,
                                  String jenisBansos, int jumlah, String status) {
        PenerimaBansos penerima = cariPenerima(id);
        if (penerima == null) return false;

        penerima.nama = nama;
        penerima.alamat = alamat;
        penerima.jenisBansos = jenisBansos;
        penerima.jumlah = jumlah;
        penerima.status = status;
        return true;
    }

    // Tampilkan semua data penerima
    public void tampilkanSemua() {
        if (head == null) {
            System.out.println("Data penerima bansos kosong.");
            return;
        }
        PenerimaBansos current = head;
        while (current != null) {
            current.tampilData();
            current = current.next;
        }
    }
}

