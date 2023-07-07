package model;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */

public class Penawaran {
    private String makanan_id;
    private String pengguna_id;
    private String tanggalPenawaran;
    private String namaMakanan;
    private String jumlahMakanan;
    private String jenisMakanan;
    private String lokasiPengambilan;
    private String tanggalKadaluarsa;

    public Penawaran(String makanan_id, String pengguna_id, String tanggalPenawaran, String namaMakanan, String jumlahMakanan, String jenisMakanan, String lokasiPengambilan, String tanggalKadaluarsa) {
        this.makanan_id = makanan_id;
        this.pengguna_id = pengguna_id;
        this.tanggalPenawaran = tanggalPenawaran;
        this.namaMakanan = namaMakanan;
        this.jumlahMakanan = jumlahMakanan;
        this.jenisMakanan = jenisMakanan;
        this.lokasiPengambilan = lokasiPengambilan;
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    public String getMakanan_id() {
        return makanan_id;
    }

    public String getPengguna_id() {
        return pengguna_id;
    }

    public String getTanggalPenawaran() {
        return tanggalPenawaran;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public String getJumlahMakanan() {
        return jumlahMakanan;
    }

    public String getJenisMakanan() {
        return jenisMakanan;
    }

    public String getLokasiPengambilan() {
        return lokasiPengambilan;
    }

    public String getTanggalKadaluarsa() {
        return tanggalKadaluarsa;
    }
}

