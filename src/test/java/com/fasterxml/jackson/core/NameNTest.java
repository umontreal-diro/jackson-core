package com.fasterxml.jackson.core.sym;

import static org.junit.jupiter.api.Assertions.assertFalse;  // 
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

/**
 * Classe de test pour la classe {@link NameN}.
 * 
 * Intention:
 * Ce fichier de test a pour but de valider le comportement de la classe `NameN`
 * en utilisant des données de test générées dynamiquement avec la bibliothèque `java-faker`.
 * Cela permet d'améliorer la couverture des tests en générant des données aléatoires
 * tout en garantissant la non-utilisation de données sensibles en QA.
 */
public class NameNTest {

    private static final Faker faker = new Faker();  // Création d'une instance de Faker
    private String randomName;
    private int randomHashCode;
    private int[] quads;

    private NameN nameN;

    /**
     * Initialisation avant chaque test
     * 
     * Arrange: Génère un nom aléatoire et un tableau de quads pour tester les différentes méthodes de NameN
     */
    @BeforeEach
    public void setUp() {
        // Arrange : Génération de données de test aléatoires
        randomName = faker.name().fullName();  // Utilisation de Faker pour générer un nom aléatoire
        randomHashCode = randomName.hashCode();
        quads = new int[]{1, 2, 3, 4, 5, 6, 7, 8};  // Tableau de quads pour le test

        // Act : Construction de l'objet NameN à tester
        nameN = NameN.construct(randomName, randomHashCode, quads, quads.length);
    }

    /**
     * Test: Comparer NameN avec un seul quad
     * 
     * AAA: Test pour garantir qu'un seul quad ne correspond pas à NameN
     */
    @Test
    public void testEqualsSingleQuad() {
        // Act & Assert : Le tableau d'une longueur différente ne devrait pas correspondre
        assertFalse(nameN.equals(1), "Un seul quad ne devrait pas correspondre à NameN.");
    }

    /**
     * Test: Comparer NameN avec deux quads
     * 
     * AAA: Test pour garantir que deux quads ne correspondent pas à NameN
     */
    @Test
    public void testEqualsTwoQuads() {
        // Act & Assert : Deux quads ne devraient pas correspondre
        assertFalse(nameN.equals(1, 2), "Deux quads ne devraient pas correspondre à NameN.");
    }

    /**
     * Test: Comparer NameN avec trois quads
     * 
     * AAA: Test pour garantir que trois quads ne correspondent pas à NameN
     */
    @Test
    public void testEqualsThreeQuads() {
        // Act & Assert : Trois quads ne devraient pas correspondre
        assertFalse(nameN.equals(1, 2, 3), "Trois quads ne devraient pas correspondre à NameN.");
    }

    /**
     * Test: Comparer NameN avec un tableau de quads qui correspond
     * 
     * Oracle: Le tableau de quads généré est celui qui doit être testé. Si le test échoue,
     * cela signifie que l'objet NameN ne correspond pas correctement.
     */
    @Test
    public void testEqualsQuadArrayMatching() {
        // Act & Assert : Un tableau de quads correspondant devrait être égal
        assertTrue(nameN.equals(quads, quads.length), "Le tableau de quads devrait correspondre à NameN.");
    }

    /**
     * Test: Comparer NameN avec un tableau de quads non correspondant
     * 
     * AAA & Oracle: Utilisation de quads non correspondants pour s'assurer que NameN n'y correspond pas.
     */
    @Test
    public void testEqualsQuadArrayNonMatching() {
        // Arrange : Créer un tableau de quads différent
        int[] nonMatchingQuads = {9, 10, 11, 12, 13, 14, 15, 16};

        // Act & Assert : Un tableau de quads différent ne devrait pas correspondre
        assertFalse(nameN.equals(nonMatchingQuads, nonMatchingQuads.length), "Le tableau de quads ne devrait pas correspondre à NameN.");
    }


}
