package com.example.project.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.project.DBHelper;

public class TheLoaiDB {
    SQLiteDatabase database;
    DBHelper dbHelper;

    public TheLoaiDB(Context context) {
        dbHelper = new DBHelper(context);
        try {
            database = dbHelper.getWritableDatabase();
        }
        catch (SQLException ex) {
            database = dbHelper.getReadableDatabase();
        }
    }

    public Cursor getAllData() {
        String[] cot = {
                dbHelper.COT_IDTHELOAI,
                dbHelper.COT_TENTHELOAI
        };
        Cursor cursor = null;
        cursor = database.query(dbHelper.TEN_BANG_THELOAI, cot, null, null, null, null,
                dbHelper.COT_IDTHELOAI + " DESC");
        return cursor;
    }

    public Cursor getIDTheLoai(String tenTheLoai) {
        Cursor cursor = null;
        cursor = database.rawQuery("SELECT * FROM " + dbHelper.TEN_BANG_THELOAI +" WHERE " + dbHelper.COT_TENTHELOAI + " = '" + tenTheLoai + "'",  null);
        return cursor;
    }

    public Cursor getNameTheLoai(int idTheLoai) {
        Cursor cursor = null;
        cursor = database.rawQuery("SELECT * FROM " + dbHelper.TEN_BANG_THELOAI +" WHERE " + dbHelper.COT_IDTHELOAI + " = " + idTheLoai,  null);
        return cursor;
    }
}
