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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.project.adapter.CustomAdapter;
import com.example.project.database.TruyenDB;
import com.example.project.event.TruyenEvent;
import com.example.project.model.Truyen;

import java.util.ArrayList;


public class SearchFragment extends Fragment implements TruyenEvent {
    ListView listViewSearch;
    LinearLayout linearSearch;
    private CustomAdapter adapter;
    ArrayList<Truyen> truyenData = new ArrayList<>();
    TruyenDB truyenDB;
    EditText edtSearchTruyen;
    ImageView imgViewSearchTruyen;
    TextView textNotification;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    private void setControl() {
        listViewSearch = (ListView) getActivity().findViewById(R.id.listViewSearch);
        linearSearch = (LinearLayout) getActivity().findViewById(R.id.linearSearch);
        edtSearchTruyen = (EditText) getActivity().findViewById(R.id.edtSearchTruyen);
        imgViewSearchTruyen = (ImageView) getActivity().findViewById(R.id.imgViewSearchTruyen);
        textNotification = (TextView) getActivity().findViewById(R.id.textNotification);
    }

    private void setEvent() {
        adapter = new CustomAdapter(getActivity(), R.layout.activity_item, truyenData, this);
        listViewSearch.setAdapter(adapter);
        truyenDB = new TruyenDB(getActivity());

        imgViewSearchTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtSearchTruyen.getText().toString().trim().equals("")) {
                    edtSearchTruyen.setError("Bạn vui lòng nhập tên truyện để tìm kiếm");
                    edtSearchTruyen.requestFocus();
                    return;
                }
                else{
                    capNhatTruyen(edtSearchTruyen.getText().toString());
                    if(truyenData.size() != 0) {
                        textNotification.setVisibility(View.GONE);
                    }
                    else{
                        textNotification.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void capNhatTruyen(String textSearch) {
        Cursor cursor = truyenDB.getTruyenTimKiem(textSearch);
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
