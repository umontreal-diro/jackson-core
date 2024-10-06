package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.sym.Name1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Name1}. This class tests all methods of the Name1 class using the
 * Arrange-Act-Assert (AAA) pattern, ensuring that each method is logically covered and thoroughly tested.
 */
public class Name1Test {

    private Name1 name1;
    private static final int QUAD = 12345;
    private static final String NAME = "TestName";
    private static final int HASH_CODE = NAME.hashCode();

    @BeforeEach
    public void setUp() throws Exception {
        // Use reflection to access the package-private constructor
        Constructor<Name1> constructor = Name1.class.getDeclaredConstructor(String.class, int.class, int.class);
        constructor.setAccessible(true); // Make the constructor accessible
        name1 = constructor.newInstance(NAME, HASH_CODE, QUAD);
    }

    @Test
    public void testEqualsSingleQuad() throws Exception {
        // Arrange
        int singleQuad = QUAD;
        
        // Act & Assert
        assertTrue(name1.equals(singleQuad), "The single quad should match the stored quad.");
    }

    @Test
    public void testEqualsTwoQuads() throws Exception {
        // Arrange
        int quad1 = QUAD;
        int quad2 = 0; // Name1 only considers the first quad

        // Act & Assert
        assertTrue(name1.equals(quad1, quad2), "Two quads should match if the first is equal and the second is zero.");
    }

    @Test
    public void testEqualsThreeQuads() {
        // Arrange
        int quad1 = QUAD;
        int quad2 = 0;
        int quad3 = 0;

        // Act & Assert
        assertFalse(name1.equals(quad1, quad2, quad3), "Name1 does not support three-quads equality.");
    }

    @Test
    public void testEqualsQuadArray() throws Exception {
        // Arrange
        int[] matchingArray = {QUAD};
        int[] nonMatchingArray = {9999};

        // Act & Assert
        assertTrue(name1.equals(matchingArray, matchingArray.length), "The sum of the array should match the quad value.");
        assertFalse(name1.equals(nonMatchingArray, nonMatchingArray.length), "Non-matching array should not be equal to the quad.");
    }

    @Test
    public void testEqualsObject() throws Exception {
        // Arrange
        Constructor<Name1> constructor = Name1.class.getDeclaredConstructor(String.class, int.class, int.class);
        constructor.setAccessible(true); // Make the constructor accessible
        Name1 identicalName1 = name1; // Same reference
        Name1 differentName1 = constructor.newInstance("DifferentName", "DifferentName".hashCode(), 9999);
        Name1 newIdenticalInstance = constructor.newInstance(NAME, HASH_CODE, QUAD); // Different instance but same data

        // Act & Assert
        assertTrue(name1.equals(identicalName1), "Two identical Name1 objects (same reference) should be equal.");
        assertFalse(name1.equals(newIdenticalInstance), "Two Name1 objects with the same data but different instances should not be equal due to reference equality.");
        assertFalse(name1.equals(differentName1), "Two different Name1 objects should not be equal.");
        assertFalse(name1.equals(null), "Name1 instance should not be equal to null.");
        assertFalse(name1.equals(new Object()), "Name1 instance should not be equal to an unrelated object.");
    }
}
