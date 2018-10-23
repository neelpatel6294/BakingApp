package com.example.patel.bakingapp.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by natan on 12/17/2017.
 */

public class Ingredients implements Parcelable {

    private String quantity;
    private String measure;
    private String ingredient;

    public Ingredients(JSONObject ingredientsObj){
        try {
            this.quantity=ingredientsObj.getString("quantity");
            this.measure=ingredientsObj.getString("measure");
            this.ingredient=ingredientsObj.getString("ingredient");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Ingredients(String quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.quantity);
        dest.writeString(this.measure);
        dest.writeString(this.ingredient);
    }

    protected Ingredients(Parcel in) {
        this.quantity = in.readString();
        this.measure = in.readString();
        this.ingredient = in.readString();
    }

    public static final Parcelable.Creator<Ingredients> CREATOR = new Parcelable.Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel source) {
            return new Ingredients(source);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };
}
