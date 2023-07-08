package model;

public class Pemesanan {
    private int idPengguna;
    private int idMakanan;
    private int idPemesanan;
    private String tanggalPemesanan;
    private String namaMakanan;
    private int jumlahPemesanan;
    private String metodePengambilan;
    private String lokasiMetode;

    public Pemesanan(int idPengguna, int idMakanan, int idPemesanan, String tanggalPemesanan, String namaMakanan, int jumlahPemesanan, String metodePengambilan, String lokasiMetode) {
        this.idPengguna = idPengguna;
        this.idMakanan = idMakanan;
        this.idPemesanan = idPemesanan;
        this.tanggalPemesanan = tanggalPemesanan;
        this.namaMakanan = namaMakanan;
        this.jumlahPemesanan = jumlahPemesanan;
        this.metodePengambilan = metodePengambilan;
        this.lokasiMetode = lokasiMetode;
    }

    public int getIdPengguna() {
        return idPengguna;
    }

    public int getIdMakanan() {
        return idMakanan;
    }

    public int getIdPemesanan() {
        return idPemesanan;
    }

    public String getTanggalPemesanan() {
        return tanggalPemesanan;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public int getJumlahPemesanan() {
        return jumlahPemesanan;
    }

    public String getMetodePengambilan() {
        return metodePengambilan;
    }

    public String getLokasiMetode() {
        return lokasiMetode;
    }
}
