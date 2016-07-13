package com.example.qqq.minimaldanddwithsqlite.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.qqq.minimaldanddwithsqlite.model.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DatabaseManager {

    private static final String TAG = DatabaseManager.class.getSimpleName();

    private SQLiteDatabase mDatabase;

    public DatabaseManager(DatabaseHelper helper) {
        mDatabase = helper.getWritableDatabase();
    }

    public void insertRecipes(List<Recipe> recipeList) {
        for (int position = 0; position < recipeList.size(); position++) {
            Recipe currentRecipe = recipeList.get(position);

            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.RECIPES_ID, currentRecipe.getId());
            contentValues.put(DatabaseHelper.RECIPES_NAME, currentRecipe.getName());
            contentValues.put(DatabaseHelper.RECIPES_RESOURCE_IDS, currentRecipe.getResourceId());
            contentValues.put(DatabaseHelper.RECIPES_INGREDIENTS, currentRecipe.getIngredients());
            contentValues.put(DatabaseHelper.RECIPES_DIRECTIONS, currentRecipe.getDirections());

            mDatabase.insert(DatabaseHelper.RECIPES_TABLE, null, contentValues);
            Log.d(TAG, "Recipe " + currentRecipe.getName() + " was successfully added in DB");
        }
    }

    public void removeRecipes() {
        mDatabase.delete(DatabaseHelper.RECIPES_TABLE, null, null);
        readRecipes();
        Log.d(TAG, "All rows are successfully removed");
    }

    public int readRecipes() {
        Cursor cursor = mDatabase.query(DatabaseHelper.RECIPES_TABLE,
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.RECIPES_ID);
            int idName = cursor.getColumnIndex(DatabaseHelper.RECIPES_NAME);
            int idResource = cursor.getColumnIndex(DatabaseHelper.RECIPES_RESOURCE_IDS);
            int idIngredients = cursor.getColumnIndex(DatabaseHelper.RECIPES_INGREDIENTS);
            int idDirections = cursor.getColumnIndex(DatabaseHelper.RECIPES_DIRECTIONS);

            do {
                String resultQuery = String.format(Locale.US, "ID = %d, NAME = %s, RESOURCE_ID = %s",
                        cursor.getInt(idIndex), cursor.getString(idName), cursor.getInt(idResource));
                Log.d(TAG, resultQuery);
            } while (cursor.moveToNext());

        } else {
            Log.d(TAG, "0 rows");
            return 0;
        }

        cursor.close();

        return 1;
    }

    public List<Recipe> getRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        Recipe currentRecipe;

        Cursor cursor = mDatabase.query(DatabaseHelper.RECIPES_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DatabaseHelper.RECIPES_ID);
            int idName = cursor.getColumnIndex(DatabaseHelper.RECIPES_NAME);
            int idResource = cursor.getColumnIndex(DatabaseHelper.RECIPES_RESOURCE_IDS);
            int idIngredients = cursor.getColumnIndex(DatabaseHelper.RECIPES_INGREDIENTS);
            int idDirections = cursor.getColumnIndex(DatabaseHelper.RECIPES_DIRECTIONS);

            do {
                currentRecipe = new Recipe(
                        cursor.getInt(idIndex),
                        cursor.getString(idName),
                        cursor.getInt(idResource),
                        cursor.getString(idIngredients),
                        cursor.getString(idDirections));
                recipeList.add(currentRecipe);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "DatabaseManager.getRecipeList(): recipeList = " + recipeList);

        cursor.close();
        return recipeList;
    }

    public SQLiteDatabase getDatabase() {
        return mDatabase;
    }
}
