# Projek Struktur Data Linier

## Mata Kuliah
Struktur Data Linier

## Topik
Sistem Pengolahan Data Bantuan Sosial

## Anggota Kelompok
1. 245314015 - Nikodemus Nowo Taka  
2. 245314016 - Lukas Indra Christiawan  
3. 245314017 - Abraham Elbar Fernandes  
4. 245314018 - Paulus Seta Risfikawanta

---

## Deskripsi Projek

Projek ini merupakan implementasi sistem pengolahan data penerima bantuan sosial menggunakan konsep struktur data linier.  
Fokus utama projek adalah membuat aplikasi manajemen data penerima bantuan sosial yang mampu melakukan operasi seperti:  
- Menambah data penerima  
- Menghapus data penerima  
- Memperbarui status penerimaan bantuan  
- Mencari data penerima berdasarkan Nomor Kartu Keluarga (KK)  
- Menampilkan seluruh data penerima dengan format tabel yang rapi  
- Mengurutkan data berdasarkan jenis bantuan, nomor KK, dan status penerimaan

---

## Fitur

- Validasi input data yang ketat agar data valid dan konsisten  
- Penggunaan binary search untuk pencarian data yang cepat (setelah pengurutan)  
- Pengurutan data menggunakan algoritma Quick Sort berdasarkan berbagai kriteria  
- Tampilan output berbentuk tabel dengan format rapi di console  
- Penanganan error dan validasi input agar aplikasi lebih robust dan user-friendly

---

## Struktur Kode

- `Main.java`  
  Berisi kelas utama yang mengelola interaksi pengguna dan menghubungkan operasi data.

- `DaftarPenerima.java`  
  Kelas yang mengelola koleksi data penerima bantuan sosial, meliputi operasi tambah, hapus, update, pencarian, dan pengurutan.

- `PenerimaBansos.java`  
  Kelas model untuk data penerima bantuan sosial, berisi atribut seperti KK, nama, alamat, jenis bantuan, dan status penerimaan.

---

## Cara Menjalankan

1. Kompilasi semua file `.java`  
   ```bash
   javac Main.java DaftarPenerima.java PenerimaBansos.java
