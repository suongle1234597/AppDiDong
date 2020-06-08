package com.example.project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.DBHelper;
import com.example.project.model.TruyenYeuThich;

public class TruyenYeuThichDB {
    SQLiteDatabase database;
    DBHelper dbHelper;

    public TruyenYeuThichDB(Context context) {
        dbHelper = new DBHelper(context);
        try {
            database = dbHelper.getWritableDatabase();
        }
        catch (SQLException ex) {
            database = dbHelper.getReadableDatabase();
        }
    }

    public long them(TruyenYeuThich truyenYeuThich) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COT_IDND, truyenYeuThich.getIdNguoiDung());
        values.put(dbHelper.COT_IDTRUYEN, truyenYeuThich.getIdTruyen());
        return database.insert(dbHelper.TEN_BANG_TRUYENYEUTHICH, null, values);
    }

    public long xoa(TruyenYeuThich truyenYeuThich) {
        return database.delete(dbHelper.TEN_BANG_TRUYENYEUTHICH,
                dbHelper.COT_IDND + " = " + truyenYeuThich.getIdNguoiDung() + " AND " + dbHelper.COT_IDTRUYEN + " = " + truyenYeuThich.getIdTruyen(), null);
    }

    public Cursor getAllData() {
        String[] cot = {
                dbHelper.COT_IDTRUYENYT,
                dbHelper.COT_IDND ,
                dbHelper.COT_IDTRUYEN
        };
        Cursor cursor = null;
        cursor = database.query(dbHelper.TEN_BANG_TRUYENYEUTHICH, cot, null, null, null, null,
                dbHelper.COT_IDTRUYENYT + " DESC");
        return cursor;
    }

    public Cursor getIdTruyen(int idNguoiDung) {
        Cursor cursor = null;
        cursor = database.rawQuery("SELECT * FROM " + dbHelper.TEN_BANG_TRUYENYEUTHICH +" WHERE " + dbHelper.COT_IDND + " = " + idNguoiDung,  null);
        return cursor;
    }

    public Boolean checkLove(int idNguoiDung, int idTruyen) {
        Cursor cursor = null;
        cursor = database.rawQuery("SELECT * FROM "  + dbHelper.TEN_BANG_TRUYENYEUTHICH +" WHERE " + dbHelper.COT_IDND + " = " + idNguoiDung + " AND " + dbHelper.COT_IDTRUYEN + " = " + idTruyen,  null);
        if(cursor.getCount() > 0) return true;
        else return false;
    }
}
