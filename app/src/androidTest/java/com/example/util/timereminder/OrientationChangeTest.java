package com.example.util.timereminder;

import com.example.util.timereminder.ui.main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class OrientationChangeTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        TestUtils.clearPreferences();
        // Recreate activity after orientation change.
        mActivityTestRule.getScenario().recreate();
        mActivityTestRule.getScenario().moveToState(Lifecycle.State.RESUMED);
        TestUtils.navigateToSettings();
        TestUtils.inputPreferences();
        pressBack();
    }

    @Test
    public void rotateInMainScreen() {
        onView(withId(R.id.tv_seconds_left)).perform(scrollTo()).check(matches(isDisplayed()));

        TestUtils.rotateOrientation(mActivityTestRule);

        onView(withId(R.id.tv_seconds_left)).perform(scrollTo()).check(matches(isDisplayed()));

        TestUtils.rotateOrientation(mActivityTestRule);

        onView(withId(R.id.tv_seconds_left)).perform(scrollTo()).check(matches(isDisplayed()));
    }

    @Test
    public void rotateInSettingsScreen() {
        TestUtils.navigateToSettings();

        onView(withText(R.string.prefs_general_settings_title)).check(matches(isDisplayed()));

        TestUtils.rotateOrientation(mActivityTestRule);

        onView(withText(R.string.prefs_general_settings_title)).check(matches(isDisplayed()));

        TestUtils.rotateOrientation(mActivityTestRule);

        onView(withText(R.string.prefs_general_settings_title)).check(matches(isDisplayed()));
    }
}
