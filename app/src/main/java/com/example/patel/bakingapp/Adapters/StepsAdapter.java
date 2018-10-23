package com.example.patel.bakingapp.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patel.bakingapp.Pojo.Steps;
import com.example.patel.bakingapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by natan on 12/17/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyViewHolder> {

    ArrayList<Steps> mSteps;


    final private ListItemClickListener mListItemClickListener;


    //Interface

    public interface ListItemClickListener {

        void onListItemClick(int clickedItemIndex);

    }

    public StepsAdapter(ArrayList<Steps> steps, ListItemClickListener listItemClickListener) {
        mSteps = steps;
        mListItemClickListener = listItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.steps_custom, parent, false);

        return new StepsAdapter.MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Steps steps = mSteps.get(position);
        holder.txt_stepId.setText(steps.getId());
        Log.i("121", steps.getId());
        holder.txt_stepDescription.setText(steps.getShortDescription());
        Log.i("121", steps.getShortDescription());

    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txt_step_id)
        TextView txt_stepId;
        @BindView(R.id.txt_step_description)
        TextView txt_stepDescription;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mListItemClickListener.onListItemClick(clickedPosition);

        }
    }


}
