package com.example.qqq.minimaldanddwithsqlite.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qqq.minimaldanddwithsqlite.R;
import com.example.qqq.minimaldanddwithsqlite.model.MyItem;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
        implements DraggableItemAdapter<MyAdapter.MyViewHolder>{
    List<MyItem> mItems;

    public MyAdapter() {
        setHasStableIds(true);

        mItems = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mItems.add(new MyItem(i, "Item " + i));
        }
    }

    public class MyViewHolder  extends AbstractDraggableItemViewHolder{
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

    // Methods of RecyclerView

    @Override
    public long getItemId(int position) {
        return mItems.get(position).id;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(view);
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

    // Methods of DraggableItemAdapter

    @Override
    public boolean onCheckCanStartDrag(MyViewHolder holder, int position, int x, int y) {
        return true;
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(MyViewHolder holder, int position) {
        return null;
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        MyItem movedItem = mItems.remove(fromPosition);
        mItems.add(toPosition, movedItem);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return true;
    }
}
