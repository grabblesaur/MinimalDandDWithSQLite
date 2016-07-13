package com.example.qqq.minimaldanddwithsqlite.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qqq.minimaldanddwithsqlite.model.MyItem;
import com.example.qqq.minimaldanddwithsqlite.R;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qqq on 13.07.2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements DraggableItemAdapter<MyViewHolder> {
    List<MyItem> mItems;

    public MyAdapter() {
        setHasStableIds(true); // this is required for D&D feature.

        mItems = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mItems.add(new MyItem(i, "Item " + i));
        }
    }

    @Override
    public long getItemId(int position) {
        return mItems.get(position).id; // need to return stable (= not change even after reordered) value
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyItem item = mItems.get(position);
        holder.textView.setText(item.text);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        MyItem movedItem = mItems.remove(fromPosition);
        mItems.add(toPosition, movedItem);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public boolean onCheckCanStartDrag(MyViewHolder holder, int position, int x, int y) {
        return true;
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(MyViewHolder holder, int position) {
        return null;
    }

    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return true;
    }
}
