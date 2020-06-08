package com.example.project.model;

public class TruyenYeuThich {
    private int id;
    private int idNguoiDung;
    private int idTruyen;

    public TruyenYeuThich(int id, int idNguoiDung, int idTruyen) {
        this.id = id;
        this.idNguoiDung = idNguoiDung;
        this.idTruyen = idTruyen;
    }

    public TruyenYeuThich() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public int getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(int idTruyen) {
        this.idTruyen = idTruyen;
    }

    @Override
    public String toString() {
        return "TruyenYeuThich{" +
                "id=" + id +
                ", idNguoiDung=" + idNguoiDung +
                ", idTruyen=" + idTruyen +
                '}';
    }
}
