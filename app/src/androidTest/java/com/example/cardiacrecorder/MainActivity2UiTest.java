package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class MainActivity2UiTest {
    @Rule
    public ActivityScenarioRule<MainActivity2>activityScenarioRule
            = new ActivityScenarioRule<MainActivity2>(MainActivity2.class);

    @Test
    public void SignInTest()
    {
        onView(withId(R.id.main_activity2)).check(matches(isDisplayed()));
        onView(withId(R.id.m1)).perform(ViewActions.typeText("Mukim"));
        onView(withId(R.id.m2)).perform(ViewActions.typeText("mukim2@gmail.com"));
        onView(withId(R.id.m3)).perform(ViewActions.typeText("mukim@12"));
        Espresso.pressBack();
        onView(withId(R.id.m42)).perform(click());
        //onView(withId(R.id.main_activity2)).check(matches(isDisplayed()));
    }

}
