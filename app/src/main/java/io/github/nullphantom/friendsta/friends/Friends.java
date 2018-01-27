package io.github.nullphantom.friendsta.friends;

/**
 * Created by Ilham Bintang on 26/01/2018.
 */

public class Friends {
    private long friendsId;
    private String nama;
    private String gambar;
    private String email;
    private String no_hp;
    private String gender;

    public Friends() {}
    public Friends(String nama, String gambar, String email, String no_hp, String gender) {
        this.nama = nama;
        this.gambar = gambar;
        this.email = email;
        this.no_hp = no_hp;
        this.gender = gender;
    }

    public long getFriendsId() {
        return friendsId;
    }

    public void setFriendsId(long friendsId) {
        this.friendsId = friendsId;
    }

    public String getNama() {
        return nama;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
