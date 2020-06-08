package com.example.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.project.database.TruyenDB;
import com.example.project.model.Truyen;

public class DBHelper  extends SQLiteOpenHelper {
    //Tên cơ sở dữ liệu
    public static final String TEN_DATABASE = "QUANLYTRUYENDOC";
    //Tên bảng
    public static final String TEN_BANG_TRUYEN = "Truyen";
    //
    public static final String COT_ID = "_id";
    public static final String COT_TENTRUYEN = "_tenTruyen";
    public static final String COT_TACGIA = "_tacGia";
    public static final String COT_IDTL = "_idTheLoai";
    public static final String COT_NGAYTAI = "_ngayTai";
    public static final String COT_HINHANH = "_hinhAnh";
    public static final String COT_TOMTAT = "_tomTat";
    public static final String COT_NOIDUNG = "_noiDung";


    private static final String Del = "DROP TABLE IF EXISTS "+TEN_BANG_TRUYEN;
    //Lệnh tạo bảng
    private static final String TAO_BANG_TRUYEN = ""
            + "create table IF NOT EXISTS " + TEN_BANG_TRUYEN + " ( "
            + COT_ID + " integer primary key autoincrement , "
            + COT_TENTRUYEN + " text not null, "
            + COT_TACGIA + " text , "
            + COT_IDTL + " integer , "
            + COT_NGAYTAI + " text ,"
            + COT_HINHANH + " text not null, "
            + COT_TOMTAT + " text, "
            + COT_NOIDUNG + " text );";

    //Tạo bảng Thể loại
    public static final String TEN_BANG_THELOAI = "TheLoai";
    //
    public static final String COT_IDTHELOAI = "_id";
    public static final String COT_TENTHELOAI = "_tenTheLoai";

    //Lệnh tạo bảng
    private static final String TAO_BANG_THELOAI = ""
            + "create table IF NOT EXISTS " + TEN_BANG_THELOAI + " ( "
            + COT_IDTHELOAI + " integer primary key autoincrement, "
            + COT_TENTHELOAI + " text not null);";

    //Tạo bảng user
    public static final String TEN_BANG_NGUOIDUNG = "NguoiDung";
    //
    public static final String COT_IDNGUOIDUNG = "_id";
    public static final String COT_TENNGUOIDUNG = "_tenNguoiDung";
    public static final String COT_TENTAIKHOAN = "_tenTaiKhoan";
    public static final String COT_EMAIL = "_email";
    public static final String COT_MATKHAU = "_matKhau";
    public static final String COT_AVATAR = "_avatar";

    //Lệnh tạo bảng
    private static final String TAO_BANG_NGUOIDUNG = ""
            + "create table IF NOT EXISTS " + TEN_BANG_NGUOIDUNG + " ( "
            + COT_IDNGUOIDUNG + " integer primary key autoincrement, "
            + COT_TENNGUOIDUNG + " text not null, "
            + COT_TENTAIKHOAN + " text not null, "
            + COT_EMAIL + " text not null , "
            + COT_MATKHAU + " text not null, "
            + COT_AVATAR + " text);";

    //Tạo bảng user
    public static final String TEN_BANG_TRUYENYEUTHICH  = "TruyenYeuThich";
    //
    public static final String COT_IDTRUYENYT = "_id";
    public static final String COT_IDND = "_idNguoiDung";
    public static final String COT_IDTRUYEN = "_idTruyen";

    //Tao bang Yeu Thich
    private static final String TAO_BANG_TRUYENYEUTHICH = ""
            + "create table IF NOT EXISTS " + TEN_BANG_TRUYENYEUTHICH + " ( "
            + COT_IDTRUYENYT + " integer primary key autoincrement, "
            + COT_IDND + " integer not null, "
            + COT_IDTRUYEN + " integer not null);";

    public DBHelper(@Nullable Context context) {
        super(context, TEN_DATABASE, null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(TAO_BANG_TRUYEN);
//        db.execSQL(TAO_BANG_THELOAI);
//        db.execSQL(TAO_BANG_NGUOIDUNG);
//        db.execSQL(TAO_BANG_TRUYENYEUTHICH);

        //////////////////////DATA TRUYEN YEU THICH /////////////////
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYENYEUTHICH + " VALUES (null, 1, 20);");

//        /////////////////////DATA THE LOAI//////////////////////////
//        db.execSQL("INSERT INTO " + TEN_BANG_THELOAI + " VALUES (null, 'Tiên Hiệp');");
//        db.execSQL("INSERT INTO " + TEN_BANG_THELOAI + " VALUES (null, 'Kiếm Hiệp');");
//        db.execSQL("INSERT INTO " + TEN_BANG_THELOAI + " VALUES (null, 'Ngôn Tình');");
//        db.execSQL("INSERT INTO " + TEN_BANG_THELOAI + " VALUES (null, 'Lịch Sử');");
//        db.execSQL("INSERT INTO " + TEN_BANG_THELOAI + " VALUES (null, 'Truyện Mới');");
//        db.execSQL("INSERT INTO " + TEN_BANG_THELOAI + " VALUES (null, 'Xem nhiều');");
//        db.execSQL("INSERT INTO " + TEN_BANG_THELOAI + " VALUES (null, 'Trinh Thám');");
//        db.execSQL("INSERT INTO " + TEN_BANG_THELOAI + " VALUES (null, 'Xuyên Không');");
//
//        /////////////////////DATA ADMIN//////////////////////////
//
//        db.execSQL("INSERT INTO " + TEN_BANG_NGUOIDUNG  + " VALUES (null,'SuongLe', 'admin', 'admin@gmail.com', 'admin12345', 'https://1.bp.blogspot.com/-5MkW7MTSn7I/Xnch-W3ikNI/AAAAAAAAZks/IBANk9N7koUDmf5VCHrB0dunwSJxHqsXQCLcBGAsYHQ/s1600/Cach-Lam-Avatar-Dang-Hot%2B%25282%2529.jpg');");
//
//        /////////////////////DATA TRUYEN//////////////////////////
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null, 'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd', 'sssssssss');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'dddddddddd','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Tkkkkkkkkkkkkkkkkkkkk','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'Ta Không Thành Tiên','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//        db.execSQL("INSERT INTO " + TEN_BANG_TRUYEN + " VALUES (null,'fffffff','Tiên Hiệp',1 ,'11/2/2017' ,'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ6RVqxXffoiNa_xOSk70hE5EbOZ04B3uk7SqcI7NFrkJ31LIzN&usqp=CAU', 'dddd');");
//

    }
}
