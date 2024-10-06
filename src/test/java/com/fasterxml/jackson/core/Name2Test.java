package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.sym.Name2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests pour la classe {@link Name2}.
 * Teste toutes les méthodes et suit les principes AAA et Oracle.
 */
public class Name2Test {

    private static final String NAME = "TestName";
    private static final int HASH_CODE = NAME.hashCode();
    private static final int Q1 = 12345;
    private static final int Q2 = 67890;

    private Name2 name2;

    @BeforeEach
    public void setUp() {
        // Arrange: Initialiser un objet Name2 avant chaque test
        name2 = new Name2(NAME, HASH_CODE, Q1, Q2);
    }

    @Test
    public void testEqualsSingleQuad() {
        // Arrange & Act: Tester si un seul quad ne correspond jamais
        boolean result = name2.equals(Q1);

        // Assert: Vérifier que la méthode renvoie false
        assertFalse(result, "La méthode equals(int) doit toujours retourner false pour Name2.");
    }

    @Test
    public void testEqualsTwoQuads() {
        // Act: Tester avec deux quads corrects
        boolean result = name2.equals(Q1, Q2);

        // Assert: Vérifier que la méthode retourne true pour deux quads égaux
        assertTrue(result, "La méthode equals(int, int) doit retourner true pour deux quads égaux.");

        // Act: Tester avec des quads incorrects
        boolean incorrectResult = name2.equals(Q1, 99999);

        // Assert: Vérifier que la méthode retourne false pour des quads différents
        assertFalse(incorrectResult, "La méthode equals(int, int) doit retourner false pour des quads différents.");
    }

    @Test
    public void testEqualsThreeQuads() {
        // Act: Tester avec trois quads (doit toujours retourner false)
        boolean result = name2.equals(Q1, Q2, 54321);

        // Assert: Vérifier que la méthode renvoie false
        assertFalse(result, "La méthode equals(int, int, int) doit toujours retourner false pour Name2.");
    }

    @Test
    public void testEqualsQuadArray() {
        // Arrange: Créer un tableau de quads correspondant à l'objet Name2
        int[] quadsCorrect = {Q1, Q2};
        int[] quadsIncorrect = {Q1, 99999};

        // Act & Assert: Vérifier que la méthode equals(int[], int) retourne true pour des quads égaux
        assertTrue(name2.equals(quadsCorrect, 2), "La méthode equals(int[], int) doit retourner true pour un tableau de quads correspondant.");

        // Act & Assert: Vérifier que la méthode retourne false pour des quads différents
        assertFalse(name2.equals(quadsIncorrect, 2), "La méthode equals(int[], int) doit retourner false pour des quads différents.");

        // Act & Assert: Vérifier que la méthode retourne false si la taille du tableau ne correspond pas
        assertFalse(name2.equals(quadsCorrect, 1), "La méthode equals(int[], int) doit retourner false si la taille ne correspond pas.");
    }
}
