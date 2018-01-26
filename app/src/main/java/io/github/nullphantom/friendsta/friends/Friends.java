package io.github.nullphantom.friendsta.friends;

/**
 * Created by Ilham Bintang on 26/01/2018.
 */

public class Friends {
    private String nama;
    private String gambar;
    private String email;
    private String no_hp;

    public String getNama() {
        return nama;
    }

    public Friends(String nama, String gambar, String email, String no_hp) {
        this.nama = nama;
        this.gambar = gambar;
        this.email = email;
        this.no_hp = no_hp;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }
}
