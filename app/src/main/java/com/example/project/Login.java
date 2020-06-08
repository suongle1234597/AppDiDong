package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.database.NguoiDungDB;

public class Login extends Fragment {
    EditText edtAccount, edtPassword;
    Button btnLogin, btnExit;
    NguoiDungDB nguoiDungDB;

    public static int idNguoiDung = 0;
    public static String tenNguoiDung = "";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    private void setControl() {
        edtAccount = (EditText) getActivity().findViewById(R.id.edtAccount);
        edtPassword = (EditText) getActivity().findViewById(R.id.edtPassword);
        btnLogin = (Button) getActivity().findViewById(R.id.btnLogin);
        btnExit = (Button) getActivity().findViewById(R.id.btnExit);
    }

    private void setEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtAccount.getText().toString().equals("")) {
                    edtAccount.setError("Bạn vui lòng nhập vào tên đăng nhập");
                    edtAccount.requestFocus();
                    return;
                }
                if (edtPassword.getText().toString().equals("")) {
                    edtPassword.setError("Bạn vui lòng nhập vào mật khẩu");
                    edtPassword.requestFocus();
                    return;
                }
                if (!edtAccount.getText().toString().equals("") && !edtAccount.getText().toString().equals("")) {
                    nguoiDungDB = new NguoiDungDB(getActivity());

                    if (kiemTraNguoiDung() == true) {
                        Toast.makeText(getActivity(), "Bạn đã đăng nhập", Toast.LENGTH_SHORT).show();
                        if(edtAccount.getText().toString().equals("admin")) {
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.fragmentLogin, new AdminFragment()).commitNow();
                        }
                        else{
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getActivity(), "Thông tin đăng nhập sai. Mời nhập lại", Toast.LENGTH_SHORT).show();
                        edtAccount.requestFocus();
                        return;
                    }
                }
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean kiemTraNguoiDung() {
        Cursor cursor = nguoiDungDB.getAllData();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                if (edtAccount.getText().toString().equals(cursor.getString(2)) && edtPassword.getText().toString().equals(cursor.getString(4))) {
                    idNguoiDung = Integer.parseInt(cursor.getString(0)) ;
                    tenNguoiDung = cursor.getString(1);
                    return true;
                }
            }
        }
        return false;
    }

}
