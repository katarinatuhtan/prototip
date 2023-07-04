package com.example.prototip;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.graphics.Color;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleUnitTest {
    @Test
    public void checkWinner_WinningCondition_ShouldBeTrue() {
        MainActivity mainActivity = new MainActivity();

        mainActivity.gameState = new int[]{1, 1, 1, 2, 2, 0, 0, 2, 2};

        Assert.assertTrue(mainActivity.checkWinner());
    }

    @Test
    public void checkWinner_NoWinningCondition_ShouldBeFalse() {
        MainActivity mainActivity = new MainActivity();

        mainActivity.gameState = new int[]{1, 0, 1, 2, 2, 0, 0, 2, 2};

        Assert.assertFalse(mainActivity.checkWinner());
    }

    @Test
    public void checkWinner_IncompleteGameState_ShouldBeFalse() {
        int[] gameState = {1, 1, 2, 2, 0, 0, 2, 2, 2};
        int[][] winningPositions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        MainActivity mainActivity = new MainActivity();
        mainActivity.gameState = gameState;
        mainActivity.winningPositions = winningPositions;

        Assert.assertFalse(mainActivity.checkWinner());
    }

    @Test
    public void playerButtonClick() {
        // Perform a click on a button with index 0
        Espresso.onView(ViewMatchers.withId(R.id.btn_0)).perform(ViewActions.click());

        // Verify that the button text has changed to "X"
        Espresso.onView(ViewMatchers.withId(R.id.btn_0)).check(matches(withText("X")));

        // Verify that the button text color has changed to the expected color
        Espresso.onView(ViewMatchers.withId(R.id.btn_0))
                .check(matches(ViewMatchers.hasTextColor(Color.parseColor("#778899"))));

        // Verify that the player status text is displayed and shows the expected text
        Espresso.onView(ViewMatchers.withId(R.id.playerStatus)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.playerStatus))
                .check(matches(withText("Player one is winning!")));
    }

    @Test
    public void playerScoreUpdate() {
        // Perform some clicks to simulate a game and update the player scores
        Espresso.onView(ViewMatchers.withId(R.id.btn_0)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.btn_1)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.btn_3)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.btn_4)).perform(ViewActions.click());

        // Verify that the player scores have been updated correctly
        Espresso.onView(ViewMatchers.withId(R.id.playerOneScore)).check(matches(withText("1")));
        Espresso.onView(ViewMatchers.withId(R.id.playerTwoScore)).check(matches(withText("0")));
    }}