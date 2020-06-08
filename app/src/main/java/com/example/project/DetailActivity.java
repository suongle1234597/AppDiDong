package com.example.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project.database.TheLoaiDB;
import com.example.project.database.TruyenDB;
import com.example.project.database.TruyenYeuThichDB;
import com.example.project.model.TheLoai;
import com.example.project.model.Truyen;
import com.example.project.model.TruyenYeuThich;

public class DetailActivity extends AppCompatActivity {
    Button btnRead, btnLike, btnDislike;
    ImageView imageView ;
    TextView tvTitle, tvAuthor, tvCatagory, tvUploadDate, tvContentDetail;
    int id;
    TruyenYeuThichDB truyenYeuThichDB;
    TruyenDB truyenDB;
    TheLoaiDB theLoaiDB;
    Truyen truyen = new Truyen();
    TheLoai theLoai = new TheLoai();
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
       position=getIntent().getIntExtra("position",0);
        setControl();
        setEvent();
    }

    private void setControl() {
        btnRead = (Button) findViewById(R.id.btnRead);
        btnLike = (Button) findViewById(R.id.btnLike);
        btnDislike = (Button) findViewById(R.id.btnDislike);
        imageView = (ImageView) findViewById(R.id.imageView);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        tvCatagory = (TextView) findViewById(R.id.tvCatagory);
        tvUploadDate = (TextView) findViewById(R.id.tvUploadDate);
        tvContentDetail = (TextView) findViewById(R.id.tvContentDetail);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void setEvent() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        truyenYeuThichDB = new TruyenYeuThichDB(getApplicationContext());
        theLoaiDB = new TheLoaiDB(getApplicationContext());
        truyenDB = new TruyenDB(getApplicationContext());
        capNhatTruyen(id);
        Glide.with(this)
                .load(truyen.getHinhAnh())
                .centerCrop()
                .into(imageView);
        tvTitle.setText(truyen.getTenTruyen());
        tvAuthor.setText("Tác giả:      " + truyen.getTacGia());
        tvCatagory.setText("Thể loại:     " + theLoai.getTenTheLoai());
        tvUploadDate.setText("Ngày tải:     " + truyen.getNgayTai());
        tvContentDetail.setText(truyen.getTomTat());

        if(truyenYeuThichDB.checkLove(Login.idNguoiDung, id) == true) {
            btnLike.setVisibility(View.GONE);
            btnDislike.setVisibility(View.VISIBLE);
        }
        else{
            btnLike.setVisibility(View.VISIBLE);
            btnDislike.setVisibility(View.GONE);
        }

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), ContentActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("position", position);
                startActivityForResult(intent, 999);
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Login.idNguoiDung == 0  && Login.tenNguoiDung.equals("")) {
                    Toast.makeText(getApplicationContext(), "Hãy đăng nhập để thêm truyện yêu thích", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    TruyenYeuThich truyenYeuThich = new TruyenYeuThich();
                    truyenYeuThich.setIdNguoiDung(Login.idNguoiDung);
                    truyenYeuThich.setIdTruyen(id);
                    truyenYeuThichDB.them(truyenYeuThich);
                    btnLike.setVisibility(View.GONE);
                    btnDislike.setVisibility(View.VISIBLE);
                }
            }
        });

        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TruyenYeuThich truyenYeuThich = new TruyenYeuThich();
                truyenYeuThich.setIdNguoiDung(Login.idNguoiDung);
                truyenYeuThich.setIdTruyen(id);
                truyenYeuThichDB.xoa(truyenYeuThich);
                btnDislike.setVisibility(View.GONE);
                btnLike.setVisibility(View.VISIBLE);
            }
        });
    }

    private void capNhatTruyen(int id) {
        int idTL = 0;
        Cursor cursor = truyenDB.getDataTruyen(id);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                truyen.setId(cursor.getInt(0));
                truyen.setTenTruyen(cursor.getString(1));
                truyen.setTacGia(cursor.getString(2));
                truyen.setIdTL(cursor.getInt(3));
                idTL = cursor.getInt(3);
                truyen.setNgayTai(cursor.getString(4));
                truyen.setHinhAnh(cursor.getString(5));
                truyen.setTomTat(cursor.getString(6));
                truyen.setNoiDung(cursor.getString(7));

                System.out.println("ddf " + idTL);
                Cursor cursor2 = theLoaiDB.getNameTheLoai(cursor.getInt(3));
                if(cursor2 != null) {
                    while (cursor2.moveToNext()) {
                        theLoai.setId(cursor2.getInt(0));
                        theLoai.setTenTheLoai(cursor2.getString(1));
                    }
                }
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_CANCELED && requestCode == 999) {
            if(data != null) {
                position=data.getIntExtra("position",0);
            }
        }
    }
}
