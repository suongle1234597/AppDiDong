package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.project.adapter.CustomAdapterAdmin;
import com.example.project.database.TruyenDB;
import com.example.project.event.TruyenEvent;
import com.example.project.model.Truyen;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class AdminFragment extends Fragment implements TruyenEvent{
    EditText edtSearchTruyen;
    ImageView imgViewSearchTruyen;
    ListView listViewTruyen;
    private CustomAdapterAdmin adapter;
    ArrayList<Truyen> truyenData = new ArrayList<>();
    TruyenDB truyenDB;
    NavigationView nv;
    Button btnAddTruyen;
    SwipeRefreshLayout swRefresh ;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    private void setControl() {
        btnAddTruyen = (Button) getActivity().findViewById(R.id.btnAddTruyen);
        edtSearchTruyen = (EditText) getActivity().findViewById(R.id.edtSearchTruyen);
        imgViewSearchTruyen = (ImageView) getActivity().findViewById(R.id.imgViewSearchTruyen);
        swRefresh = (SwipeRefreshLayout) getActivity().findViewById(R.id.swRefresh);
        listViewTruyen = (ListView) getActivity().findViewById(R.id.listViewTruyen);
        nv = (NavigationView)  getActivity().findViewById(R.id.nv);
        Menu menu = nv.getMenu();

        if(Login.idNguoiDung != 0  && !Login.tenNguoiDung.equals("")) {
            MenuItem item = menu.findItem(R.id.item_login).setTitle(Login.tenNguoiDung);
            menu.findItem(R.id.item_register).setVisible(false);
            menu.findItem(R.id.item_logout).setVisible(true);
        }
        else {
            MenuItem item = menu.findItem(R.id.item_login).setTitle("Đăng nhập");
            menu.findItem(R.id.item_register).setVisible(true);
            menu.findItem(R.id.item_logout).setVisible(false);
        }
    }

    private void setEvent() {
        adapter = new CustomAdapterAdmin(getActivity(), R.layout.activity_admin_item, truyenData, this);
        listViewTruyen.setAdapter(adapter);
        truyenDB = new TruyenDB(getActivity());
        capNhatTruyen();

        btnAddTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragmentAdmin, new AddEditTruyenFragment()).addToBackStack("truyen").commit();
            }
        });

        swRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                capNhatTruyen();
                swRefresh.setRefreshing(false);
            }
        });
    }



    public  void capNhatTruyen() {
        Cursor cursor = truyenDB.getAllData();
        if(cursor != null) {
            truyenData.clear();
            while (cursor.moveToNext()) {
                Truyen truyen = new Truyen();
                truyen.setId(cursor.getInt(0));
                truyen.setTenTruyen(cursor.getString(1));
                truyen.setTacGia(cursor.getString(2));
                truyen.setIdTL(cursor.getInt(3));
                truyen.setNgayTai(cursor.getString(4));
                truyen.setHinhAnh(cursor.getString(5));
                truyen.setTomTat(cursor.getString(6));
                truyen.setNoiDung(cursor.getString(7));
                truyenData.add(truyen);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void truyenClick(int idTruyen) {
        AddEditTruyenFragment addEditTruyenFragment = new AddEditTruyenFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", idTruyen);
        addEditTruyenFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentAdmin, addEditTruyenFragment).addToBackStack("truyen").commit();
        getFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        capNhatTruyen();
                    }
                });
    }

    @Override
    public void truyenClickDelete(int idTruyen) {
        truyenDB.xoa(idTruyen);
        capNhatTruyen();
    }
}
