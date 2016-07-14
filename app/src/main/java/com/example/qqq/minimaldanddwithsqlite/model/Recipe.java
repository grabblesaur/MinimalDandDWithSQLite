package com.example.qqq.minimaldanddwithsqlite.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable{
    public static final String KEY_LIST = "key for list";

    private long id;
    private String name;
    private int resourceId;
    private String ingredients;
    private String directions;

    public Recipe(long id, String name, int resourceId, String ingredients, String directions) {
        this.id = id;
        this.name = name;
        this.resourceId = resourceId;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    protected Recipe(Parcel in) {
        id = in.readLong();
        name = in.readString();
        resourceId = in.readInt();
        ingredients = in.readString();
        directions = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeInt(resourceId);
        parcel.writeString(ingredients);
        parcel.writeString(directions);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }
}
