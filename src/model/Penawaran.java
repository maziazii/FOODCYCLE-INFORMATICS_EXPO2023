package model;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class Penawaran {
    private int idMakanan;
    private String tanggalPenawaran;
    private String namaMakanan;
    private int jumlahMakanan;
    private String jenis;
    private String lokasi;
    private String kadaluwarsa;
    
    public Penawaran(int idMakanan, String tanggalPenawaran, String namaMakanan, int jumlahMakanan, String lokasi, String jenis, String kadaluwarsa) {
        this.idMakanan = idMakanan;
        this.tanggalPenawaran = tanggalPenawaran;
        this.namaMakanan = namaMakanan;
        this.jumlahMakanan = jumlahMakanan;
        this.lokasi = lokasi;
        this.jenis = jenis;
        this.kadaluwarsa = kadaluwarsa;
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
    
    public String getLokasi() {
        return lokasi;
    }
    
    public String getJenis() {
        return jenis;
    }

    public String getKadaluwarsa() {
        return kadaluwarsa;
    }
}
