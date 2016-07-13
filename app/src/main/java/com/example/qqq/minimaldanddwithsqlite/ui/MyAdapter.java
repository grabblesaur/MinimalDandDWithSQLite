package com.example.qqq.minimaldanddwithsqlite.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qqq.minimaldanddwithsqlite.R;
import com.example.qqq.minimaldanddwithsqlite.model.Recipe;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
        implements DraggableItemAdapter<MyAdapter.MyViewHolder>{
    List<Recipe> mRecipeList;

    public MyAdapter(List<Recipe> recipeList) {
        setHasStableIds(true);
        mRecipeList = recipeList;
    }

    public class MyViewHolder  extends AbstractDraggableItemViewHolder{
        ImageView imageView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_iv_image);
            textView = (TextView) itemView.findViewById(R.id.item_tv_text);
        }
    }

    // Methods of RecyclerView

    @Override
    public long getItemId(int position) {
        return mRecipeList.get(position).getId();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Recipe item = mRecipeList.get(position);

        holder.imageView.setImageResource(item.getResourceId());
        holder.textView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
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
        Recipe movedItem = mRecipeList.remove(fromPosition);
        mRecipeList.add(toPosition, movedItem);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return true;
    }

    public List<Recipe> getRecipeList() {
        return mRecipeList;
    }
}
