package com.example.qqq.minimaldanddwithsqlite.database;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.qqq.minimaldanddwithsqlite.model.Recipe;

import java.util.List;

public class SaveDataIntentService extends IntentService{

    public SaveDataIntentService() {
        super("SaveDataIntentService");
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<Recipe> recipeList = intent.getParcelableArrayListExtra(Recipe.KEY_LIST);
        saveData(recipeList);
        Log.d(DatabaseManager.TAG, "SaveDataIntentState: all data was successfully saved");
    }

    private void saveData(List<Recipe> recipeList) {
        DatabaseHelper helper = new DatabaseHelper(this);
        DatabaseManager databaseManager = new DatabaseManager(helper);

        databaseManager.removeRecipes();
        databaseManager.insertRecipes(recipeList);
        databaseManager.readRecipes();

        databaseManager.getDatabase().close();
        helper.close();
    }
}
