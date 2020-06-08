package com.example.project.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.project.DetailActivity;
import com.example.project.event.TruyenEvent;
import com.example.project.model.Truyen;
import com.example.project.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapter extends ArrayAdapter {
    private Context context;
    private int layoutId;
    private TruyenEvent event;
    private ArrayList<Truyen> truyenData = new ArrayList<>();

    public CustomAdapter(@NonNull Context context, int layoutId, ArrayList<Truyen> truyenData, TruyenEvent event) {
        super(context, layoutId);
        this.context = context;
        this.layoutId = layoutId;
        this.truyenData = truyenData;
        this.event = event;
    }

    public int getCount() {
        return truyenData.size();
    }

    @SuppressLint("ViewHolder")
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater.from(context));
        convertView = inflater.inflate(layoutId, null);
        CircleImageView profile_image = (CircleImageView) convertView.findViewById(R.id.profile_image);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
        TextView tvDateUp = (TextView) convertView.findViewById(R.id.tvDateUp);
        LinearLayout linearItem = (LinearLayout) convertView.findViewById(R.id.linearItem);

        Glide.with(context)
                .load(truyenData.get(position).getHinhAnh())
                .centerCrop()
                .into(profile_image);
        tvTitle.setText(truyenData.get(position).getTenTruyen());
        tvAuthor.setText( "Tác giả: " + truyenData.get(position).getTacGia());
        tvDateUp.setText("Ngày tải: " + truyenData.get(position).getNgayTai());

        linearItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               event.truyenClick(truyenData.get(position).getId());
            }
        });
        return convertView;
    }
}
