package com.example.util.timereminder;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.example.util.timereminder.data.prefs.PreferencesHelper;
import com.example.util.timereminder.ui.main.MainActivity;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.lifecycle.Lifecycle;
import androidx.preference.PreferenceManager;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    @BeforeClass
    public static void beforeClass() {
        clearPreferences();
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

        navigateToSettings();

        onView(withText(R.id.tv_no_data)).check(doesNotExist());
        onView(withText(R.string.prefs_general_settings_title)).check(matches(isDisplayed()));
    }

    @Test
    public void backFromSettings_toMainScreen() {
        navigateToSettings();

        pressBack();

        onView(withId(R.id.tv_no_data)).check(matches(isDisplayed()));
        onView(withText(R.string.prefs_general_settings_title)).check(doesNotExist());
    }

    private static void navigateToSettings() {
        onView(withId(R.id.menu_settings)).check(matches(isDisplayed())).perform(click());
    }

    private static void clearPreferences() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Resources resources = context.getResources();
        PreferencesHelper preferencesHelper = new PreferencesHelper(sharedPreferences, resources);
        preferencesHelper.clear();
    }

}
