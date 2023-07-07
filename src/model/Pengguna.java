package model;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class Pengguna {
    private String pengguna_id;
    private String nama;
    private String alamat;
    private String username;
    private String peran;
    private String noTelepon;
    private String password;

    public Pengguna(String pengguna_id, String nama, String alamat, String username, String peran, String noTelepon, String password) {
        this.pengguna_id = pengguna_id;
        this.nama = nama;
        this.alamat = alamat;
        this.username = username;
        this.peran = peran;
        this.noTelepon = noTelepon;
        this.password = password;
    }

    public String getPengguna_id() {
        return pengguna_id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getUsername() {
        return username;
    }

    public String getPeran() {
        return peran;
    }

    public String getnoTelepon() {
        return noTelepon;
    }

    public String getPassword() {
        return password;
    }
}
