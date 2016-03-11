/**
 * Created by nickge on 3/4/16.
 */
package com.parse.flushr;

import android.location.Location;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)

public class TestPositioning {

    private static String MISSING_INFO = "There were an missing information, please be completed";

    @Rule
    public ActivityTestRule<MapsActivity> myActivityRule = new ActivityTestRule<>(
            MapsActivity.class);
    @Test
    public void PositioningCheck() {

        try {
            Thread.sleep(3000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        Location loc = new Location("AnyProvider");
        loc.setLatitude(32.8810567);
        loc.setLongitude(-117.2326976);

        try {
            Thread.sleep(3000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.helper)).check(matches(isDisplayed()));

        try {
            Thread.sleep(3000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.createButton)).check(matches(isDisplayed()));

    }
}
