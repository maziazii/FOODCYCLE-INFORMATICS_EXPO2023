package model;

import java.time.LocalDate;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class Makanan {
    private int idMakanan;
    private String tanggalPenawaran;
    private String namaMakanan;
    private int jumlahMakanan;
    private String lokasiPengambilan;
    private String jenisMakanan;
    private String tanggalKadaluwarsa;

    public Makanan(int idMakanan, String tanggalPenawaran, String namaMakanan, int jumlahMakanan, String lokasiPengambilan, String jenisMakanan, String tanggalKadaluwarsa) {
        this.idMakanan = idMakanan;
        this.tanggalPenawaran = tanggalPenawaran;
        this.namaMakanan = namaMakanan;
        this.jumlahMakanan = jumlahMakanan;    
        this.lokasiPengambilan = lokasiPengambilan;
        this.jenisMakanan = jenisMakanan;    
        this.tanggalKadaluwarsa = tanggalKadaluwarsa;  
    }

    public int getIdMakanan() {
        return idMakanan;
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
