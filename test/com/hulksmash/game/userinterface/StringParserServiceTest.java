package com.hulksmash.game.userinterface;

import org.junit.Assert;
import org.junit.Test;

public class StringParserServiceTest {
    private StringParserService stringParserService = new StringParserService();

    @Test
    public void tryParseToIntCorrectValue(){
        boolean correct = stringParserService.tryParseToInt("12");
        Assert.assertTrue(correct);
        Assert.assertEquals(12, stringParserService.getIntNumber());
    }

    @Test
    public void tryParseToIntNullValue(){
        boolean correct = stringParserService.tryParseToInt(null);
        Assert.assertFalse(correct);
    }

    @Test
    public void tryParseToIntEmptyValue(){
        boolean correct = stringParserService.tryParseToInt("");
        Assert.assertFalse(correct);
    }
    @Test
    public void tryParseToIntNotNumericValue(){
        boolean correct = stringParserService.tryParseToInt("aa");
        Assert.assertFalse(correct);
    }
}
