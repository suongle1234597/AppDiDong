package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.database.NguoiDungDB;
import com.example.project.model.NguoiDung;


public class Register_Fragment extends Fragment {
    EditText edtTenNguoiDungDK, edtTenTKDK, edtEmailDK, edtMatKhauDK, edtXacNhanMKDK;
    Button btnDangKy;
    NguoiDungDB nguoiDungDB;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    private void setControl() {
        edtTenNguoiDungDK = (EditText) getActivity().findViewById(R.id.edtTenNguoiDungDK);
        edtTenTKDK = (EditText) getActivity().findViewById(R.id.edtTenTKDK);
        edtEmailDK = (EditText) getActivity().findViewById(R.id.edtEmailDK);
        edtMatKhauDK = (EditText) getActivity().findViewById(R.id.edtMatKhauDK);
        edtXacNhanMKDK = (EditText) getActivity().findViewById(R.id.edtXacNhanMKDK);
        btnDangKy = (Button) getActivity().findViewById(R.id.btnDangKy);
    }

    private void setEvent() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nguoiDungDB = new NguoiDungDB(getActivity());

                if (edtTenNguoiDungDK.getText().toString().equals("")) {
                    edtTenNguoiDungDK.setError("Bạn vui lòng nhập vào họ tên");
                    edtTenNguoiDungDK.requestFocus();
                    return;
                }
                if (edtTenTKDK.getText().toString().equals("")) {
                    edtTenTKDK.setError("Bạn vui lòng nhập vào tên tài khoản");
                    edtTenTKDK.requestFocus();
                    return;
                }
                else{
                    if(nguoiDungDB.checkAccount(edtTenTKDK.getText().toString()) == false) {
                        edtTenTKDK.setError("Tên tài khoản bị trùng. vui lòng nhập lại!");
                        edtTenTKDK.requestFocus();
                        return;
                    }
                }
                if (edtEmailDK.getText().toString().equals("")) {
                    edtEmailDK.setError("Bạn vui lòng nhập vào email");
                    edtEmailDK.requestFocus();
                    return;
                }
                if (edtMatKhauDK.getText().toString().equals("")) {
                    edtMatKhauDK.setError("Bạn vui lòng nhập vào mật khẩu");
                    edtMatKhauDK.requestFocus();
                    return;
                }
                if (edtXacNhanMKDK.getText().toString().equals("")) {
                    edtXacNhanMKDK.setError("Bạn vui lòng nhập xác nhận mật khẩu");
                    edtXacNhanMKDK.requestFocus();
                    return;
                }
                if (!edtTenNguoiDungDK.getText().toString().equals("") && !edtTenTKDK.getText().toString().equals("")
                        && !edtEmailDK.getText().toString().equals("")  && !edtMatKhauDK.getText().toString().equals("")
                        && !edtXacNhanMKDK.getText().toString().equals("")) {

                    if(!edtMatKhauDK.getText().toString().equals(edtXacNhanMKDK.getText().toString())) {
                        edtXacNhanMKDK.setError("Xác nhận mật khẩu phải trùng với mật khẩu");
                        edtXacNhanMKDK.requestFocus();
                        return;
                    }
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.setTenNguoiDung(edtTenNguoiDungDK.getText().toString());
                    nguoiDung.setEmail(edtEmailDK.getText().toString());
                    nguoiDung.setTenTaiKhoan(edtTenTKDK.getText().toString());
                    nguoiDung.setMatKhau(edtMatKhauDK.getText().toString());
                    nguoiDungDB.them(nguoiDung);
                    Toast.makeText(getActivity(), "Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragmentRegister, new Login()).commitNow();
                }
            }
        });
    }
}
