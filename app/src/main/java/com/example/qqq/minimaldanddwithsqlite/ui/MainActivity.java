package com.example.qqq.minimaldanddwithsqlite.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.qqq.minimaldanddwithsqlite.R;
import com.example.qqq.minimaldanddwithsqlite.database.DatabaseHelper;
import com.example.qqq.minimaldanddwithsqlite.database.DatabaseManager;
import com.example.qqq.minimaldanddwithsqlite.database.SaveDataIntentService;
import com.example.qqq.minimaldanddwithsqlite.model.Recipe;
import com.example.qqq.minimaldanddwithsqlite.model.Recipes;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Recipe> mRecipeList;
    private MyAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingUpRecipeList();
//        Intent intent = new Intent(this, LoadDataIntentService.class);
//        startService(intent);

        settingUpRecyclerView();
    }

    public void settingUpRecipeList() {
        DatabaseHelper helper = new DatabaseHelper(this);
        DatabaseManager databaseManager = new DatabaseManager(helper);

        if (databaseManager.readRecipes() == 0) {
            mRecipeList = settingUpModel();
        } else {
            mRecipeList = databaseManager.getRecipes();
        }
        databaseManager.getDatabase().close();
        helper.close();
    }

    private List<Recipe> settingUpModel() {

        List<Recipe> recipeList = new ArrayList<>();
        int length = Recipes.names.length;

        for (int i = 0; i < length; i++) {

            String name = Recipes.names[i];
            int resourceId = Recipes.resourceIds[i];
            String ingredients = Recipes.ingredients[i];
            String directions = Recipes.directions[i];

            Recipe item = new Recipe(i, name, resourceId, ingredients, directions);
            recipeList.add(item);
        }
        return recipeList;
    }

    private void settingUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Setup D&D feature and RecyclerView
        RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();
        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyAdapter(mRecipeList);
        recyclerView.setAdapter(dragMgr.createWrappedAdapter(mAdapter));

        dragMgr.attachRecyclerView(recyclerView);

        Snackbar.make(findViewById(R.id.main_container),
                "TIP: Long press item to initiate Drag & Drop action!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        Intent intent = new Intent(this, SaveDataIntentService.class);
        intent.putParcelableArrayListExtra(Recipe.KEY_LIST,
                (ArrayList<? extends Parcelable>) mAdapter.getRecipeList());

        startService(intent);
    }
}
