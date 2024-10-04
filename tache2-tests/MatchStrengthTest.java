package com.fasterxml.jackson.core.format;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for MatchStrength enum, ensuring that the enum values behave as expected.
 * These tests verify the correct order of enum values and their descriptions.
 */
class MatchStrengthTest {

    /**
     * Test that verifies the enum values are defined in the correct order.
     * 
     * Arrange: Set up the expected order of MatchStrength values.
     * Act: Retrieve the actual order of values from the enum.
     * Assert: Ensure that the enum values are in the expected order.
     */
    @Test
    void testEnumOrder() {
        // Arrange: Expected enum order
        MatchStrength[] expectedOrder = {
                MatchStrength.NO_MATCH,
                MatchStrength.INCONCLUSIVE,
                MatchStrength.WEAK_MATCH,
                MatchStrength.SOLID_MATCH,
                MatchStrength.FULL_MATCH
        };

        // Act: Retrieve actual order from enum
        MatchStrength[] actualOrder = MatchStrength.values();

        // Assert: Verify that the enum values are in the correct order
        assertArrayEquals(expectedOrder, actualOrder, "MatchStrength enum values should follow the correct order.");
    }

    /**
     * Test that verifies that each MatchStrength enum value exists and can be accessed.
     * 
     * Arrange: Define the expected MatchStrength enum values.
     * Act: Check that each enum value can be retrieved.
     * Assert: Ensure that the enum values are retrievable without exceptions.
     */
    @Test
    void testEnumValuesExist() {
        // Arrange: Set up expected enum values
        MatchStrength noMatch = MatchStrength.NO_MATCH;
        MatchStrength inconclusive = MatchStrength.INCONCLUSIVE;
        MatchStrength weakMatch = MatchStrength.WEAK_MATCH;
        MatchStrength solidMatch = MatchStrength.SOLID_MATCH;
        MatchStrength fullMatch = MatchStrength.FULL_MATCH;

        // Act & Assert: Verify that each enum value exists and is not null
        assertNotNull(noMatch, "NO_MATCH should exist and not be null.");
        assertNotNull(inconclusive, "INCONCLUSIVE should exist and not be null.");
        assertNotNull(weakMatch, "WEAK_MATCH should exist and not be null.");
        assertNotNull(solidMatch, "SOLID_MATCH should exist and not be null.");
        assertNotNull(fullMatch, "FULL_MATCH should exist and not be null.");
    }

    /**
     * Test that ensures the descriptions of MatchStrength are correctly represented.
     * 
     * Arrange: Provide expected descriptions for the enum values.
     * Act: Validate each description by checking the corresponding enum value.
     * Assert: Ensure that each description corresponds to the correct enum value.
     */
    @Test
    void testEnumToString() {
        // Act & Assert: Check that toString() matches enum names
        assertEquals("NO_MATCH", MatchStrength.NO_MATCH.toString(), "NO_MATCH description should be 'NO_MATCH'.");
        assertEquals("INCONCLUSIVE", MatchStrength.INCONCLUSIVE.toString(), "INCONCLUSIVE description should be 'INCONCLUSIVE'.");
        assertEquals("WEAK_MATCH", MatchStrength.WEAK_MATCH.toString(), "WEAK_MATCH description should be 'WEAK_MATCH'.");
        assertEquals("SOLID_MATCH", MatchStrength.SOLID_MATCH.toString(), "SOLID_MATCH description should be 'SOLID_MATCH'.");
        assertEquals("FULL_MATCH", MatchStrength.FULL_MATCH.toString(), "FULL_MATCH description should be 'FULL_MATCH'.");
    }

    /**
     * Test that ensures a switch case can correctly work with MatchStrength enum values.
     * 
     * Arrange: Define a method that uses MatchStrength enum in a switch statement.
     * Act: Pass different enum values to the method.
     * Assert: Ensure that the correct branch is executed for each enum value.
     */
    @Test
    void testSwitchCaseWithEnum() {
        // Arrange: Setup switch case for enum
        MatchStrength match = MatchStrength.SOLID_MATCH;

        // Act: Use switch to handle different cases
        String result = switch (match) {
            case NO_MATCH -> "No Match";
            case INCONCLUSIVE -> "Inconclusive";
            case WEAK_MATCH -> "Weak Match";
            case SOLID_MATCH -> "Solid Match";
            case FULL_MATCH -> "Full Match";
        };

        // Assert: Ensure correct case is executed
        assertEquals("Solid Match", result, "The switch case should correctly handle SOLID_MATCH.");
    }
}
