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


@RunWith(AndroidJUnit4.class)

public class TestCreateWithUnfilledBlanks {

    private static String MISSING_INFO = "There were an missing information, please be completed";

    @Rule
    public ActivityTestRule<CreatePageActivity> mActivityRule = new ActivityTestRule<>(
            CreatePageActivity.class);
    @Test
    public void CreatePageCheck() {

        try {
            Thread.sleep(4000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        // test create restroom without name
        onView(withId(R.id.checkMale)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.checkMale), FirstViewMatcher.firstView())).perform(click());

        onView(withId((R.id.checkFemale))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.checkFemale), FirstViewMatcher.firstView())).perform(click());

        try {
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.submitButton)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.submitButton), FirstViewMatcher.firstView())).perform(click());

        onView(withText(CreatePageActivity.TOAST_STRING_FAILURE)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

        try {
            Thread.sleep(4000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        //onView(withText(CreatePageActivity.TOAST_STRING_FAILURE)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));

    }

}
