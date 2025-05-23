package sdl.projek;

public class PenerimaBansos {
    String id;
    String nama;
    String alamat;
    String jenisBansos;
    int jumlah;
    String status;
    PenerimaBansos next;

    public PenerimaBansos(String id, String nama, String alamat,
                          String jenisBansos, int jumlah, String status) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.jenisBansos = jenisBansos;
        this.jumlah = jumlah;
        this.status = status;
        this.next = null;
    }

    public void tampilData() {
        System.out.println("ID: " + id);
        System.out.println("Nama: " + nama);
        System.out.println("Alamat: " + alamat);
        System.out.println("Jenis Bansos: " + jenisBansos);
        System.out.println("Jumlah: " + jumlah);
        System.out.println("Status: " + status);
        System.out.println("---------------------------");
    }
}
