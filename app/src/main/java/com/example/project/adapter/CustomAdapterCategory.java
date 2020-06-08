package com.example.project.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.project.R;
import com.example.project.event.TheLoaiEvent;
import com.example.project.event.TruyenEvent;
import com.example.project.model.TheLoai;
import com.example.project.model.Truyen;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapterCategory extends ArrayAdapter {
    private Context context;
    private int layoutId;
    private TheLoaiEvent event;
    private ArrayList<TheLoai> theLoaiData = new ArrayList<>();

    public CustomAdapterCategory(@NonNull Context context, int layoutId, ArrayList<TheLoai> theLoaiData, TheLoaiEvent event) {
        super(context, layoutId);
        this.context = context;
        this.layoutId = layoutId;
        this.theLoaiData = theLoaiData;
        this.event = event;
    }

    public int getCount() {
        return theLoaiData.size();
    }

    @SuppressLint("ViewHolder")
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater.from(context));
        convertView = inflater.inflate(layoutId, null);
        Button btnCategory= (Button) convertView.findViewById(R.id.btnCategory);

        btnCategory.setText(theLoaiData.get(position).getTenTheLoai());

        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.theLoaiClick(theLoaiData.get(position).getId());
            }
        });
        return convertView;
    }
}
