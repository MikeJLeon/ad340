package com.example.mike.mikeleonad340;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

public class sharedPrefInstrumental {
    Intent intent;
    private static final String PREFS_NAME = "storeInput";
    private static final String KEY_PREF = "input";
    SharedPreferences inputPrefs;
    SharedPreferences.Editor editor;

    @Rule
    public ActivityTestRule<MainActivity> m = new ActivityTestRule<>(
            MainActivity.class,
            true,
            false);
    @Before
    public void setUp(){
        intent = new Intent();
        Context context = getInstrumentation().getTargetContext();

        // create a SharedPreferences editor
        inputPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
    @Test
    public void populateInputText() {
        String test = "test";

        // Set SharedPreferences data
        inputPrefs.edit().putString(KEY_PREF, test).apply();
        String test2 = inputPrefs.getString(KEY_PREF, "");
        // Launch activity
        m.launchActivity(intent);
        //Test if inputPrefs in test2 = to string test
        assertEquals(test, test2);
    }
    @Test
    public void renderTest(){
        m.launchActivity(intent);
        //Check if button or input is displayed
        onView(withId(R.id.button)).check(matches(isDisplayed()));
        onView(withId(R.id.input)).check(matches(isDisplayed()));
    }
    @Test
    public void submitTestPass() {
        m.launchActivity(intent);
        //Submit text
        onView(withId(R.id.input))
                .perform(typeText("Let's Rock"), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
    }
    @Test
    public void submitTestFail() {
        m.launchActivity(intent);
        //Submit text
        onView(withId(R.id.input))
                .perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());
    }
    @After
    public void tearDown(){
        Context context = getInstrumentation().getTargetContext();
        inputPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = inputPrefs.edit();
        //Destroy the sharedPReferences after testing.
        editor.clear();
        editor.commit();
    }
}