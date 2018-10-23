package com.example.patel.bakingapp.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by natan on 12/16/2017.
 */

public class Recepie implements Parcelable {

    private String id;
    private String name;

    private String servings;
    private String image;
    private ArrayList<Ingredients> ingredients;
    private ArrayList<Steps> steps;


    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Steps> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Steps> steps) {
        this.steps = steps;
    }

    public Recepie(String id, String name, ArrayList<Ingredients> ingredients, ArrayList<Steps> steps, String servings, String image) {
        this.id = id;
        this.name = name;
        this.servings = servings;

        this.image = image;
        this.ingredients = ingredients;
        this.steps = steps;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.servings);
        dest.writeString(this.image);
        dest.writeTypedList(this.ingredients);
        dest.writeTypedList(this.steps);
    }

    protected Recepie(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.servings = in.readString();
        this.image = in.readString();
        this.ingredients = in.createTypedArrayList(Ingredients.CREATOR);
        this.steps = in.createTypedArrayList(Steps.CREATOR);
    }

    public static final Creator<Recepie> CREATOR = new Creator<Recepie>() {
        @Override
        public Recepie createFromParcel(Parcel source) {
            return new Recepie(source);
        }

        @Override
        public Recepie[] newArray(int size) {
            return new Recepie[size];
        }
    };
}
