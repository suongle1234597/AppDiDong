package com.example.project.event;

import com.example.project.ScrollViewExt;

public interface ScrollInterface {
    void onScrollChanged(ScrollViewExt scrollView,
                         int x, int y, int oldx, int oldy);
}
