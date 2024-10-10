package com.fasterxml.jackson.core.read;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.util.JsonParserSequence;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("resource")
class ParserSequenceTest
        extends JUnit5TestBase {
    @Test
    void simple() throws Exception {
        JsonParser p1 = JSON_FACTORY.createParser("[ 1 ]");
        JsonParser p2 = JSON_FACTORY.createParser("[ 2 ]");
        JsonParserSequence seq = JsonParserSequence.createFlattened(false, p1, p2);
        assertEquals(2, seq.containedParsersCount());

        assertFalse(p1.isClosed());
        assertFalse(p2.isClosed());
        assertFalse(seq.isClosed());
        assertToken(JsonToken.START_ARRAY, seq.nextToken());
        assertToken(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(1, seq.getIntValue());
        assertToken(JsonToken.END_ARRAY, seq.nextToken());
        assertFalse(p1.isClosed());
        assertFalse(p2.isClosed());
        assertFalse(seq.isClosed());
        assertToken(JsonToken.START_ARRAY, seq.nextToken());

        // first parser ought to be closed now
        assertTrue(p1.isClosed());
        assertFalse(p2.isClosed());
        assertFalse(seq.isClosed());

        assertToken(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(2, seq.getIntValue());
        assertToken(JsonToken.END_ARRAY, seq.nextToken());
        assertTrue(p1.isClosed());
        assertFalse(p2.isClosed());
        assertFalse(seq.isClosed());

        assertNull(seq.nextToken());
        assertTrue(p1.isClosed());
        assertTrue(p2.isClosed());
        assertTrue(seq.isClosed());

        seq.close();
    }

    @Test
    void multiLevel() throws Exception {
        JsonParser p1 = JSON_FACTORY.createParser("[ 1 ] ");
        JsonParser p2 = JSON_FACTORY.createParser(" 5");
        JsonParser p3 = JSON_FACTORY.createParser(" { } ");
        JsonParserSequence seq1 = JsonParserSequence.createFlattened(true, p1, p2);
        JsonParserSequence seq = JsonParserSequence.createFlattened(false, seq1, p3);
        assertEquals(3, seq.containedParsersCount());

        assertToken(JsonToken.START_ARRAY, seq.nextToken());
        assertToken(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertToken(JsonToken.END_ARRAY, seq.nextToken());

        assertToken(JsonToken.VALUE_NUMBER_INT, seq.nextToken());

        assertToken(JsonToken.START_OBJECT, seq.nextToken());
        assertToken(JsonToken.END_OBJECT, seq.nextToken());

        assertNull(seq.nextToken());
        assertTrue(p1.isClosed());
        assertTrue(p2.isClosed());
        assertTrue(p3.isClosed());
        assertTrue(seq.isClosed());
    }

    // for [jackson-core#296]
    @Test
    void initializationDisabled() throws Exception {
        // // First, with old legacy settings

        JsonParser p1 = JSON_FACTORY.createParser("1 2");
        JsonParser p2 = JSON_FACTORY.createParser("3 true");
        assertToken(JsonToken.VALUE_NUMBER_INT, p1.nextToken());
        assertEquals(1, p1.getIntValue());
        assertToken(JsonToken.VALUE_NUMBER_INT, p2.nextToken());
        assertEquals(3, p2.getIntValue());

        // with legacy settings, will see neither '1' nor '3'

        JsonParserSequence seq = JsonParserSequence.createFlattened(false, p1, p2);
        assertToken(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(2, seq.getIntValue());
        assertToken(JsonToken.VALUE_TRUE, seq.nextToken());
        assertNull(seq.nextToken());
        seq.close();
    }

    // for [jackson-core#296]
    @Test
    void initializationEnabled() throws Exception {
        // // and then with new "check for current":
        JsonParser p1 = JSON_FACTORY.createParser("1 2");
        JsonParser p2 = JSON_FACTORY.createParser("3 true");
        assertToken(JsonToken.VALUE_NUMBER_INT, p1.nextToken());
        assertEquals(1, p1.getIntValue());
        assertToken(JsonToken.VALUE_NUMBER_INT, p2.nextToken());
        assertEquals(3, p2.getIntValue());

        // with new settings, both '1' and '3' will be visible

        JsonParserSequence seq = JsonParserSequence.createFlattened(true, p1, p2);
        assertToken(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(1, seq.getIntValue());
        assertToken(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(2, seq.getIntValue());
        assertToken(JsonToken.VALUE_NUMBER_INT, seq.nextToken());
        assertEquals(3, seq.getIntValue());
        assertToken(JsonToken.VALUE_TRUE, seq.nextToken());
        assertNull(seq.nextToken());
        seq.close();
    }

    /**
     * Cette méthode permet de skip les tableaux, objets JSON) et de sauter au
     * prochain élément hors de ces structures.
     */

    @Test
    void testSkipChildren() throws Exception {
        // Arrange: Create two JsonParsers and combine them into a JsonParserSequence
        JsonParser parser1 = JSON_FACTORY.createParser("{ \"cle1\": [1, 2, 3], \"cle2\": true }");
        JsonParser parser2 = JSON_FACTORY
                .createParser("{ \"cle3\": { \"cleImbriquee\": \"valeur\" }, \"cle4\": false }");
        JsonParserSequence sequence = JsonParserSequence.createFlattened(false, parser1, parser2);

        // Act: Read tokens from the JsonParserSequence and skip children as needed
        JsonToken token;

        // Assert
        token = sequence.nextToken();
        assertEquals(JsonToken.START_OBJECT, token); // {

        token = sequence.nextToken();
        assertEquals(JsonToken.FIELD_NAME, token); // "cle1"

        token = sequence.nextToken();
        assertEquals(JsonToken.START_ARRAY, token); // [

        sequence.skipChildren(); // Skip the array elements [1, 2, 3]

        token = sequence.nextToken();
        assertToken(JsonToken.FIELD_NAME, token); // "cle2"

        token = sequence.nextToken();
        assertToken(JsonToken.VALUE_TRUE, token); // true

        token = sequence.nextToken();
        assertToken(JsonToken.END_OBJECT, token); // End of first object }

        // Start of second object
        token = sequence.nextToken();
        assertEquals(JsonToken.START_OBJECT, token); // {

        token = sequence.nextToken();
        assertEquals(JsonToken.FIELD_NAME, token); // "cle3"

        token = sequence.nextToken();
        assertEquals(JsonToken.START_OBJECT, token); // Start nested object {

        sequence.skipChildren(); // Skip nested object {"cleImbriquee": "valeur"}

        token = sequence.nextToken();
        assertToken(JsonToken.FIELD_NAME, token); // "cle4"

        token = sequence.nextToken();
        assertToken(JsonToken.VALUE_FALSE, token); // false

        token = sequence.nextToken();
        assertToken(JsonToken.END_OBJECT, token); // End of second object }

        // Assert: No more tokens should be available
        assertNull(sequence.nextToken()); // End of sequence
    }

}
