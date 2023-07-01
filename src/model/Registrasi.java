package model;

public class Registrasi {
    private String nama;
    private String alamat;
    private String username;
    private String peran;
    private String noTelepon;
    private String password;

    public Registrasi(String nama, String alamat, String username, String peran, String noTelepon, String password) {
        this.nama = nama;
        this.alamat = alamat;
        this.username = username;
        this.peran = peran;
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
