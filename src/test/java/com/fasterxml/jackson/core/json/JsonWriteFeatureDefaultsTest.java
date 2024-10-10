package com.fasterxml.jackson.core.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonWriteFeatureDefaultsTest {

    @Test
    public void testWriteFeatureDefaults() {

        // Chaque Feat ici est un flag. Chaque feat a une position associée.
        final int expectedFeatures = 0b10011;
        assertEquals(expectedFeatures, JsonWriteFeature.collectDefaults());

        // Changeons les feature et assurons nous que le mask associé est le bon.
        JsonWriteFeature j = JsonWriteFeature.WRITE_HEX_UPPER_CASE;
        assertEquals(0b10000, j.getMask());

        j = JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS;
        assertEquals(0b100, j.getMask());

        j = JsonWriteFeature.WRITE_NAN_AS_STRINGS;
        assertEquals(0b10, j.getMask());

        j = JsonWriteFeature.QUOTE_FIELD_NAMES;
        assertEquals(0b1, j.getMask());

        j = JsonWriteFeature.ESCAPE_FORWARD_SLASHES;
        assertEquals(0b100000, j.getMask());
        // Chaque enum possible à alors le bon masque selon le feat associé.


    }
}
