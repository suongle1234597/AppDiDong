package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.project.adapter.CustomAdapterCategory;
import com.example.project.database.TheLoaiDB;
import com.example.project.event.TheLoaiEvent;
import com.example.project.model.TheLoai;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements TheLoaiEvent{
    ListView listView_category;
    private CustomAdapterCategory adapter;
    TheLoaiDB theLoaiDB;
    Button btnCategory;
    ArrayList<TheLoai> theLoaiData = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    private void setControl() {
        listView_category = (ListView) getActivity().findViewById(R.id.listView_category);
        btnCategory = (Button) getActivity().findViewById(R.id.btnCategory);
    }

    private void setEvent() {
        adapter = new CustomAdapterCategory(getActivity(), R.layout.activity_item_category, theLoaiData, (TheLoaiEvent) this);
        listView_category.setAdapter(adapter);
        theLoaiDB = new TheLoaiDB(getActivity());
        capNhatTheLoai();
    }

    private void capNhatTheLoai() {
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

    @Override
    public void theLoaiClick(int idTheLoai) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", idTheLoai);
        homeFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentCate, homeFragment).commit();

//        Intent intent = new Intent(getActivity(), HomeFragment.class);
//        intent.putExtra("id", idTheLoai);
//        getActivity().startActivityForResult(intent, 999);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_CANCELED && requestCode == 999) { }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == Activity.RESULT_CANCELED && requestCode == 999) { }
//    }
}
