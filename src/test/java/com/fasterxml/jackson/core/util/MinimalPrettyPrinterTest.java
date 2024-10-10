package com.fasterxml.jackson.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

/**
 * Classe de tests unitaires pour {@link MinimalPrettyPrinter}.
 * Les tests ont pour objectifs de vérifier que les méthodes de la classe
 * génèrent la sortie attendues
 */
public class MinimalPrettyPrinterTest {

    private MinimalPrettyPrinter prettyPrinter;
    private StringWriter writer;
    private JsonGenerator jsonGenerator;

    /**
     * Méthode d'initialisation appelée manuellement dans chaque test.
     * (parce que dans le repo original, les annotations tels que @BeforeEach ne sont pas utilisées)
     * et il faut que les Github actions passent.
     * J'aurais bien utiliser mockito pour générer le générateur JSON, mais idem
     * Donc on crée un générateur JSON réel et on lui attache le pretty printer
     * pour tester la sortie.
     * Note : on utilise flush() dans tous les tests pour que le contenu du buffer soit écrit.
     * 
     * @throws IOException
     */
    public void setUp() throws IOException {
        prettyPrinter = new MinimalPrettyPrinter();
        writer = new StringWriter();
        JsonFactory factory = new JsonFactory();
        jsonGenerator = factory.createGenerator(writer);
        jsonGenerator.setPrettyPrinter(prettyPrinter);
    }

    /**
     * Teste la méthode {@link MinimalPrettyPrinter#writeStartObject(JsonGenerator)}.
     * Cette méthode est utilisée pour écrire le caractère de début d'un JSON `{`.
     * <p>
     * On vérifie que la méthode génère correctement le caractère de début '{'
     * avec @AssertEquals.
     *
     * @throws IOException 
     */
    @Test
    public void testWriteStartObject() throws IOException {
        setUp(); // Appel manuel de l'initialisation
        prettyPrinter.writeStartObject(jsonGenerator);
        jsonGenerator.flush();
        assertEquals("{", writer.toString());
    }

    /**
     * Teste la méthode {@link MinimalPrettyPrinter#writeEndObject(JsonGenerator, int)}.
     * Cette méthode est utilisée pour écrire le caractère de fin d'un JSON `}`.
     * <p>
     * On vérifie que la méthode génère correctement le caractère de fin '}'
     *
     * @throws IOException
     */
    @Test
    public void testWriteEndObject() throws IOException {
        setUp(); // Appel manuel de l'initialisation
        // utilité de nrOfEntries ?
        prettyPrinter.writeEndObject(jsonGenerator, 0);
        jsonGenerator.flush();
        assertEquals("}", writer.toString());
    }

    /**
     * Teste la méthode {@link MinimalPrettyPrinter#writeStartArray(JsonGenerator)}.
     * Cette méthode est utilisée pour écrire le caractère de début d'un tableau JSON `[`.
     * <p>
     * On Vérifie que la méthode génère correctement le caractère de début de tableau `[`.
     *
     * @throws IOException 
     */
    @Test
    public void testWriteStartArray() throws IOException {
        setUp(); // Appel manuel de l'initialisation
        prettyPrinter.writeStartArray(jsonGenerator);
        jsonGenerator.flush();
        assertEquals("[", writer.toString());
    }

    /**
     * Teste la méthode {@link MinimalPrettyPrinter#writeEndArray(JsonGenerator, int)}.
     * Cette méthode est utilisée pour écrire le caractère de fin d'un tableau JSON `]`.
     * <p>
     * On vérifie que la méthode génère correctement le caractère de fin de tableau `]`
     *
     * @throws IOException 
     */
    @Test
    public void testWriteEndArray() throws IOException {
        setUp(); // Appel manuel de l'initialisation
        prettyPrinter.writeEndArray(jsonGenerator, 0);
        jsonGenerator.flush();
        assertEquals("]", writer.toString());
    }

    /**
     * Teste la méthode {@link MinimalPrettyPrinter#setRootValueSeparator(String)} 
     * et {@link MinimalPrettyPrinter#writeRootValueSeparator(JsonGenerator)}.
     * <p>
     * On vérifie que le séparateur de valeurs (en général un espace ou un retour chariot) 
     * est correctement défini et écrit dans le JSON.
     * On teste avec " " comme séparateur.
     *
     * @throws IOException
     */
    @Test
    public void testSetAndWriteRootValueSeparator() throws IOException {
        setUp(); // Appel manuel de l'initialisation
        prettyPrinter.setRootValueSeparator(" ");
        prettyPrinter.writeRootValueSeparator(jsonGenerator);
        jsonGenerator.flush();
        assertEquals(" ", writer.toString());
    }
}
