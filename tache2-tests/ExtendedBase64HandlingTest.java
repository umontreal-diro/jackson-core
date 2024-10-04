package com.fasterxml.jackson.core.base64;

import java.io.*;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedBase64HandlingTest extends JUnit5TestBase {

    private final JsonFactory JSON_F = newStreamFactory();

    /**
     * Test that validates proper Base64 decoding with a valid string input.
     * This ensures the library handles regular Base64 encoding correctly.
     */
    @Test
    void base64ValidDecoding() throws Exception {
        // Arrange
        final String validBase64 = q("SGVsbG8gd29ybGQ=");  // "Hello world" in Base64

        // Act
        JsonParser parser = createParser(JSON_F, MODE_READER, validBase64);
        assertToken(JsonToken.VALUE_STRING, parser.nextToken());
        byte[] decoded = parser.getBinaryValue(Base64Variants.MIME);

        // Assert
        assertArrayEquals("Hello world".getBytes("UTF-8"), decoded);
        parser.close();
    }

    /**
     * Test handling of invalid Base64 input with an illegal character.
     * Expecting that parsing fails and an InvalidFormatException is thrown.
     */
    @Test
    void base64InvalidCharacter() throws IOException {
        // Arrange
        final String invalidBase64 = q("a#b=");

        // Act
        JsonParser parser = createParser(JSON_F, MODE_READER, invalidBase64);
        assertToken(JsonToken.VALUE_STRING, parser.nextToken());

        // Assert
        try {
            parser.getBinaryValue(Base64Variants.MIME);
            fail("Should have thrown JsonParseException for illegal character.");
        } catch (JsonParseException e) {
            verifyException(e, "illegal character");
        } finally {
            parser.close();
        }
    }

    /**
     * Test for Base64 decoding with missing padding. The decoder should handle it gracefully
     * without throwing errors.
     */
    @Test
    void base64MissingPadding() throws IOException {
        // Arrange
        final String missingPadding = q("YWJjZA");  // "abcd" without padding

        // Act
        JsonParser parser = createParser(JSON_F, MODE_READER, missingPadding);
        assertToken(JsonToken.VALUE_STRING, parser.nextToken());
        byte[] decoded = parser.getBinaryValue(Base64Variants.MIME_NO_LINEFEEDS);

        // Assert
        assertArrayEquals("abcd".getBytes("UTF-8"), decoded);
        parser.close();
    }

    /**
     * Test that verifies the behavior when Base64 string is empty.
     * It should return an empty byte array.
     */
    @Test
    void base64EmptyString() throws Exception {
        // Arrange
        final String emptyBase64 = q("");

        // Act
        JsonParser parser = createParser(JSON_F, MODE_READER, emptyBase64);
        assertToken(JsonToken.VALUE_STRING, parser.nextToken());
        byte[] decoded = parser.getBinaryValue(Base64Variants.MIME);

        // Assert
        assertArrayEquals(new byte[0], decoded);
        parser.close();
    }

    /**
     * Test for handling null values in Base64 encoded fields.
     * The resulting binary value should be null, and no exception should be thrown.
     */
    @Test
    void base64NullValue() throws IOException {
        // Arrange
        final String jsonWithNullBase64 = "{ \"imageData\": null }";
        class DataWithBase64Field {
            public byte[] imageData;
        }

        // Act
        ObjectMapper objectMapper = new ObjectMapper();
        DataWithBase64Field result = objectMapper.readValue(jsonWithNullBase64, DataWithBase64Field.class);

        // Assert
        assertNull(result.imageData);
    }

    /**
     * Test for verifying Base64 decoding for very large input strings.
     * This ensures the parser can handle large data inputs without performance issues.
     */
    @Test
    void base64LargeInput() throws IOException {
        // Arrange
        final int SIZE = 1000000;  // 1 MB of data
        String largeBase64 = new String(new char[SIZE]).replace("\0", "a");

        // Act
        JsonParser parser = createParser(JSON_F, MODE_READER, largeBase64);
        assertToken(JsonToken.VALUE_STRING, parser.nextToken());
        byte[] decoded = parser.getBinaryValue(Base64Variants.MODIFIED_FOR_URL);

        // Assert
        assertEquals(SIZE / 4 * 3, decoded.length);
        parser.close();
    }

    /**
     * Test the behavior of Jackson when encountering an unexpected end of Base64 input
     * due to missing padding characters.
     */
    @Test
    void base64UnexpectedEnd() throws IOException {
        // Arrange
        final String incompleteBase64 = q("fQ");  // Incomplete Base64 string

        // Act
        JsonParser parser = createParser(JSON_F, MODE_READER, incompleteBase64);
        assertToken(JsonToken.VALUE_STRING, parser.nextToken());

        // Assert
        try {
            parser.getBinaryValue(Base64Variants.MIME);
            fail("Should have thrown JsonParseException for unexpected end.");
        } catch (JsonParseException e) {
            verifyException(e, "Unexpected end of base64-encoded String");
        } finally {
            parser.close();
        }
    }

    /**
     * Test for Base64 decoding with escaped padding characters.
     * This ensures the decoder can handle scenarios with escaped special characters.
     */
    @Test
    void base64EscapedPadding() throws IOException {
        // Arrange
        final String escapedPaddingBase64 = q("VGVzdCE\\u003d");  // "Test!" with escaped padding '='

        // Act
        JsonParser parser = createParser(JSON_F, MODE_READER, escapedPaddingBase64);
        assertToken(JsonToken.VALUE_STRING, parser.nextToken());
        byte[] decoded = parser.getBinaryValue(Base64Variants.MIME);

        // Assert
        assertEquals("Test!", new String(decoded, "UTF-8"));
        parser.close();
    }

    /**
     * Test for handling Base64 decoding inside a JSON array.
     * This tests the behavior when multiple Base64 strings are present in an array.
     */
    @Test
    void base64InArray() throws IOException {
        // Arrange
        final int NUM_ELEMENTS = 5;
        StringWriter writer = new StringWriter();
        JsonGenerator generator = JSON_F.createGenerator(writer);
        generator.writeStartArray();
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            generator.writeBinary(new byte[]{(byte) i, (byte) (i + 1)});
        }
        generator.writeEndArray();
        generator.close();

        // Act
        JsonParser parser = createParser(JSON_F, MODE_READER, writer.toString());
        assertToken(JsonToken.START_ARRAY, parser.nextToken());

        // Assert
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            assertToken(JsonToken.VALUE_STRING, parser.nextToken());
            byte[] decoded = parser.getBinaryValue();
            assertArrayEquals(new byte[]{(byte) i, (byte) (i + 1)}, decoded);
        }
        assertToken(JsonToken.END_ARRAY, parser.nextToken());
        parser.close();
    }
}
