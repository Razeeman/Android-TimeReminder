package com.example.util.timereminder;

import com.example.util.timereminder.ui.main.MainActivity;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    @BeforeClass
    public static void beforeClass() {
        TestUtils.clearPreferences();
    }

    @Rule
    public final ActivityScenarioRule<MainActivity> mActivityTestRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        mActivityTestRule.getScenario().moveToState(Lifecycle.State.RESUMED);
    }

    @Test
    public void mainScreen_toSettingsScreen() {
        onView(withId(R.id.tv_no_data)).check(matches(isDisplayed()));

        TestUtils.navigateToSettings();

        onView(withText(R.id.tv_no_data)).check(doesNotExist());
        onView(withText(R.string.prefs_general_settings_title)).check(matches(isDisplayed()));
    }

    @Test
    public void backFromSettings_toMainScreen() {
        TestUtils.navigateToSettings();

        pressBack();

        onView(withId(R.id.tv_no_data)).check(matches(isDisplayed()));
        onView(withText(R.string.prefs_general_settings_title)).check(doesNotExist());
    }

}
