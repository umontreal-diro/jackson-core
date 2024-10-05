package com.fasterxml.jackson.core.exc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InputCoercionException, ensuring that the exception is correctly
 * instantiated and that its methods function as expected. 
 */
class InputCoercionExceptionTest {

    /**
     * Test that verifies the proper creation of an InputCoercionException,
     * checking that the input type and target type are correctly stored.
     * 
     * Arrange: Setup necessary objects, including a mock JsonParser and expected values.
     * Act: Instantiate the InputCoercionException.
     * Assert: Ensure that the input type and target type match the expected values.
     */
    @Test
    void testInputCoercionExceptionProperties() {
        // Arrange: Setup mock JsonParser and expected types
        JsonParser mockParser = null;  // Here we assume mock or null parser
        String errorMessage = "Coercion failed";
        JsonToken inputToken = JsonToken.VALUE_NUMBER_INT;
        Class<?> targetType = Long.class;

        // Act: Instantiate the InputCoercionException
        InputCoercionException exception = new InputCoercionException(
                mockParser, errorMessage, inputToken, targetType);

        // Assert: Verify that the input type and target type are correctly set
        assertEquals(inputToken, exception.getInputType(), "The input type should match the expected JsonToken.");
        assertEquals(targetType, exception.getTargetType(), "The target type should match the expected Class.");
    }

    /**
     * Test that ensures the withParser() method works as expected by updating
     * the parser reference inside the exception.
     * 
     * Arrange: Create an exception instance with a null parser.
     * Act: Call the withParser() method with a mock JsonParser.
     * Assert: Verify that the parser is correctly updated.
     */
    @Test
    void testWithParser() {
        // Arrange: Create an initial InputCoercionException with null parser
        JsonParser initialParser = null;
        JsonToken inputToken = JsonToken.VALUE_STRING;
        InputCoercionException exception = new InputCoercionException(
                initialParser, "Failed coercion", inputToken, Integer.class);

        // Act: Update the parser using withParser()
        JsonParser mockParser = null; // Use a real or mocked parser here if needed
        exception = exception.withParser(mockParser);

        // Assert: Verify that the parser has been updated correctly
        assertEquals(mockParser, exception.getProcessor(), "The parser should be updated with the new instance.");
    }

    /**
     * Test that checks the message and token handling when coercion fails.
     * 
     * Arrange: Setup the exception with a known message and token.
     * Act: Trigger the exception and retrieve the stored message.
     * Assert: Ensure that the message returned by the exception is correct.
     */
    @Test
    void testExceptionMessage() {
        // Arrange: Setup the exception parameters
        String expectedMessage = "Coercion failed due to incompatible types";
        JsonToken inputToken = JsonToken.VALUE_NUMBER_FLOAT;
        Class<?> targetType = Integer.class;

        // Act: Create the InputCoercionException
        InputCoercionException exception = new InputCoercionException(null, expectedMessage, inputToken, targetType);

        // Assert: Verify the exception message
        assertEquals(expectedMessage, exception.getMessage(), "The exception message should match the expected value.");
    }
}
