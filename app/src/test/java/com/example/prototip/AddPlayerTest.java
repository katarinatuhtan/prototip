package com.example.prototip;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AddPlayerTest extends TestCase {
    @Test
    public void validatePlayerNames_OneIsEmpty_ShouldBeFalse() {
        // mock activity
        AddPlayer activity = new AddPlayer();

        // prepare mock data
        String playerOneName = "Test";
        String playerTwoName = "";

        // make assert
        Assert.assertFalse(activity.validatePlayerNames(playerOneName, playerTwoName));
    }

    @Test
    public void validatePlayerNames_BothEntered_ShouldBeTrue() {
        // mock activity
        AddPlayer activity = new AddPlayer();

        // prepare mock data
        String playerOneName = "Test1";
        String playerTwoName = "Test2";

        // make assert
        Assert.assertTrue(activity.validatePlayerNames(playerOneName, playerTwoName));
    }

}