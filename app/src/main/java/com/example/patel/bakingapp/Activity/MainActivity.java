package com.example.patel.bakingapp.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.patel.bakingapp.Fragments.FragmentMain;
import com.example.patel.bakingapp.R;

public class MainActivity extends AppCompatActivity {

    public static boolean isTablet = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            if (findViewById(R.id.tablet_view) != null) {
                isTablet = true;
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentMain recipeFragment = new FragmentMain();
                fragmentManager.beginTransaction()
                        .add(R.id.tablet_view, recipeFragment)
                        .commit();
            } else {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentMain recipeFragment = new FragmentMain();
                fragmentManager.beginTransaction()
                        .add(R.id.phone_view, recipeFragment)
                        .commit();
            }
        }

    }


}
