package com.fasterxml.jackson.core.sym;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de test pour la classe {@link Name} et son implémentation {@link NameImpl}.
 */
public class NameTest {

    private Name name;

    @BeforeEach
    public void setUp() {
        name = new NameImpl("TestName", 12345);
    }

    @Test
    public void testGetName() {
        assertEquals("TestName", name.getName(), "Le nom doit être 'TestName'.");
    }

    @Test
    public void testEqualsSingleQuad() {
        assertTrue(name.equals(12345), "L'unique valeur doit être égale au hashCode.");
    }

    @Test
    public void testEqualsTwoQuads() {
        assertTrue(name.equals(6000, 6345), "La somme des deux valeurs doit être égale au hashCode.");
    }

    @Test
    public void testEqualsThreeQuads() {
        assertTrue(name.equals(4000, 4000, 4345), "La somme des trois valeurs doit être égale au hashCode.");
    }

    @Test
    public void testEqualsQuadArray() {
        
        int[] matchingArray = {4000, 3000, 3000, 2345}; // Somme = 12345
        int sum = 0;
        for (int i : matchingArray) {
            sum += i;
        }
    
        // Debugging output to trace values
        System.out.println("Expected sum: 12345, Calculated sum: " + sum);
    
        
        assertEquals(12345, sum, "La somme calculée du tableau doit être égale à 12345.");
        assertTrue(name.equals(matchingArray, matchingArray.length), "La somme du tableau doit correspondre au hashCode.");
    }
    

    @Test
    public void testHashCode() {
        assertEquals(12345, name.hashCode(), "Le hashCode doit être 12345.");
    }

    @Test
    public void testToString() {
        assertEquals("TestName", name.toString(), "La méthode toString doit retourner 'TestName'.");
    }

    @Test
    public void testEqualsObject() {
        Name otherName = new NameImpl("TestName", 12345);
        assertTrue(name.equals(otherName), "Les deux objets Name identiques doivent être égaux.");
    }
}
