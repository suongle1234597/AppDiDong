package com.example.project;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.adapter.CustomAdapter;
import com.example.project.database.TruyenDB;
import com.example.project.event.TruyenEvent;
import com.example.project.model.Truyen;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements TruyenEvent {
    ListView listview;
    LinearLayout linearItem;
    private CustomAdapter adapter;
    ArrayList<Truyen> truyenData = new ArrayList<>();
    TruyenDB truyenDB;
    TextView textNotificationCate;
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void setControl() {
        listview = (ListView) getActivity().findViewById(R.id.listview);
        linearItem = (LinearLayout) getActivity().findViewById(R.id.linearItem);
        textNotificationCate = (TextView) getActivity().findViewById(R.id.textNotificationCate);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("id", 0);
        }
    }

    private void setEvent() {
        adapter = new CustomAdapter(getActivity(), R.layout.activity_item, truyenData, this);
        listview.setAdapter(adapter);
        truyenDB = new TruyenDB(getActivity());
        if(id != 0) {
            capNhatTruyenByIdTheLoai();
            if(truyenData.size() != 0) {
                textNotificationCate.setVisibility(View.GONE);
            }
            else{
                textNotificationCate.setVisibility(View.VISIBLE);
            }
        }
        else  capNhatTruyen();
    }

    private void capNhatTruyenByIdTheLoai() {
        Cursor cursor = truyenDB.getDataTruyenByIdTheLoai(id);
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

    private void capNhatTruyen() {
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
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("id", idTruyen);
        startActivity(intent);
    }

    @Override
    public void truyenClickDelete(int idTruyen) {
    }
}
