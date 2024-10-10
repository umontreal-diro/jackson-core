package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilteringGeneratorDelegateTest {

    private static final JsonFactory JSON_F = new JsonFactory();

    private static ByteArrayOutputStream outputStream;
    private static JsonGenerator gen;
    private static FilteringGeneratorDelegate filteringGeneratorDelegate;

    @AfterAll
    public static void tearDown() throws IOException {
        // Clean up and close the output stream after each test
        if (outputStream != null) {
            outputStream.close();
        }
    }

    // Cette fonction est deja couverte, mais nous l'avons mis ici pour donner le contexte de l'utilisation couverte de write array.
    @Test
    public void testFilteringGeneratorDelegateBaseArray() throws IOException {

        outputStream = new ByteArrayOutputStream();
        gen = JSON_F.createGenerator(outputStream);
        // Ici, on veut ecrire cet array en utilisant le filter qui permet tous les elements (token filter include all)
        int[] myArray = {1, 2, 3, 4, 5};
        filteringGeneratorDelegate = new FilteringGeneratorDelegate(gen, TokenFilter.INCLUDE_ALL, false, false);


        filteringGeneratorDelegate.writeStartArray();
        for (int number : myArray) {
            filteringGeneratorDelegate.writeNumber(number);
        }

        filteringGeneratorDelegate.writeEndArray();
        filteringGeneratorDelegate.flush();

        assertEquals("[1,2,3,4,5]",outputStream.toString());
    }

    // Plusieurs de version de polymorphisme de WriteArray ne sont pas covered. Nous allons les tester ici

    // WriteArray ou une taille est specifiee
    @Test
    public void testFilteringGeneratorDelegateWriteArrayAll() throws IOException {

        outputStream = new ByteArrayOutputStream();
        gen = JSON_F.createGenerator(outputStream);
        int[] myArray = {1, 2, 3, 4, 5};
        FilteringGeneratorDelegate filteringGeneratorDelegate = new FilteringGeneratorDelegate(gen, TokenFilter.INCLUDE_ALL, false, false);

        // Cette fois, on specifie une taille maximale
        filteringGeneratorDelegate.writeStartArray(10);

        for (int number : myArray) {
            filteringGeneratorDelegate.writeNumber(number);
        }


        filteringGeneratorDelegate.writeEndArray();
        filteringGeneratorDelegate.flush();

        assertEquals("[1,2,3,4,5]",outputStream.toString());
    }
    // WriteArray avec size et un filtre null (Un null filter est permis)
    @Test
    public void testFilteringGeneratorDelegateWriteArrayNullFilter() throws IOException {

        outputStream = new ByteArrayOutputStream();
        gen = JSON_F.createGenerator(outputStream);
        int[] myArray = {1, 2, 3, 4, 5};
        FilteringGeneratorDelegate filteringGeneratorDelegate = new FilteringGeneratorDelegate(gen, null, false, false);

        // Cette fois, on specifie une taille maximale
        filteringGeneratorDelegate.writeStartArray(10);

        for (int number : myArray) {
            filteringGeneratorDelegate.writeNumber(number);
        }


        filteringGeneratorDelegate.writeEndArray();
        filteringGeneratorDelegate.flush();

        // Un token null signifie que rien ne devrait passer
        assertEquals("",outputStream.toString());
    }
    // WriteArray avec un object comme initiateur
    @Test
    public void testFilteringGeneratorDelegateWriteArrayObj() throws IOException {

        outputStream = new ByteArrayOutputStream();
        gen = JSON_F.createGenerator(outputStream);
        int[] myArray = {1, 2, 3, 4, 5};
        FilteringGeneratorDelegate filteringGeneratorDelegate = new FilteringGeneratorDelegate(gen, TokenFilter.INCLUDE_ALL, false, false);

        // Cette fois, on specifie un object pour copier ses proprietes
        filteringGeneratorDelegate.writeStartArray(myArray);

        for (int number : myArray) {
            filteringGeneratorDelegate.writeNumber(number);
        }


        filteringGeneratorDelegate.writeEndArray();
        filteringGeneratorDelegate.flush();

        assertEquals("[1,2,3,4,5]",outputStream.toString());
    }

    // Write array avec Object et Taille
    @Test
    public void testFilteringGeneratorDelegateWriteArrayObjAndSize() throws IOException {

        outputStream = new ByteArrayOutputStream();
        gen = JSON_F.createGenerator(outputStream);
        int[] myArray = {1, 2, 3, 4, 5};
        FilteringGeneratorDelegate filteringGeneratorDelegate = new FilteringGeneratorDelegate(gen, TokenFilter.INCLUDE_ALL, false, false);

        // Cette fois, on specifie un object pour copier ses proprietes
        filteringGeneratorDelegate.writeStartArray(myArray, 10);

        for (int number : myArray) {
            filteringGeneratorDelegate.writeNumber(number);
        }


        filteringGeneratorDelegate.writeEndArray();
        filteringGeneratorDelegate.flush();

        assertEquals("[1,2,3,4,5]",outputStream.toString());
    }
    // Write array avec Object et Taille et null token
    @Test
    public void testFilteringGeneratorDelegateWriteArrayObjAndSizeNullToken() throws IOException {

        outputStream = new ByteArrayOutputStream();
        gen = JSON_F.createGenerator(outputStream);
        int[] myArray = {1, 2, 3, 4, 5};
        FilteringGeneratorDelegate filteringGeneratorDelegate = new FilteringGeneratorDelegate(gen, null, false, false);

        // Cette fois, on specifie un object pour copier ses proprietes
        filteringGeneratorDelegate.writeStartArray(myArray, 10);

        for (int number : myArray) {
            filteringGeneratorDelegate.writeNumber(number);
        }

        filteringGeneratorDelegate.writeEndArray();
        filteringGeneratorDelegate.flush();

        assertEquals("",outputStream.toString());
    }

}
