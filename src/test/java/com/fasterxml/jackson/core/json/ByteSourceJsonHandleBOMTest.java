package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.testsupport.TestSupport;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteSourceJsonHandleBOMTest
{


    // On remaque que little endians et big endians ont les memes bytes mais symmetriquement places
    // On choisit ces bytes particulier car nous simulons chaque type de encoding.
    public static InputStream createUTF32BEInputStream() {
        byte[] byteArray = new byte[] { 0x00, 0x00, (byte) 0xFE, (byte) 0xFF };

        return new ByteArrayInputStream(byteArray);
    }

    public static InputStream createUTF32LEInputStream() {
        byte[] byteArray = new byte[] { (byte) 0xFF, (byte) 0xFE, 0x00, 0x00 };

        return new ByteArrayInputStream(byteArray);
    }

    public static InputStream createUTF16BEInputStream() {
        byte[] byteArray = new byte[] { (byte) 0xFE, (byte) 0xFF, 0x11, 0x11 };

        return new ByteArrayInputStream(byteArray);
    }
    public static InputStream createUTF16LEInputStream() {
        byte[] byteArray = new byte[] { (byte) 0xFF, (byte) 0xFE, 0x11, 0x11 };

        return new ByteArrayInputStream(byteArray);
    }

    @Test
    public void testUTF32() throws IOException {
        IOContext io = TestSupport.testIOContext();

        ByteSourceJsonBootstrapper byteSourceJsonBootstrapperUTF32BE = new ByteSourceJsonBootstrapper(io, createUTF32BEInputStream());
        JsonEncoding expected = byteSourceJsonBootstrapperUTF32BE.detectEncoding();

        // Il assume correctement le encoding que nous utilisons (Big endian)
        assertEquals("UTF-32BE",expected.getJavaName());

        io = TestSupport.testIOContext();
        ByteSourceJsonBootstrapper byteSourceJsonBootstrapper = new ByteSourceJsonBootstrapper(io, createUTF32LEInputStream());
        expected = byteSourceJsonBootstrapper.detectEncoding();
        // Quand on met les bytes attendu au debut, c'est un little endian
        assertEquals("UTF-32LE",expected.getJavaName());
    }

    @Test
    public void testUTF16() throws IOException {
        IOContext io = TestSupport.testIOContext();

        ByteSourceJsonBootstrapper byteSourceJsonBootstrapperUTF16BE = new ByteSourceJsonBootstrapper(io, createUTF16BEInputStream());
        JsonEncoding expected = byteSourceJsonBootstrapperUTF16BE.detectEncoding();

        // Il assume correctement le encoding que nous utilisons (Big endian)
        assertEquals("UTF-16BE",expected.getJavaName());

        io = TestSupport.testIOContext();
        ByteSourceJsonBootstrapper byteSourceJsonBootstrapper = new ByteSourceJsonBootstrapper(io, createUTF16LEInputStream());
        expected = byteSourceJsonBootstrapper.detectEncoding();
        // Quand on met les bytes attendu au debut, c'est un little endian
        assertEquals("UTF-16LE",expected.getJavaName());
    }

}

