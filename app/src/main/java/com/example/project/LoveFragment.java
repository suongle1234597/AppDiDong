package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.project.adapter.CustomAdapter;
import com.example.project.adapter.CustomAdapterLove;
import com.example.project.database.NguoiDungDB;
import com.example.project.database.TruyenDB;
import com.example.project.database.TruyenYeuThichDB;
import com.example.project.event.TruyenEvent;
import com.example.project.model.NguoiDung;
import com.example.project.model.Truyen;
import com.example.project.model.TruyenYeuThich;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class LoveFragment extends Fragment implements TruyenEvent {
    ListView listViewLove;
    LinearLayout linearItem;
    private CustomAdapterLove adapter;
    ArrayList<Truyen> truyenData = new ArrayList<>();
    ArrayList<TruyenYeuThich> truyenYeuThichData = new ArrayList<>();

    TruyenDB truyenDB;
    TruyenYeuThichDB truyenYeuThichDB;
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
        return inflater.inflate(R.layout.fragment_love, container, false);
    }

    private void setControl() {
        listViewLove = (ListView) getActivity().findViewById(R.id.listViewLove);
        linearItem = (LinearLayout) getActivity().findViewById(R.id.linearItem);

        Intent intent = getActivity().getIntent();
        id = intent.getIntExtra("id", 0);
    }

    private void setEvent() {
        adapter = new CustomAdapterLove(getActivity(), R.layout.activity_item, truyenData, this);
        listViewLove.setAdapter(adapter);
        truyenYeuThichDB = new TruyenYeuThichDB(getActivity());
        truyenDB = new TruyenDB(getActivity());
        getListId();
    }

    private void getListId() {
        Cursor cursor = truyenYeuThichDB.getIdTruyen(Login.idNguoiDung);
        if(cursor != null) {
            truyenYeuThichData.clear();
            truyenData.clear();
            while (cursor.moveToNext()) {
                TruyenYeuThich truyenYeuThich = new TruyenYeuThich();
                truyenYeuThich.setId(cursor.getInt(0));
                truyenYeuThich.setIdNguoiDung(cursor.getInt(1));
                truyenYeuThich.setIdTruyen(cursor.getInt(2));

                Cursor cursor2 = truyenDB.getDataTruyen(cursor.getInt(2));
                if(cursor2 != null) {
                    while (cursor2.moveToNext()) {
                        Truyen truyen = new Truyen();
                        truyen.setId(cursor2.getInt(0));
                        truyen.setTenTruyen(cursor2.getString(1));
                        truyen.setTacGia(cursor2.getString(2));
                        truyen.setIdTL(cursor2.getInt(3));
                        truyen.setNgayTai(cursor2.getString(4));
                        truyen.setHinhAnh(cursor2.getString(5));
                        truyen.setTomTat(cursor2.getString(6));
                        truyen.setNoiDung(cursor2.getString(7));
                        truyenData.add(truyen);
                    }
                }
                truyenYeuThichData.add(truyenYeuThich);


            }
            System.out.println(truyenYeuThichData);
            System.out.println(truyenData);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void truyenClick(int idTruyen) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("id", idTruyen);
        startActivity(intent);
    }

    @Override
    public void truyenClickDelete(int idTruyen) {
    }
}
