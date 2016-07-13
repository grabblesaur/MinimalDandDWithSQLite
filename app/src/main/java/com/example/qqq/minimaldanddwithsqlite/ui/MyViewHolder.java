package com.example.qqq.minimaldanddwithsqlite.ui;

import android.view.View;
import android.widget.TextView;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

/**
 * Created by qqq on 13.07.2016.
 */
class MyViewHolder extends AbstractDraggableItemViewHolder {
    TextView textView;

    public MyViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(android.R.id.text1);
    }
}
