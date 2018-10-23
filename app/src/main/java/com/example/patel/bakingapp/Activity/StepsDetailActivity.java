package com.example.patel.bakingapp.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.patel.bakingapp.Fragments.FragmentDetailActivity;
import com.example.patel.bakingapp.Fragments.FragmentStepsDetailActivity;
import com.example.patel.bakingapp.R;


public class StepsDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (savedInstanceState == null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            if (MainActivity.isTablet) {
                FragmentDetailActivity fragmentDetailActivity = new FragmentDetailActivity();
                fragmentManager.beginTransaction()
                        .add(R.id.steps_details_frame, fragmentDetailActivity)
                        .commit();

                FragmentStepsDetailActivity fragmentStepsDetailActivity = new FragmentStepsDetailActivity();
                fragmentManager.beginTransaction()
                        .replace(R.id.detail_activity_layout, fragmentStepsDetailActivity)
                        .commit();
            } else {


                FragmentStepsDetailActivity fragmentStepsDetailActivity = new FragmentStepsDetailActivity();
                fragmentManager.beginTransaction()
                        .add(R.id.detail_activity_layout, fragmentStepsDetailActivity)
                        .commit();

            }


        }
    }
}




