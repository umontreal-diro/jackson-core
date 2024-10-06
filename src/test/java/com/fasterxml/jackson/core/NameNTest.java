package com.fasterxml.jackson.core.sym;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de test pour la classe {@link NameN}.
 * Teste tous les scénarios couverts par la classe en respectant les principes AAA, Oracle et Intention.
 */
public class NameNTest {

    private static final String NAME = "TestName";
    private static final int HASH_CODE = NAME.hashCode();
    private static final int[] QUADS = {1, 2, 3, 4, 5, 6, 7, 8};

    private NameN nameN;

    @BeforeEach
    public void setUp() {
        // Arrange : Initialisation de l'objet pour les tests
        nameN = NameN.construct(NAME, HASH_CODE, QUADS, QUADS.length);
    }

    @Test
    public void testEqualsSingleQuad() {
        // Act & Assert : Le tableau d'une longueur différente ne devrait pas correspondre
        assertFalse(nameN.equals(1), "Un seul quad ne devrait pas correspondre à NameN.");
    }

    @Test
    public void testEqualsTwoQuads() {
        // Act & Assert : Deux quads ne devraient pas correspondre
        assertFalse(nameN.equals(1, 2), "Deux quads ne devraient pas correspondre à NameN.");
    }

    @Test
    public void testEqualsThreeQuads() {
        // Act & Assert : Trois quads ne devraient pas correspondre
        assertFalse(nameN.equals(1, 2, 3), "Trois quads ne devraient pas correspondre à NameN.");
    }

    @Test
    public void testEqualsQuadArrayMatching() {
        // Act & Assert : Un tableau de quads correspondant devrait être égal
        assertTrue(nameN.equals(QUADS, QUADS.length), "Le tableau de quads devrait correspondre à NameN.");
    }

    @Test
    public void testEqualsQuadArrayNonMatching() {
        // Arrange : Créer un tableau de quads différent
        int[] nonMatchingQuads = {9, 10, 11, 12, 13, 14, 15, 16};

        // Act & Assert : Un tableau de quads différent ne devrait pas correspondre
        assertFalse(nameN.equals(nonMatchingQuads, nonMatchingQuads.length), "Le tableau de quads ne devrait pas correspondre à NameN.");
    }

    @Test
    public void testEqualsQuadArrayPartialMatching() {
        // Arrange : Un tableau qui ne correspond que partiellement
        int[] partialMatchingQuads = {1, 2, 3, 4, 9, 10, 11, 12};

        // Act & Assert : Un tableau partiellement correspondant ne devrait pas être égal
        assertFalse(nameN.equals(partialMatchingQuads, partialMatchingQuads.length), "Le tableau de quads partiellement correspondant ne devrait pas correspondre à NameN.");
    }


    @Test
    public void testEqualsObjectDifferent() {
        // Arrange : Créer un objet différent
        int[] differentQuads = {9, 10, 11, 12};
        NameN differentNameN = NameN.construct("DifferentName", "DifferentName".hashCode(), differentQuads, differentQuads.length);

        // Act & Assert : Deux objets NameN différents ne devraient pas être égaux
        assertFalse(nameN.equals(differentNameN), "Deux objets différents NameN ne devraient pas être égaux.");
    }

    @Test
    public void testEqualsObjectNull() {
        // Act & Assert : NameN ne devrait pas être égal à null
        assertFalse(nameN.equals(null), "NameN ne devrait pas être égal à null.");
    }

    @Test
    public void testEqualsObjectOtherType() {
        // Act & Assert : NameN ne devrait pas être égal à un objet d'un autre type
        assertFalse(nameN.equals(new Object()), "NameN ne devrait pas être égal à un objet d'un autre type.");
    }
}
