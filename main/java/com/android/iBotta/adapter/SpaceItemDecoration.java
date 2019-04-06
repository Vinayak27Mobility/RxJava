package com.android.iBotta.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int spaceVert;
    private int spaceHorz;

    public SpaceItemDecoration(int spaceVert, int spaceHorz) {
        this.spaceVert = spaceVert;
        this.spaceHorz = spaceHorz;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = spaceVert;
        outRect.right = spaceHorz;
    }
}
