package com.fasterxml.jackson.core.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

import com.fasterxml.jackson.core.SerializableString;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.io.SerializedString;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Simple unit tests to try to verify that the default
 * {@link SerializableString} implementation works as expected.
 */
class SerializedStringTest
    extends com.fasterxml.jackson.core.JUnit5TestBase
{
    private static final String QUOTED = "\\\"quo\\\\ted\\\"";

    @Test
    void appending() throws IOException
    {
        final String INPUT = "\"quo\\ted\"";

        SerializableString sstr = new SerializedString(INPUT);
        // sanity checks first:
        assertEquals(sstr.getValue(), INPUT);
        assertEquals(QUOTED, new String(sstr.asQuotedChars()));

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        assertEquals(QUOTED.length(), sstr.writeQuotedUTF8(bytes));
        assertEquals(QUOTED, bytes.toString("UTF-8"));
        bytes.reset();
        assertEquals(INPUT.length(), sstr.writeUnquotedUTF8(bytes));
        assertEquals(INPUT, bytes.toString("UTF-8"));

        byte[] buffer = new byte[100];
        assertEquals(QUOTED.length(), sstr.appendQuotedUTF8(buffer, 3));
        assertEquals(QUOTED, new String(buffer, 3, QUOTED.length()));
        Arrays.fill(buffer, (byte) 0);
        assertEquals(INPUT.length(), sstr.appendUnquotedUTF8(buffer, 5));
        assertEquals(INPUT, new String(buffer, 5, INPUT.length()));
    }

    @Test
    void failedAccess() throws IOException
    {
        final String INPUT = "Bit longer text";
        SerializableString sstr = new SerializedString(INPUT);

        final byte[] buffer = new byte[INPUT.length() - 2];
        final char[] ch = new char[INPUT.length() - 2];
        final ByteBuffer bbuf = ByteBuffer.allocate(INPUT.length() - 2);

        assertEquals(-1, sstr.appendQuotedUTF8(buffer, 0));
        assertEquals(-1, sstr.appendQuoted(ch, 0));
        assertEquals(-1, sstr.putQuotedUTF8(bbuf));

        bbuf.rewind();
        assertEquals(-1, sstr.appendUnquotedUTF8(buffer, 0));
        assertEquals(-1, sstr.appendUnquoted(ch, 0));
        assertEquals(-1, sstr.putUnquotedUTF8(bbuf));
    }

    @Test
    void testAppendQuotedUTF8() throws IOException {
        SerializedString sstr = new SerializedString(QUOTED);
        assertEquals(QUOTED, sstr.getValue());
        final byte[] buffer = new byte[100];
        final int len = sstr.appendQuotedUTF8(buffer, 3);
        assertEquals("\\\\\\\"quo\\\\\\\\ted\\\\\\\"", new String(buffer, 3, len));
    }

    @Test
    void testJdkSerialize() throws IOException {
        final byte[] bytes = jdkSerialize(new SerializedString(QUOTED));
        SerializedString sstr = jdkDeserialize(bytes);
        assertEquals(QUOTED, sstr.getValue());
        final byte[] buffer = new byte[100];
        final int len = sstr.appendQuotedUTF8(buffer, 3);
        assertEquals("\\\\\\\"quo\\\\\\\\ted\\\\\\\"", new String(buffer, 3, len));
    }

    /**
     * Teste la méthode {@link SerializedString#charLength()}
     * avec un la chaîne de caractère vide.
     */
    @Test
    void testEmptyCharLength() {
        SerializedString stringVide = new SerializedString("");
        assertEquals(0, stringVide.charLength());
    }

    /**
     * Teste la méthode {@link SerializedString#charLength()}
     * avec le retour chariot
     */
    @Test
    void testRetourchariotCharLength() {
        SerializedString retourCharriot = new SerializedString("\n");
        assertEquals(1, retourCharriot.charLength());
    }

    /**
     * Teste la méthode {@link SerializedString#charLength()}
     * avec le tabulation
     */
    @Test
    void testTabulationCharLength() {
        SerializedString tabulation = new SerializedString("\t");
        assertEquals(1, tabulation.charLength());
    }

    /**
     * Teste la méthode {@link SerializedString#appendQuoted(char[], int)}
     * avec un buffer de taille suffisante.
     */
    @Test
    void testAppendQuotedWithTooSmallBuffer() {
        SerializedString mike_horn = new SerializedString("Mike Horn");
        char[] buffer = new char[5]; 
        int result = mike_horn.appendQuoted(buffer, 0);
        assertEquals(-1, result);
    }

    /**
     * Teste la méthode {@link SerializedString#appendUnquoted(char[], int)}
     * avec un buffer de taille suffisante.
     */
    @Test
    void testAppendUnquotedWithTooSmallBuffer() {
        SerializedString stromae = new SerializedString("House'llelujah");
        char[] buffer = new char[11];
        int result = stromae.appendUnquoted(buffer, 0);
        assertEquals(-1, result);
    }

    /**
     * Teste la méthode {@link SerializedString#appendQuotedUTF8(byte[], int)}.
     * La méthode appendQuotedUTF8 doit ajouter la chaîne de caractères
     * encodée en UTF-8 dans le buffer passé en paramètre.
     */
    @Test
    void testAsQuotedUTF8() {
        SerializedString batman = new SerializedString("Bruce Wayne");
        byte[] expected = batman.asQuotedUTF8();
        assertNotNull(expected);
        assertTrue(expected.length > 0);
    }
}
