package com.example.patel.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.patel.bakingapp.Activity.MainActivity;

import android.support.test.espresso.contrib.RecyclerViewActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by natan on 12/22/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule
            = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void displayMainRecycler() {
        onView(withId(R.id.recyclerView))
                .check(matches(isDisplayed()));
    }

    @Test
    public void onClick_openStepsFrag() {
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

        onView(withId(R.id.ingredient_list))
                .check(matches(isDisplayed()));

        onView(withId(R.id.recipe_details_steps))
                .check(matches(isDisplayed()));


    }


}
