package com.example.patel.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patel.bakingapp.Pojo.Recepie;
import com.example.patel.bakingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by natan on 12/16/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    ArrayList<Recepie> mRecepies;
    final private ListItemClickListener mListItemClickListener;


    //Interface

    public interface ListItemClickListener {

        void onListItemClick(Recepie recepie);

    }

    public RecipeAdapter(ArrayList<Recepie> recepies, ListItemClickListener listItemClickListener) {
        mListItemClickListener = listItemClickListener;
        mRecepies = recepies;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_custom2, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Recepie recepie = mRecepies.get(position);
        Context context = holder.img.getContext();

        if (recepie.getImage().isEmpty())

        {
            holder.img.setImageResource(R.drawable.recipe_icon);

        } else {
            Picasso.with(context)
                    .load(recepie.getImage())
                    .placeholder(R.drawable.recipe_icon)
                    .error(R.drawable.recipe_icon)
                    .into(holder.img);

        }




        holder.txt_name.setText(recepie.getName());
        holder.txt_serving.setText("Servings : " + recepie.getServings());
        Log.i("serving21", String.valueOf(recepie.getServings()));
        holder.bind(mRecepies.get(position), mListItemClickListener);


    }

    @Override
    public int getItemCount() {
        return mRecepies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.imageView)
        ImageView img;
        @BindView(R.id.txt_name)
        TextView txt_name;
        @BindView(R.id.txt_serving)
        TextView txt_serving;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
           /* img = itemView.findViewById(R.id.imageView);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_serving = itemView.findViewById(R.id.txt_serving);*/


        }


        public void bind(final Recepie recepie, final ListItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onListItemClick(recepie);
                }
            });
        }


    }


}





