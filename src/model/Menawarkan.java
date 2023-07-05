package model;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class Menawarkan {
    private String namaMakanan;
    private String jumlah;
    private String idPenawaran;
    private String kadaluwarsa;
    private String lokasi;
    private String jenis;

    public Menawarkan(String namaMakanan, String jumlah, String idPenawaran, String kadaluwarsa, String lokasi, String jenis) {
        this.namaMakanan = namaMakanan;
        this.jumlah = jumlah;
        this.idPenawaran = idPenawaran;
        this.kadaluwarsa = kadaluwarsa;
        this.lokasi = lokasi;
        this.jenis = jenis;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public String getJumlah() {
        return jumlah;
    }
    
    public String getIdPenawaran() {
        return idPenawaran;
    }

    public String getKadaluwarsa() {
        return kadaluwarsa;
    }

    public String getLokasi() {
        return lokasi;
    }
    
    public String getJenis() {
        return jenis;
    }
}
