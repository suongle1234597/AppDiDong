package com.example.project.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.project.AddEditTruyenFragment;
import com.example.project.AdminFragment;
import com.example.project.DetailActivity;
import com.example.project.R;
import com.example.project.database.TruyenDB;
import com.example.project.event.TruyenEvent;
import com.example.project.model.Truyen;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapterAdmin extends ArrayAdapter {
    private Context context;
    private int layoutId;
    private TruyenEvent event;
    private TruyenDB truyenDB;
    private ArrayList<Truyen> truyenData = new ArrayList<>();

    public CustomAdapterAdmin(@NonNull Context context, int layoutId, ArrayList<Truyen> truyenData, TruyenEvent event) {
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
        CircleImageView imgTruyenAdmin = (CircleImageView) convertView.findViewById(R.id.imgTruyenAdmin);
        TextView tvTitleTruyen = (TextView) convertView.findViewById(R.id.tvTitleTruyen);
        TextView tvAuthorTruyen = (TextView) convertView.findViewById(R.id.tvAuthorTruyen);
        TextView tvDateUpTruyen = (TextView) convertView.findViewById(R.id.tvDateUpTruyen);
        ImageView imgViewEdit = (ImageView) convertView.findViewById(R.id.imgViewEdit);
        ImageView imgViewDelete = (ImageView) convertView.findViewById(R.id.imgViewDelete);

        Glide.with(context)
                .load(truyenData.get(position).getHinhAnh())
                .centerCrop()
                .into(imgTruyenAdmin);
        tvTitleTruyen.setText(truyenData.get(position).getTenTruyen());
        tvAuthorTruyen.setText( "Tác giả: " + truyenData.get(position).getTacGia());
        tvDateUpTruyen.setText("Ngày tải: " + truyenData.get(position).getNgayTai());

        truyenDB = new TruyenDB(getContext());
        imgViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.truyenClick(truyenData.get(position).getId());
            }
        });

        imgViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.truyenClickDelete(truyenData.get(position).getId());
//                ((AdminFragment) context.getApplicationContext() ).capNhatTruyen();
            }
        });

        return convertView;
    }
}
