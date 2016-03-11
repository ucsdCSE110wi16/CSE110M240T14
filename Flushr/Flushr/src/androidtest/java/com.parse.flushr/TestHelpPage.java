/**
 * Created by nickge on 3/4/16.
 */
package com.parse.flushr;

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

public class TestHelpPage {
    @Rule
    public ActivityTestRule<HelpActivity> mActivityRule = new ActivityTestRule<>(
            HelpActivity.class);

    @Test
    public void HelpPageCheck() {

        try {
            Thread.sleep(3000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        ViewActions.scrollTo();
        ViewActions.swipeDown();

        try {
            Thread.sleep(3000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        ViewActions.swipeUp();
        ViewActions.swipeDown();
    }
}
