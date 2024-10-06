package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.sym.Name3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests pour la classe {@link Name3}.
 * Teste toutes les méthodes et suit les principes AAA et Oracle.
 */
public class Name3Test {

    private static final String NAME = "TestName";
    private static final int HASH_CODE = NAME.hashCode();
    private static final int Q1 = 12345;
    private static final int Q2 = 67890;
    private static final int Q3 = 54321;

    private Name3 name3;

    @BeforeEach
    public void setUp() {
        // Arrange: Initialiser un objet Name3 avant chaque test
        name3 = new Name3(NAME, HASH_CODE, Q1, Q2, Q3);
    }

    @Test
    public void testEqualsSingleQuad() {
        // Arrange & Act: Tester si un seul quad ne correspond jamais
        boolean result = name3.equals(Q1);

        // Assert: Vérifier que la méthode renvoie false
        assertFalse(result, "La méthode equals(int) doit toujours retourner false pour Name3.");
    }

    @Test
    public void testEqualsTwoQuads() {
        // Arrange & Act: Tester si deux quads ne correspondent jamais
        boolean result = name3.equals(Q1, Q2);

        // Assert: Vérifier que la méthode renvoie false
        assertFalse(result, "La méthode equals(int, int) doit toujours retourner false pour Name3.");
    }

    @Test
    public void testEqualsThreeQuads() {
        // Act: Tester avec trois quads corrects
        boolean result = name3.equals(Q1, Q2, Q3);

        // Assert: Vérifier que la méthode retourne true pour trois quads égaux
        assertTrue(result, "La méthode equals(int, int, int) doit retourner true pour trois quads égaux.");

        // Act: Tester avec des quads incorrects
        boolean incorrectResult = name3.equals(Q1, Q2, 99999);

        // Assert: Vérifier que la méthode retourne false pour des quads différents
        assertFalse(incorrectResult, "La méthode equals(int, int, int) doit retourner false pour des quads différents.");
    }

    @Test
    public void testEqualsQuadArray() {
        // Arrange: Créer un tableau de quads correspondant à l'objet Name3
        int[] quadsCorrect = {Q1, Q2, Q3};
        int[] quadsIncorrect = {Q1, Q2, 99999};

        // Act & Assert: Vérifier que la méthode equals(int[], int) retourne true pour des quads égaux
        assertTrue(name3.equals(quadsCorrect, 3), "La méthode equals(int[], int) doit retourner true pour un tableau de quads correspondant.");

        // Act & Assert: Vérifier que la méthode retourne false pour des quads différents
        assertFalse(name3.equals(quadsIncorrect, 3), "La méthode equals(int[], int) doit retourner false pour des quads différents.");

        // Act & Assert: Vérifier que la méthode retourne false si la taille du tableau ne correspond pas
        assertFalse(name3.equals(quadsCorrect, 2), "La méthode equals(int[], int) doit retourner false si la taille ne correspond pas.");
    }
}
