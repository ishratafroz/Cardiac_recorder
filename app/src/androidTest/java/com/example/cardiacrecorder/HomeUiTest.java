package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class HomeUiTest {
    @Rule
    public ActivityScenarioRule<home> activityRule =
            new ActivityScenarioRule<>(home.class);

    @Test
    public void testActivityName()
    {
        onView(withText("Cardiacrecorder")).check(matches(isDisplayed()));
    }

    @Test
    public void testAddButton()
    {

        onView(withId(R.id.cnt3)).perform(click());
        onView(withId(R.id.customdialog)).check(matches(isDisplayed()));
        onView(withId(R.id.syst)).perform(ViewActions.typeText("120"));
        onView(withId(R.id.diast)).perform(ViewActions.typeText("80"));
        onView(withId(R.id.pulse_rate)).perform(ViewActions.typeText("65"));

        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        String date_v = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        String time_v = simpleDateFormat.format(calendar.getTime());

        onView(withId(R.id.comments)).perform(ViewActions.typeText("Resting"));
        onView(withText(date_v)).check(matches(isDisplayed()));
        onView(withText(time_v)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.yes_btn)).perform(click());
        //onView(withId(R.id.status)).check(matches(isDisplayed()));
    }
    @Test
    public void testRecordButton()
    {
        onView(withId(R.id.cnt4)).perform(click());
        onView(withText("Cardiacrecorder")).check(matches(isDisplayed()));
    }
}
