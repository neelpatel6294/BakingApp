package com.example.patel.bakingapp.Utils;

import android.net.Uri;
import android.util.Log;

import com.example.patel.bakingapp.Pojo.Ingredients;
import com.example.patel.bakingapp.Pojo.Recepie;
import com.example.patel.bakingapp.Pojo.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by natan on 12/16/2017.
 */

public class NetworkUtils {

    final static String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";


    public static ArrayList<Recepie> fetchRecipeData(URL url) throws JSONException {

        String jsonResponse = null;
        try {
            jsonResponse = getResponseFromHttpUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Recepie> recepies = extractFeaturesFromJson(jsonResponse);
        return recepies;

    }


    public static URL buildURl() {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
            Log.i("url21", String.valueOf(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static ArrayList<Recepie> extractFeaturesFromJson(String baking) throws JSONException {

        ArrayList<Recepie> recepies = new ArrayList<>();


        //CONSTANTS for the JSON Objects
        final String RECIPE_ID = "id";
        final String RECIPE_NAME = "name";
        final String RECIPE_INGREDIENTS = "ingredients";
        final String INGREDIENTS_QUANTIFY = "quantity";
        final String INGREDIENTS_MEASURE = "measure";
        final String INGREDIENTS_INGREDIENT = "ingredient";
        final String RECIPE_STEPS = "steps";
        final String STEPS_ID = "id";
        final String STEPS_SHORT_DESCRP = "shortDescription";
        final String STEPS_DESCRP = "description";
        final String STEPS_VIDEO_PATH = "videoURL";
        final String STEPS_THUMBNAIL = "thumbnailURL";
        final String RECIPE_SERVINGS = "servings";
        final String RECIPE_IMAGE = "image";

        JSONArray recipeArray = new JSONArray(baking);

        for (int i = 0; i < recipeArray.length(); i++) {
            JSONObject recipeJsonObject = recipeArray.optJSONObject(i);
            String recipeId = recipeJsonObject.getString(RECIPE_ID);
            String recipeName = recipeJsonObject.getString(RECIPE_NAME);
            String recipeServings = recipeJsonObject.getString((RECIPE_SERVINGS));
            String recipeImage = recipeJsonObject.getString(RECIPE_IMAGE);
            Log.i("tagu21", recipeId + recipeName);


            ArrayList<Ingredients> ingredientList = new ArrayList<>();
            ArrayList<Steps> stepsList = new ArrayList<>();


            //Ingredients Json Array
            JSONArray ingredientJsonArray = recipeJsonObject.optJSONArray(RECIPE_INGREDIENTS);

            //Iterate through the Ingredient Array
            for (int j = 0; j < ingredientJsonArray.length(); j++) {
                JSONObject ingredientsObjects = ingredientJsonArray.getJSONObject(j);

                String ingredientQuantity = ingredientsObjects.getString(INGREDIENTS_QUANTIFY);
                String ingredientMeasure = ingredientsObjects.getString(INGREDIENTS_MEASURE);
                String ingredientIngredients = ingredientsObjects.getString(INGREDIENTS_INGREDIENT);

                //Creating Ingredients Object
                Ingredients ingredients = new Ingredients(ingredientQuantity, ingredientMeasure, ingredientIngredients);

                ingredientList.add(ingredients);
            }

            //Steps Json Array
            JSONArray stepsJsonArray = recipeJsonObject.optJSONArray(RECIPE_STEPS);

            //Iterate through the Step Array
            for (int k = 0; k < stepsJsonArray.length(); k++) {
                JSONObject stepObject = stepsJsonArray.getJSONObject(k);

                String stepsId = stepObject.getString(STEPS_ID);
                String stepsSdescrp = stepObject.getString(STEPS_SHORT_DESCRP);
                String stepsDescrp = stepObject.getString(STEPS_DESCRP);
                String stepsVpath = stepObject.getString(STEPS_VIDEO_PATH);
                String stepsThumnail = stepObject.getString(STEPS_THUMBNAIL);

                //Creating Step Object
                Steps stepsObj = new Steps(stepsId, stepsSdescrp, stepsDescrp, stepsVpath, stepsThumnail);

                stepsList.add(stepsObj);
            }

            Recepie recipeObj = new Recepie(recipeId, recipeName, ingredientList, stepsList, recipeServings, recipeImage);
            recepies.add(recipeObj);

        }


        return recepies;

    }


}
