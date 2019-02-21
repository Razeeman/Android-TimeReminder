package com.example.util.timereminder;

import com.example.util.timereminder.ui.main.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class SettingsTest {

    @Rule
    public final ActivityScenarioRule<MainActivity> mActivityTestRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        TestUtils.clearPreferences();
        mActivityTestRule.getScenario().moveToState(Lifecycle.State.RESUMED);
    }

    @Test
    public void settingsAreNotSetUp() {
        TestUtils.navigateToSettings(); // TODO workaround?
        pressBack();

        onView(withText(R.string.no_data_available)).check(matches(isDisplayed()));
        onView(withText(R.string.footer_label)).check(matches(not(isDisplayed())));

        TestUtils.navigateToSettings();

        onView(allOf(
                hasSibling(withText(R.string.prefs_date_of_birth_title)),
                withText(R.string.prefs_not_set)))
                .check(matches(isDisplayed()));
        onView(allOf(
                hasSibling(withText(R.string.prefs_life_expectancy_title)),
                withText(R.string.prefs_not_set)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void setUpSettings() {
        TestUtils.navigateToSettings();

        TestUtils.actionSetDate(R.string.prefs_date_of_birth_title, 1990, 1, 1);

        pressBack();

        onView(withText(R.string.no_data_available)).check(matches(isDisplayed()));

        TestUtils.navigateToSettings();

        TestUtils.actionTypeText(R.string.prefs_life_expectancy_title, "100");

        pressBack();

        onView(withText(R.string.no_data_available)).check(matches(not(isDisplayed())));
        onView(withId(R.id.tv_seconds_left)).check(matches(isDisplayed()));
        onView(withText(R.string.seconds_label)).check(matches(isDisplayed()));
        onView(withText(R.string.footer_label)).check(matches(isDisplayed()));
    }

    @Test
    public void visibilitySettingsInitial() {
        TestUtils.navigateToSettings();
        TestUtils.inputPreferences();

        pressBack();

        onView(withId(R.id.tv_minutes_left)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.tv_hours_left)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.tv_days_left)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.tv_years_left)).perform(scrollTo()).check(matches(isDisplayed()));
    }

    @Test
    public void visibilitySettings() {
        TestUtils.navigateToSettings();
        TestUtils.inputPreferences();

        TestUtils.actionClickOnPreferenceWithTitle(R.string.prefs_show_minutes_title);
        pressBack();
        onView(withId(R.id.tv_minutes_left)).check(matches(not(isDisplayed())));

        TestUtils.navigateToSettings();
        TestUtils.actionClickOnPreferenceWithTitle(R.string.prefs_show_hours_title);
        pressBack();
        onView(withId(R.id.tv_hours_left)).check(matches(not(isDisplayed())));

        TestUtils.navigateToSettings();
        TestUtils.actionClickOnPreferenceWithTitle(R.string.prefs_show_days_title);
        pressBack();
        onView(withId(R.id.tv_days_left)).check(matches(not(isDisplayed())));

        TestUtils.navigateToSettings();
        TestUtils.actionClickOnPreferenceWithTitle(R.string.prefs_show_years_title);
        pressBack();
        onView(withId(R.id.tv_years_left)).check(matches(not(isDisplayed())));
    }

    @Test
    public void incorrectInput() {
        TestUtils.navigateToSettings();

        TestUtils.actionTypeText(R.string.prefs_life_expectancy_title, "1000");

        TestUtils.toastTextShowing(R.string.incorrect_input);
    }

}
