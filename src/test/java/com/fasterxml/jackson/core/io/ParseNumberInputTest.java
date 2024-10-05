package com.fasterxml.jackson.core.io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParseNumberInputTest {

    @Test
    void parseAsInTest()
    {
        final int def = 0;

        final String sNull = null;
        int resultNull = NumberInput.parseAsInt(sNull, def);
        assertEquals(0,resultNull);

        final String sLen0 = "";
        int resultLen0 = NumberInput.parseAsInt(sLen0, def);
        assertEquals(0,resultLen0);

        final String sPlus = "+1234";
        int resultPlus = NumberInput.parseAsInt(sPlus, def);
        assertEquals(1234 ,resultPlus);

        final String sMoins = "-1234";
        int resultMoins = NumberInput.parseAsInt(sMoins, def);
        assertEquals(-1234 , resultMoins);

        final String sAlpha = "hello";
        int resultAlpha = NumberInput.parseAsInt(sAlpha, def);
        assertEquals(0 , resultAlpha);

        final String sNum = "123";
        int resultNum = NumberInput.parseAsInt(sNum, def);
        assertEquals(123 , resultNum);
    }
}
