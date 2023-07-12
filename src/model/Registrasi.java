package model;

/**
 * @author LEMTIKOM
 * Muhamad Azis - 22523289
 * Andi Arya Tri Buana Agung - 22523299
 * Pugar Huda Mantoro - 22523045
 * Muhammad Haris Rusnanda - 22523282
 */
public class Registrasi {
    private String nama;
    private String alamat;
    private String username;
    private String noTelepon;
    private String password;

    public Registrasi(String nama, String alamat, String username, String noTelepon, String password) {
        this.nama = nama;
        this.alamat = alamat;
        this.username = username;
        this.noTelepon = noTelepon;
        this.password = password;
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

    public String getnoTelepon() {
        return noTelepon;
    }

    public String getPassword() {
        return password;
    }
}