package com.example.qqq.minimaldanddwithsqlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "cooker.db";

    public static final String RECIPES_TABLE = "recipes";
    public static final String RECIPES_ID = "_id";
    public static final String RECIPES_NAME = "name";
    public static final String RECIPES_RESOURCE_IDS = "resourceId";
    public static final String RECIPES_INGREDIENTS = "ingredients";
    public static final String RECIPES_DIRECTIONS = "directions";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCreateTable = String.format(
                "CREATE TABLE %s(%s integer, %s text, %s integer, %s text, %s text)",
                RECIPES_TABLE, RECIPES_ID, RECIPES_NAME,
                RECIPES_RESOURCE_IDS, RECIPES_INGREDIENTS, RECIPES_DIRECTIONS);
        sqLiteDatabase.execSQL(queryCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RECIPES_TABLE);
        onCreate(sqLiteDatabase);
    }
}
