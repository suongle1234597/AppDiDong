package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.project.database.TruyenDB;
import com.example.project.event.ScrollInterface;
import com.example.project.model.Truyen;

public class ContentActivity extends AppCompatActivity implements ScrollInterface {
    Button btnBackContent;
    ImageButton imgbtnComeback, imgbtnSetting;
    TextView tvTitleContent, tvContent;
    int id;
    TruyenDB truyenDB;
    Truyen truyen = new Truyen();
    ScrollViewExt scroll;
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        setControl();
        setEvent();
    }

    private void setControl() {
        tvTitleContent = (TextView) findViewById(R.id.tvTitleContent);
        tvContent = (TextView) findViewById(R.id.tvContent) ;
        imgbtnComeback = (ImageButton) findViewById(R.id.imgbtnComeback);
        imgbtnSetting = (ImageButton) findViewById(R.id.imgbtnSetting);
        scroll = (ScrollViewExt) findViewById(R.id.scrollView1);
        scroll.setScrollViewListener(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        position = intent.getIntExtra("position", 0);
    }

    private void setEvent() {
        truyenDB = new TruyenDB(this);
        capNhatTruyen(id);
        tvTitleContent.setText(truyen.getTenTruyen());
        tvContent.setText(truyen.getNoiDung());

        scroll.post(new Runnable() {
            public void run() {
                scroll.scrollTo(0, position);
                scroll.smoothScrollTo(0, position);
                scroll.smoothScrollBy(0, position);
            }
        });

        setStyle();

        imgbtnComeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("position", position);
                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
        });

        imgbtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SettingActivity.class);
                intent.putExtra("id", 0);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setStyle();
    }

    private void setStyle() {
        Typeface type = Typeface.createFromAsset(getAssets(), SettingActivity.font);
        tvContent.setTypeface(type);
        if(SettingActivity.background.equals("1") && SettingActivity.textColor.equals("0")) {
            scroll.setBackgroundColor(Color.WHITE);
            tvContent.setTextColor(Color.BLACK);
        }
        else{
            scroll.setBackgroundColor(Color.BLACK);
            tvContent.setTextColor(Color.WHITE);
        }
    }

    @Override
    public void onScrollChanged(ScrollViewExt scrollView, int x, int y, int oldx, int oldy) {
        // We take the last son in the scrollview
        View view = (View) scrollView.getChildAt(scrollView.getChildCount() - 1);
        int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));
        position = oldy;
        // if diff is zero, then the bottom has been reached
        if (diff == 0) {
            // do stuff
        }
    }

    private void capNhatTruyen(int id) {
        Cursor cursor = truyenDB.getDataTruyen(id);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                truyen.setId(cursor.getInt(0));
                truyen.setTenTruyen(cursor.getString(1));
                truyen.setTacGia(cursor.getString(2));
                truyen.setIdTL(cursor.getInt(3));
                truyen.setNgayTai(cursor.getString(4));
                truyen.setHinhAnh(cursor.getString(5));
                truyen.setTomTat(cursor.getString(6));
                truyen.setNoiDung(cursor.getString(7));
            }
        }
    }
}
