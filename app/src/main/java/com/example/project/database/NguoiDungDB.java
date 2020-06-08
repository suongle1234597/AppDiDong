package com.example.project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.DBHelper;
import com.example.project.model.NguoiDung;
import com.example.project.model.Truyen;

public class NguoiDungDB {
    SQLiteDatabase database;
    DBHelper dbHelper;

    public NguoiDungDB(Context context) {
        dbHelper = new DBHelper(context);
        try {
            database = dbHelper.getWritableDatabase();
        }
        catch (SQLException ex) {
            database = dbHelper.getReadableDatabase();
        }
    }

    public long them(NguoiDung nguoiDung) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COT_TENNGUOIDUNG, nguoiDung.getTenNguoiDung());
        values.put(dbHelper.COT_TENTAIKHOAN, nguoiDung.getTenTaiKhoan());
        values.put(dbHelper.COT_EMAIL, nguoiDung.getEmail());
        values.put(dbHelper.COT_MATKHAU, nguoiDung.getMatKhau());
        values.put(dbHelper.COT_AVATAR, nguoiDung.getAvatar());
        return database.insert(dbHelper.TEN_BANG_NGUOIDUNG, null, values);
    }

    public Cursor getAllData() {
        String[] cot = {
                dbHelper.COT_IDNGUOIDUNG,
                dbHelper.COT_TENNGUOIDUNG ,
                dbHelper.COT_TENTAIKHOAN,
                dbHelper.COT_EMAIL,
                dbHelper.COT_MATKHAU,
                dbHelper.COT_AVATAR
        };
        Cursor cursor = null;
        cursor = database.query(dbHelper.TEN_BANG_NGUOIDUNG, cot, null, null, null, null,
                dbHelper.COT_IDNGUOIDUNG + " DESC");
        return cursor;
    }

    public Cursor getIdTruyen(int idNguoiDung) {
        Cursor cursor = null;
        cursor = database.rawQuery("SELECT * FROM " + dbHelper.TEN_BANG_NGUOIDUNG +" WHERE " + dbHelper.COT_IDNGUOIDUNG + " = " + idNguoiDung,  null);
        return cursor;
    }

    public Boolean checkAccount(String tenTaiKhoan) {
        Cursor cursor = null;
        cursor = database.rawQuery("SELECT * FROM "  + dbHelper.TEN_BANG_NGUOIDUNG +" WHERE " + dbHelper.COT_TENTAIKHOAN + " = '" + tenTaiKhoan +"'",  null);
        if(cursor.getCount() > 0) return false;
        else return true;
    }
}
