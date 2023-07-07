package model;

public class Makanan {
    private int makanan_id;
    private int pengguna_id;
    private String tanggalPenawaran;
    private String namaMakanan;
    private int jumlahMakanan;
    private String lokasiPengambilan;
    private String jenisMakanan;
    private String tanggalKadaluwarsa;

    public Makanan(int makanan_id, String tanggalPenawaran, String namaMakanan, int jumlahMakanan, String lokasiPengambilan, String jenisMakanan, String tanggalKadaluwarsa) {
        this.makanan_id = makanan_id;
        this.tanggalPenawaran = tanggalPenawaran;
        this.namaMakanan = namaMakanan;
        this.jumlahMakanan = jumlahMakanan;    
        this.lokasiPengambilan = lokasiPengambilan;
        this.jenisMakanan = jenisMakanan;    
        this.tanggalKadaluwarsa = tanggalKadaluwarsa;  
    }

    public int getMakanan_id() {
        return makanan_id;
    }
    public String getTanggalPenawaran() {
        return tanggalPenawaran;
    }
    public String getNamaMakanan() {
        return namaMakanan;
    }
    public int getJumlahMakanan() {
        return jumlahMakanan;
    }
    public String getLokasiPengambilan() {
        return lokasiPengambilan;
    }
    public String getJenisMakanan() {
        return jenisMakanan;
    }
    public String getTanggalKadaluwarsa() {
        return tanggalKadaluwarsa;
    }

    public void setJumlahMakanan(int stokMakanan) {
        this.jumlahMakanan = stokMakanan;
    }
}
