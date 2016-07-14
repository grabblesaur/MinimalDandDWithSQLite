package com.example.qqq.minimaldanddwithsqlite.database;

import android.app.IntentService;
import android.content.Intent;

import com.example.qqq.minimaldanddwithsqlite.model.Recipe;
import com.example.qqq.minimaldanddwithsqlite.model.Recipes;

import java.util.ArrayList;
import java.util.List;

public class LoadDataIntentService extends IntentService {
    private List<Recipe> mRecipeList = new ArrayList<>();

    public LoadDataIntentService() {
        super("LoadDataIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        settingUpRecipeList();
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
}
