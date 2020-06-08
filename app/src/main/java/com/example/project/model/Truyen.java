package com.example.project.model;

public class Truyen {
    private int id;
    private  String tenTruyen;
    private  String tacGia;
    private  int idTL;
    private  String ngayTai;
    private  String hinhAnh;
    private  String noiDung;
    private  String tomTat;

    public Truyen(int id, String tenTruyen, String tacGia, int idTL, String ngayTai, String hinhAnh, String tomTat, String noiDung) {
        this.id = id;
        this.tenTruyen = tenTruyen;
        this.tacGia = tacGia;
        this.idTL = idTL;
        this.ngayTai = ngayTai;
        this.hinhAnh = hinhAnh;
        this.tomTat = tomTat;
        this.noiDung = noiDung;
    }

    public Truyen() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public int getIdTL() {
        return idTL;
    }

    public void setIdTL(int idTL) {
        this.idTL = idTL;
    }

    public String getNgayTai() {
        return ngayTai;
    }

    public void setNgayTai(String ngayTai) {
        this.ngayTai = ngayTai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @Override
    public String toString() {
        return "Truyen{" +
                "id=" + id +
                ", tenTruyen='" + tenTruyen + '\'' +
                ", tacGia='" + tacGia + '\'' +
                ", idTL=" + idTL +
                ", ngayTai='" + ngayTai + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", noiDung='" + noiDung + '\'' +
                ", tomTat='" + tomTat + '\'' +
                '}';
    }
}
