package com.example.project;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    public static String background = "1";
    public static String textColor = "0";
    public static String font = "font/archivo_narrow.ttf";

    TextView tvTest;
    Button btnChangeStyleText;
    RadioButton rbtn_archivo_narrow, rbtn_amita, rbtn_aguafina_script, rbtn_light, rbtn_dark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setControl();
        setEvent();
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        setControl();
//        setEvent();
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_setting, container, false);
//    }

    private void setControl() {
        btnChangeStyleText = (Button) findViewById(R.id.btnChangeStyleText);
        rbtn_archivo_narrow = (RadioButton) findViewById(R.id.rbtn_archivo_narrow);
        rbtn_amita = (RadioButton) findViewById(R.id.rbtn_amita);
        rbtn_aguafina_script = (RadioButton) findViewById(R.id.rbtn_aguafina_script);
        rbtn_light = (RadioButton) findViewById(R.id.rbtn_light);
        rbtn_dark = (RadioButton) findViewById(R.id.rbtn_dark);
        tvTest = (TextView) findViewById(R.id.tvTest);
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

        btnChangeStyleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rbtn_amita.isChecked()){
                    Typeface type = Typeface.createFromAsset(getAssets(), "font/amita.ttf");
                    tvTest.setTypeface(type);
                    font = "font/amita.ttf";
                }
                if(rbtn_aguafina_script.isChecked()){
                    Typeface type = Typeface.createFromAsset(getAssets(), "font/aguafina_script.ttf");
                    tvTest.setTypeface(type);
                    font = "font/aguafina_script.ttf";
                }
                if(rbtn_archivo_narrow.isChecked()) {
                    Typeface type = Typeface.createFromAsset(getAssets(), "font/archivo_narrow.ttf");
                    tvTest.setTypeface(type);
                    font = "font/archivo_narrow.ttf";
                }
                if(rbtn_light.isChecked()) {
                    tvTest.setBackgroundColor(Color.WHITE);
                    tvTest.setTextColor(Color.BLACK);
                    background = "1";
                    textColor = "0";
                }
                if(rbtn_dark.isChecked()) {
                    tvTest.setBackgroundColor(Color.BLACK);
                    tvTest.setTextColor(Color.WHITE);
                    background = "0";
                    textColor = "1";
                }
            }
        });
    }
}
