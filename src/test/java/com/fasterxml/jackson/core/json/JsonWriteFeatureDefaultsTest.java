package com.fasterxml.jackson.core.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriteFeatureDefaultsTest {

    @Test
    public void testWriteFeatureDefaults() {

        // Chaque Feat ici est un flag. Chaque feat a une position associée.
        final int expectedFeatures = 0b10011;
        assertEquals(expectedFeatures, JsonWriteFeature.collectDefaults());

        // Changeons les feature et assurons nous que le mask associé est le bon.
        // On observe aussi si c'est un default feature
        JsonWriteFeature jsonWriteFeature = JsonWriteFeature.WRITE_HEX_UPPER_CASE;
        int expectedMask = 0b10000;
        assertEquals(expectedMask, jsonWriteFeature .getMask());
        assertTrue(jsonWriteFeature .enabledByDefault());

        jsonWriteFeature  = JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS;
        expectedMask = 0b100;
        assertEquals(expectedMask, jsonWriteFeature .getMask());
        assertFalse(jsonWriteFeature .enabledByDefault());


        jsonWriteFeature  = JsonWriteFeature.WRITE_NAN_AS_STRINGS;
        expectedMask= 0b10;
        assertEquals(expectedMask, jsonWriteFeature .getMask());
        assertTrue(jsonWriteFeature .enabledByDefault());


        jsonWriteFeature  = JsonWriteFeature.QUOTE_FIELD_NAMES;
        expectedMask= 0b1;
        assertEquals(expectedMask, jsonWriteFeature .getMask());
        assertTrue(jsonWriteFeature.enabledByDefault());


        jsonWriteFeature  = JsonWriteFeature.ESCAPE_FORWARD_SLASHES;
        expectedMask= 0b100000;
        assertEquals(expectedMask, jsonWriteFeature .getMask());
        assertFalse(jsonWriteFeature.enabledByDefault());

        // Chaque enum possible à alors le bon masque selon le feat associé, et est retourne bien s'il est activé pas defaut.
    }
}
