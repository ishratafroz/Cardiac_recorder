package com.example.cardiacrecorder;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

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

public class RecordTest {
    @Rule
    public ActivityScenarioRule<Record> activityRule =
            new ActivityScenarioRule<>(Record.class);


    @Test
    public void testUpdate()
    {
        onView(withId(R.id.listview1)).check(matches(isDisplayed()));
//        onData(anything()).inAdapterView(withId(R.id.list_view)).atPosition(1).
//                check(matches((withText("Systol"))));
        onData(anything()).inAdapterView(withId(R.id.listview1)).atPosition(0).perform(click());
        onView(withId(R.id.cust)).check(matches(isDisplayed()));
        onView(withId(R.id.custom1)).perform(click());
        onView(withId(R.id.customdialog)).check(matches(isDisplayed()));
        onView(withId(R.id.no_btn)).perform(click());
        onView(withId(R.id.cust)).check(matches(isDisplayed()));
        onView(withId(R.id.custom1)).perform(click());
        onView(withId(R.id.customdialog)).check(matches(isDisplayed()));
        onView(withId(R.id.syst)).perform(ViewActions.typeText("100"));
        onView(withId(R.id.diast)).perform(ViewActions.typeText("60"));
        onView(withId(R.id.pulse_rate)).perform(ViewActions.typeText("55"));

        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        String date_v = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        String time_v = simpleDateFormat.format(calendar.getTime());

        onView(withId(R.id.comments)).perform(ViewActions.typeText("Sitting"));
        onView(withText(date_v)).check(matches(isDisplayed()));
        onView(withText(time_v)).check(matches(isDisplayed()));
        Espresso.pressBack();
        onView(withId(R.id.yes_btn)).perform(click());
        //onView(withId(R.id.listview1)).check(matches(isDisplayed()));
    }

    @Test
    public void testDelete()
    {
        onView(withId(R.id.listview1)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listview1)).atPosition(0).perform(click());
        onView(withId(R.id.cust)).check(matches(isDisplayed()));
        onView(withId(R.id.custom2)).perform(click());
    }

    @Test
    public void testNothing(){
        onView(withId(R.id.listview1)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listview1)).atPosition(0).perform(click());
        onView(withId(R.id.cust)).check(matches(isDisplayed()));
        onView(withId(R.id.custom3)).perform(click());
    }
}
