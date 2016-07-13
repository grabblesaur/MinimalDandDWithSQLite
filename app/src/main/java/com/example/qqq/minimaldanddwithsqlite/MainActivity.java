package com.example.qqq.minimaldanddwithsqlite;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.qqq.minimaldanddwithsqlite.model.MyItem;
import com.example.qqq.minimaldanddwithsqlite.model.Recipes;
import com.example.qqq.minimaldanddwithsqlite.ui.MyAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<MyItem> mItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        settingUpModel();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Setup D&D feature and RecyclerView
        RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();

        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dragMgr.createWrappedAdapter(new MyAdapter(mItems)));

        dragMgr.attachRecyclerView(recyclerView);

        Snackbar.make(findViewById(R.id.main_container), "TIP: Long press item to initiate Drag & Drop action!", Snackbar.LENGTH_LONG).show();
    }

    private void settingUpModel() {
        mItems = new ArrayList<>();
        int length = Recipes.names.length;

        for (int i = 0; i < length; i++) {
            String name = Recipes.names[i];
            int resourceId = Recipes.resourceIds[i];
            String ingredients = Recipes.ingredients[i];
            String directions = Recipes.directions[i];

            MyItem item = new MyItem(i, name, resourceId, ingredients, directions);
            mItems.add(item);
        }
    }

}
