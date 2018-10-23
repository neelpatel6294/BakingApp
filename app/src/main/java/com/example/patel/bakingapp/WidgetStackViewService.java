package com.example.patel.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.patel.bakingapp.Pojo.Ingredients;
import com.example.patel.bakingapp.Pojo.Recepie;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import timber.log.Timber;

/**
 * Created by natan on 12/22/2017.
 */

public class WidgetStackViewService extends RemoteViewsService {
    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackViewsRemoteFactory(this.getApplicationContext(), intent);
    }
}

class StackViewsRemoteFactory implements RemoteViewsService.RemoteViewsFactory {
    private ArrayList<Recepie> mRecipes;
    private Context mContext;

    public StackViewsRemoteFactory(Context mContext, Intent intent) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        /**
         * In onCreate() you setup any connections / cursors to your data source. Heavy lifting,
         * for example downloading or creating content etc, should be deferred to onDataSetChanged()
         * or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.
         */

        // We sleep for 3 seconds here to show how the empty view appears in the interim.
        // The empty view is set in the StackWidgetProvider and should be a sibling of the
        // collection view.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        // In onDestroy() you should tear down anything that was setup for your data source,
        // eg. cursors, connections, etc.
        mRecipes.clear();
    }

    @Override
    public int getCount() {
        if (mRecipes == null)
            return 0;

        return mRecipes.size();
    }

    /**
     * You can do heaving lifting in here, synchronously. For example, if you need to
     * process an image, fetch something from the network, etc., it is ok to do it here,
     * synchronously. A loading view will show up in lieu of the actual contents in the
     * interim.
     *
     * @param position here used for getting Pojo Recipe Item Positions
     * @return remoteViews
     */
    @Override
    public RemoteViews getViewAt(int position) {
        // Construct a remote views item based on the app widget item XML file,
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list);

        //Recipe Pojo Instence to get all the getters
        Recepie recipe = mRecipes.get(position);

        // and set the text based on the position.
        remoteViews.setTextViewText(R.id.widget_item_recipe_name, recipe.getName());

        //Iterate throught the ingredients
        String ingredients = "";
        for (Ingredients ingredient : recipe.getIngredients()) {
            ingredients += " - " + ingredient.getIngredient() + "\n";
        }

        //Set Ingredient text view from the ingredients
        remoteViews.setTextViewText(R.id.widget_item_ingredients, ingredients);

        // Next, we set a fill-intent which will be used to fill-in the pending intent template
        // which is set on the collection view in BakingWidgetProvider.
        Bundle extras = new Bundle();
        extras.putParcelable("recipe", recipe);
        Intent fillIntent = new Intent();
        fillIntent.putExtras(extras);
        remoteViews.setOnClickFillInIntent(R.id.recipe_widget_item, fillIntent);

        //return the remoteview;
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        // You can create a custom loading view (for instance when getViewAt() is slow.) If you
        // return null here, you will get the default loading view.
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * This is triggered when you call AppWidgetManager notifyAppWidgetViewDataChanged
     * on the collection view corresponding to this factory. You can do heaving lifting in
     * here, synchronously. For example, if you need to process an image, fetch something
     * from the network, etc., it is ok to do it here, synchronously. The widget will remain
     * in its current state while work is being done here, so you don't need to worry about
     * locking up the widget.
     */
    @Override
    public void onDataSetChanged() {
        if (mRecipes == null) {
            HttpURLConnection urlConnection = null;
            try {
                //Recipe Base Url
                final String BASE_URL = "https://go.udacity.com/android-baking-app-json";

                //Creating the Uri
                Uri builtUri = Uri.parse(BASE_URL).buildUpon().build();

                //Creating URL
                URL url = new URL(builtUri.toString());
                Timber.d("Url Created : " + url.toString());

                // Create the request to OpenWeatherMap, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.connect();

                if (urlConnection.getResponseCode() == 200) {
                    // create an input stream reader
                    InputStreamReader inputStream = new InputStreamReader(urlConnection.getInputStream());

                    //getting data from Json using Gson to the recipeArray
                    Recepie[] recipeArray = new Gson().fromJson(inputStream, Recepie[].class);
                    mRecipes = new ArrayList<>(Arrays.asList(recipeArray));
                } else {
                    Timber.d("Error response code: " + urlConnection.getResponseCode());
                }

            } catch (IOException e) {
                // If the code didn't successfully get the Recipe data, there's no point in attemping
                // to parse it.
                Timber.d("IOException: " + e.getMessage());
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
    }

}