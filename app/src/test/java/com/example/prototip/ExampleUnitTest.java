package com.example.prototip;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;

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
    }}