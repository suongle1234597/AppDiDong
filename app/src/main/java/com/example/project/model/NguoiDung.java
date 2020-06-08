package com.example.project.model;

public class NguoiDung {
     private  int id;
     private String tenNguoiDung;
     private String tenTaiKhoan;
     private String email;
     private String matKhau;
     private String avatar;

    public NguoiDung(int id, String tenNguoiDung, String tenTaiKhoan, String email, String matKhau, String avatar) {
        this.id = id;
        this.tenNguoiDung = tenNguoiDung;
        this.tenTaiKhoan = tenTaiKhoan;
        this.email = email;
        this.matKhau = matKhau;
        this.avatar = avatar;
    }

    public NguoiDung() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "id=" + id +
                ", tenNguoiDung='" + tenNguoiDung + '\'' +
                ", tenTaiKhoan='" + tenTaiKhoan + '\'' +
                ", email='" + email + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
