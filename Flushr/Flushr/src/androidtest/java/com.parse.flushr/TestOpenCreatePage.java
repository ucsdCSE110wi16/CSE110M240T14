/**
 * Created by nickge on 3/4/16.
 */
package com.parse.flushr;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.*;

@RunWith(AndroidJUnit4.class)
public class TestOpenCreatePage {

    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void CreatePageCheck() {

        try {
            Thread.sleep(3000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        onView(withId((R.id.createButton))).check(matches(isDisplayed()));

        try {
            Thread.sleep(3000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        onView(allOf(withId(R.id.createButton),FirstViewMatcher.firstView())).perform(click());

    }
}

