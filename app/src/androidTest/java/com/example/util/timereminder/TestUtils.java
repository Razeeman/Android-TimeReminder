package com.example.util.timereminder;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.WindowManager;
import android.widget.DatePicker;

import com.example.util.timereminder.data.prefs.PreferencesHelper;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Root;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.equalTo;

@SuppressWarnings("SameParameterValue")
class TestUtils {
    static void navigateToSettings() {
        onView(withId(R.id.menu_settings)).perform(click());
    }

    static void clearPreferences() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Resources resources = context.getResources();
        PreferencesHelper preferencesHelper = new PreferencesHelper(sharedPreferences, resources);
        preferencesHelper.clear();
    }

    static void actionSetDate(int preferenceTextId, int year, int month, int day) {
        onView(withText(preferenceTextId)).perform(click());
        onView(withClassName(equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month, day));
        onView(withId(android.R.id.button1)).perform(click());
    }

    static void actionTypeText(int preferenceTextId, String text) {
        onView(withText(preferenceTextId)).perform(click());
        onView(withId(android.R.id.edit)).perform(typeText(text));
        onView(withId(android.R.id.button1)).perform(click());
    }

    static void actionClickOnPreferenceWithTitle(int preferenceTitleId) {
        onView(withClassName(equalTo(RecyclerView.class.getName())))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(preferenceTitleId))));
        onView(withText(preferenceTitleId)).perform(click());
    }

    static void inputPreferences() {
        actionSetDate(R.string.prefs_date_of_birth_title, 2000, 1, 1);
        actionTypeText(R.string.prefs_life_expectancy_title, "50");
    }

    static void toastTextShowing(int resourceId) {
        onView(withText(resourceId)).inRoot(isToast()).check(matches(isDisplayed()));
    }

    private static Matcher<Root> isToast() {
        return new TypeSafeMatcher<Root>() {
            @Override
            protected boolean matchesSafely(Root item) {
                int type = item.getWindowLayoutParams().get().type;
                return type == WindowManager.LayoutParams.TYPE_TOAST;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("is toast");
            }
        };
    }

    static <T extends Activity> void rotateOrientation (ActivityScenarioRule<T> activityScenarioRule) {
        activityScenarioRule.getScenario().onActivity(TestUtils::rotateOrientation);

        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
    }

    private static void rotateOrientation(Activity activity) {
        int currentOrientation = activity.getResources().getConfiguration().orientation;
        final int newOrientation = (currentOrientation == Configuration.ORIENTATION_PORTRAIT) ?
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE :
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        activity.setRequestedOrientation(newOrientation);
    }
}
