package com.example.patel.bakingapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patel.bakingapp.Pojo.Ingredients;
import com.example.patel.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by natan on 12/17/2017.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {

    ArrayList<Ingredients> mIngredients;

    public IngredientsAdapter(ArrayList<Ingredients> ingredients) {
        mIngredients = ingredients;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients_list_custom, parent, false);

        return new IngredientsAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        Ingredients ingredients = mIngredients.get(position);
        holder.txt_ingredients.setText(ingredients.getIngredient());
        Log.i("ingi21",ingredients.getIngredient());
        holder.txt_measure.setText(ingredients.getMeasure());
        Log.i("ingi21",ingredients.getMeasure());
        holder.txt_quantity.setText(ingredients.getQuantity());
        Log.i("ingi21",ingredients.getQuantity());

    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ingredient)
        TextView txt_ingredients;
        @BindView(R.id.quantity)
        TextView txt_quantity;
        @BindView(R.id.measure)
        TextView txt_measure;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
