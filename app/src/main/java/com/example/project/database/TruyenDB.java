package com.example.project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.DBHelper;
import com.example.project.model.Truyen;

public class TruyenDB {
    SQLiteDatabase database;
    DBHelper dbHelper;

    public TruyenDB(Context context) {
        dbHelper = new DBHelper(context);
        try {
            database = dbHelper.getWritableDatabase();
        }
        catch (SQLException ex) {
            database = dbHelper.getReadableDatabase();
        }
    }

    public long them(Truyen truyen) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COT_TENTRUYEN, truyen.getTenTruyen());
        values.put(dbHelper.COT_TACGIA, truyen.getTacGia());
        values.put(dbHelper.COT_IDTL, truyen.getIdTL());
        values.put(dbHelper.COT_NGAYTAI, truyen.getNgayTai());
        values.put(dbHelper.COT_HINHANH, truyen.getHinhAnh());
        values.put(dbHelper.COT_TOMTAT, truyen.getTomTat());
        values.put(dbHelper.COT_NOIDUNG, truyen.getNoiDung());
        return database.insert(dbHelper.TEN_BANG_TRUYEN, null, values);
    }

    public long xoa(int idTruyen) {
        return database.delete(dbHelper.TEN_BANG_TRUYEN,
                dbHelper.COT_ID + " = " + idTruyen, null);
    }

    public long sua(Truyen truyen) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COT_TENTRUYEN, truyen.getTenTruyen());
        values.put(dbHelper.COT_TACGIA, truyen.getTacGia());
        values.put(dbHelper.COT_IDTL, truyen.getIdTL());
        values.put(dbHelper.COT_NGAYTAI, truyen.getNgayTai());
        values.put(dbHelper.COT_HINHANH, truyen.getHinhAnh());
        values.put(dbHelper.COT_TOMTAT, truyen.getTomTat());
        values.put(dbHelper.COT_NOIDUNG, truyen.getNoiDung());

        return  database.update(dbHelper.TEN_BANG_TRUYEN, values,
                dbHelper.COT_ID + " = " + truyen.getId(), null);
    }

    public Cursor getAllData() {
        String[] cot = {
                dbHelper.COT_ID,
                dbHelper.COT_TENTRUYEN ,
                dbHelper.COT_TACGIA,
                dbHelper.COT_IDTL,
                dbHelper.COT_NGAYTAI,
                dbHelper.COT_HINHANH,
                dbHelper.COT_TOMTAT,
                dbHelper.COT_NOIDUNG
        };
        Cursor cursor = null;
        cursor = database.query(dbHelper.TEN_BANG_TRUYEN, cot, null, null, null, null,
                dbHelper.COT_ID + " DESC");
        return cursor;
    }

    public Cursor getDataTruyenByIdTheLoai(int idTheLoai) {
        Cursor cursor = null;
        cursor = database.rawQuery("SELECT * FROM " + dbHelper.TEN_BANG_TRUYEN +" WHERE " + dbHelper.COT_IDTL + " = " + idTheLoai,  null);
        return cursor;
    }

    public Cursor getDataTruyen(int id) {
        Cursor cursor = null;
        cursor = database.rawQuery("SELECT * FROM " + dbHelper.TEN_BANG_TRUYEN +" WHERE " + dbHelper.COT_ID + " = " + id,  null);
        return cursor;
    }

    public Cursor getTruyenTimKiem(String text) {
        Cursor cursor = null;
        cursor=database.rawQuery("SELECT * FROM "+ dbHelper.TEN_BANG_TRUYEN + " WHERE "
                + dbHelper.COT_TENTRUYEN + " LIKE  '"+text+"%'", null);
        return cursor;
    }
}


