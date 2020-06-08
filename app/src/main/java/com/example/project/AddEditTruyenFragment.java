package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project.database.TheLoaiDB;
import com.example.project.database.TruyenDB;
import com.example.project.model.TheLoai;
import com.example.project.model.Truyen;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class AddEditTruyenFragment extends Fragment {
    Button btnLuu, btnThoat;
    EditText edtTenTruyen, edtTacGia, edtNoiDung, edtTomTat, edtLink;
    Spinner spnTheLoai;
    TheLoaiDB theLoaiDB;
    TruyenDB truyenDB;
    ImageView imgUpload;
    ArrayList<TheLoai> theLoaiData = new ArrayList<TheLoai>();
    ArrayAdapter<TheLoai> adapter;
    Truyen truyen = new Truyen();

    int id;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_edit_truyen, container, false);
    }

    private void setControl() {
        edtTenTruyen = (EditText) getActivity().findViewById(R.id.edtTenTruyen);
        edtTacGia = (EditText) getActivity().findViewById(R.id.edtTacGia);
        edtNoiDung = (EditText) getActivity().findViewById(R.id.edtNoiDung);
        edtTomTat = (EditText) getActivity().findViewById(R.id.edtTomTat);
        edtLink = (EditText) getActivity().findViewById(R.id.edtLink);
        btnLuu = (Button) getActivity().findViewById(R.id.btnLuu);
        btnThoat = (Button) getActivity().findViewById(R.id.btnThoat);
        spnTheLoai = (Spinner) getActivity().findViewById(R.id.spnTheLoai);
        imgUpload = (ImageView) getActivity().findViewById(R.id.imgUpload);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("id", 0);
        }
    }

    private void setEvent() {
        truyenDB = new TruyenDB(getActivity());
        theLoaiDB = new TheLoaiDB(getActivity());
        adapter = new ArrayAdapter<TheLoai>(getActivity(), android.R.layout.simple_spinner_item, theLoaiData);
        spnTheLoai.setAdapter(adapter);
        getTheLoai();

        if(id != 0) {
            editThongTin(id);
            edtTenTruyen.setText(truyen.getTenTruyen());
            edtTacGia.setText(truyen.getTacGia());
            edtLink.setText(truyen.getHinhAnh());
            edtTomTat.setText(truyen.getTomTat());
            edtNoiDung.setText(truyen.getNoiDung());
            spnTheLoai.setSelection(truyen.getIdTL());

            Glide.with(getActivity())
                    .load(truyen.getHinhAnh())
                    .centerCrop()
                    .into(imgUpload);

            btnLuu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edtTenTruyen.getText().toString().trim().equals("")) {
                        edtTenTruyen.setError("Bạn vui lòng nhập vào tên truyện!");
                        edtTenTruyen.requestFocus();
                        return;
                    }
                    if(edtTacGia.getText().toString().trim().equals("")) {
                        edtTacGia.setError("Bạn vui lòng nhập vào tên tác giả!");
                        edtTacGia.requestFocus();
                        return;
                    }

                    if(edtLink.getText().toString().trim().equals("")) {
                        edtLink.setError("Bạn vui lòng nhập vào link hình ảnh!");
                        edtLink.requestFocus();
                        return;
                    }
                    else{
                        Glide.with(getActivity())
                                .load(edtLink.getText().toString())
                                .centerCrop()
                                .into(imgUpload);
                    }
                    if(edtTomTat.getText().toString().trim().equals("")) {
                        edtTomTat.setError("Bạn vui lòng nhập vào phần tóm tắt!");
                        edtTomTat.requestFocus();
                        return;
                    }
                    if(edtNoiDung.getText().toString().trim().equals("")) {
                        edtNoiDung.setError("Bạn vui lòng nhập vào phần nội dung!");
                        edtNoiDung.requestFocus();
                        return;
                    }

                    Date todayDate = Calendar.getInstance().getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    String todayString = formatter.format(todayDate);
                    truyen.setId(id);
                    truyen.setNgayTai(todayString);
                    truyen.setTenTruyen(edtTenTruyen.getText().toString().trim());
                    truyen.setTacGia(edtTacGia.getText().toString().trim());
                    truyen.setIdTL(getIdTheLoai(spnTheLoai.getSelectedItem().toString()) );
                    truyen.setTomTat(edtTomTat.getText().toString().trim());
                    truyen.setNoiDung(edtNoiDung.getText().toString().trim());
                    truyen.setHinhAnh(edtLink.getText().toString().trim());
                    truyenDB.sua(truyen);
                    Toast.makeText(getActivity(), "Sửa truyện thành công!", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                }
            });
        }
        else {
            btnLuu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edtTenTruyen.getText().toString().trim().equals("")) {
                        edtTenTruyen.setError("Bạn vui lòng nhập vào tên truyện!");
                        edtTenTruyen.requestFocus();
                        return;
                    }
                    if(edtTacGia.getText().toString().trim().equals("")) {
                        edtTacGia.setError("Bạn vui lòng nhập vào tên tác giả!");
                        edtTacGia.requestFocus();
                        return;
                    }

                    if(edtLink.getText().toString().trim().equals("")) {
                        edtLink.setError("Bạn vui lòng nhập vào link hình ảnh!");
                        edtLink.requestFocus();
                        return;
                    }
                    else{
                        Glide.with(getActivity())
                                .load(edtLink.getText().toString())
                                .centerCrop()
                                .into(imgUpload);
                    }
                    if(edtTomTat.getText().toString().trim().equals("")) {
                        edtTomTat.setError("Bạn vui lòng nhập vào phần tóm tắt!");
                        edtTomTat.requestFocus();
                        return;
                    }
                    if(edtNoiDung.getText().toString().trim().equals("")) {
                        edtNoiDung.setError("Bạn vui lòng nhập vào phần nội dung!");
                        edtNoiDung.requestFocus();
                        return;
                    }

                    Date todayDate = Calendar.getInstance().getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    String todayString = formatter.format(todayDate);
                    truyen.setNgayTai(todayString);
                    truyen.setTenTruyen(edtTenTruyen.getText().toString().trim());
                    truyen.setTacGia(edtTacGia.getText().toString().trim());
                    truyen.setIdTL(getIdTheLoai(spnTheLoai.getSelectedItem().toString()) );
                    truyen.setTomTat(edtTomTat.getText().toString().trim());
                    truyen.setNoiDung(edtNoiDung.getText().toString().trim());
                    truyen.setHinhAnh(edtLink.getText().toString().trim());
                    truyenDB.them(truyen);
                    Toast.makeText(getActivity(), "Thêm truyện thành công!", Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                }
            });
        }

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void editThongTin(int id) {
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

    private int getIdTheLoai(String ten) {
        Cursor cursor = theLoaiDB.getIDTheLoai(ten);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                return cursor.getInt(0);
            }
        }
        return 0;
    }

    private void getTheLoai() {
        Cursor cursor = theLoaiDB.getAllData();
        if(cursor != null) {
            theLoaiData.clear();
            while (cursor.moveToNext()) {
                TheLoai theLoai = new TheLoai();
                theLoai.setId(cursor.getInt(0));
                theLoai.setTenTheLoai(cursor.getString(1));
                theLoaiData.add(theLoai);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
