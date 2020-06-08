package com.example.project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AboutAppFragment extends Fragment {
    TextView aboutApp;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setControl();
        setEvent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about_app, container, false);
    }

    private void setControl() {
        aboutApp = (TextView) getActivity().findViewById(R.id.aboutApp);
    }

    private void setEvent() {
        aboutApp.setText("Ứng dụng Đọc Truyện bao gồm hơn 20.000 bộ truyện hay nhất, được cộng đồng dịch giả và chia sẻ online. Các truyện được tổng hợp và phân loại đầy đủ, gồm có: Ngôn Tình, Kiếm Hiệp, Truyện Teen, Tiên Hiệp, Sắc Hiệp, Đam Mỹ, Dị Giới,... Đồng thời, trạng thái truyện được phân loại: hoàn thành, đang dịch, tạm dừng,...\n" +
                "\n" +
                "Cảm ơn các bạn đã đồng hành cùng ứng dụng!\n" +
                "\n" +
                "Chúng tôi rất vui vì các bạn đã tin tưởng và sử dụng ứng dụng trong thời gian qua, chúng tôi ra mong muốn nhận được những nhận xét, đánh giá, góp ý của các bạn, đây là nguồn cảm hứng để chúng tôi tiếp tục cải tiến mang lại những trải nghiệm tốt hơn trong tương lai!");
    }
}
