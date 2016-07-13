package com.example.qqq.minimaldanddwithsqlite.model;

public class Recipe {
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
