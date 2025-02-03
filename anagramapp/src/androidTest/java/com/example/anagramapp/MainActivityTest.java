package com.example.anagramapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAnagramTransformation() {

        onView(withId(R.id.anagramInput)).perform(clearText());

        onView(withId(R.id.anagramInput))
                .perform(typeText("abcd efgh"), closeSoftKeyboard());

        onView(withId(R.id.anagramResult))
                .check(matches(withText("dcba hgfe")));
    }

    @Test
    public void testAnagramWithDigitsAndPunctuation() {
        onView(withId(R.id.anagramInput)).perform(clearText());

        onView(withId(R.id.anagramInput))
                .perform(typeText("a1bcd efg!h"), closeSoftKeyboard());

        onView(withId(R.id.anagramResult))
                .check(matches(withText("d1cba hgf!e")));
    }

    @Test
    public void testAnagramWithFilter() {
        onView(withId(R.id.anagramInput)).perform(clearText());
        onView(withId(R.id.anagramFilter)).perform(clearText());

        onView(withId(R.id.anagramFilter))
                .perform(typeText("xl"), closeSoftKeyboard());

        onView(withId(R.id.anagramInput))
                .perform(typeText("Foxminded cool 24/7"), closeSoftKeyboard());

        onView(withId(R.id.anagramResult))
                .check(matches(withText("dexdnimoF oocl 7/42")));
    }
}
