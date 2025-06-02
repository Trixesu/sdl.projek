public class PenerimaBansos implements Comparable<PenerimaBansos> {
    private String kk;
    private String nama;
    private String alamat;
    private String jenisBansos;
    private boolean aktif; // true = sudah menerima, false = belum

    public PenerimaBansos(String kk, String nama, String alamat, String jenisBansos, boolean aktif) {
        this.kk = kk;
        this.nama = nama;
        this.alamat = alamat;
        this.jenisBansos = jenisBansos;
        this.aktif = aktif;
    }

    // Getter dan Setter
    public String getkk() {
        return kk;
    }

    public void setkk(String kk) {
        this.kk = kk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisBansos() {
        return jenisBansos;
    }

    public void setJenisBansos(String jenisBansos) {
        this.jenisBansos = jenisBansos;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    @Override
    public int compareTo(PenerimaBansos other) {
        // Urutkan berdasarkan nomor KK secara ascending
        return this.kk.compareToIgnoreCase(other.kk);
    }

    @Override
    public String toString() {
        return String.format("KK: %s, Nama: %s, Jenis: %s, Status: %s",
                kk, nama, jenisBansos, aktif ? "Sudah" : "Belum");
    }
}
