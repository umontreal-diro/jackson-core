package com.fasterxml.jackson.failing;

import com.fasterxml.jackson.core.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// Plusieurs exceptions dependant de leur exception parent
// On peut observer quand dans l'appel de ces erreurs on call toujours super()
// Ces tests s'assurent donc que ces classes et ceux auquels ils dependent soient toujours
// Coordonnes.
public class JsonGenerationExceptionTest {

    @Test
    public void testDeprecatedConstructorWithRootCause() {
        Throwable cause = new RuntimeException("Root cause");
        JsonGenerationException ex = new JsonGenerationException(cause);

        assertEquals(cause, ex.getCause());
        assertNull(ex.getProcessor());
        assertEquals("N/A",ex.getMessage());
    }

    @Test
    public void testDeprecatedConstructorWithMessage() {
        String message = "Test message";
        JsonGenerationException ex = new JsonGenerationException(message);

        assertEquals(message, ex.getMessage());
        assertNull(ex.getProcessor());
        assertNull(ex.getCause());
    }

    @Test
    public void testDeprecatedConstructorWithMessageAndRootCause() {
        String message = "Test message";
        Throwable cause = new RuntimeException("Root cause");
        JsonGenerationException ex = new JsonGenerationException(message, cause);

        assertEquals(message, ex.getMessage());
        assertEquals(cause, ex.getCause());
        assertNull(ex.getProcessor());
    }

    @Test
    public void testConstructorWithRootCauseAndGenerator() {
        Throwable cause = new RuntimeException("Root cause");

        // Encore une fois on fait un mock generator. Avec plus de temps nous le meteriosn dans un classe de tests
        // Pour generaliser
        // ceci est automatiquement produit par mon IDE donc pas trop de perte de temps.
        JsonGenerator mockGenerator = new JsonGenerator() {
            @Override
            public JsonGenerator setCodec(ObjectCodec oc) {
                return null;
            }

            @Override
            public ObjectCodec getCodec() {
                return null;
            }

            @Override
            public Version version() {
                return null;
            }

            @Override
            public JsonStreamContext getOutputContext() {
                return null;
            }

            @Override
            public JsonGenerator enable(Feature f) {
                return null;
            }

            @Override
            public JsonGenerator disable(Feature f) {
                return null;
            }

            @Override
            public boolean isEnabled(Feature f) {
                return false;
            }

            @Override
            public int getFeatureMask() {
                return 0;
            }

            @Override
            public JsonGenerator setFeatureMask(int values) {
                return null;
            }

            @Override
            public JsonGenerator useDefaultPrettyPrinter() {
                return null;
            }

            @Override
            public void writeStartArray() throws IOException {

            }

            @Override
            public void writeEndArray() throws IOException {

            }

            @Override
            public void writeStartObject() throws IOException {

            }

            @Override
            public void writeEndObject() throws IOException {

            }

            @Override
            public void writeFieldName(String name) throws IOException {

            }

            @Override
            public void writeFieldName(SerializableString name) throws IOException {

            }

            @Override
            public void writeString(String text) throws IOException {

            }

            @Override
            public void writeString(char[] buffer, int offset, int len) throws IOException {

            }

            @Override
            public void writeString(SerializableString text) throws IOException {

            }

            @Override
            public void writeRawUTF8String(byte[] buffer, int offset, int len) throws IOException {

            }

            @Override
            public void writeUTF8String(byte[] buffer, int offset, int len) throws IOException {

            }

            @Override
            public void writeRaw(String text) throws IOException {

            }

            @Override
            public void writeRaw(String text, int offset, int len) throws IOException {

            }

            @Override
            public void writeRaw(char[] text, int offset, int len) throws IOException {

            }

            @Override
            public void writeRaw(char c) throws IOException {

            }

            @Override
            public void writeRawValue(String text) throws IOException {

            }

            @Override
            public void writeRawValue(String text, int offset, int len) throws IOException {

            }

            @Override
            public void writeRawValue(char[] text, int offset, int len) throws IOException {

            }

            @Override
            public void writeBinary(Base64Variant bv, byte[] data, int offset, int len) throws IOException {

            }

            @Override
            public int writeBinary(Base64Variant bv, InputStream data, int dataLength) throws IOException {
                return 0;
            }

            @Override
            public void writeNumber(int v) throws IOException {

            }

            @Override
            public void writeNumber(long v) throws IOException {

            }

            @Override
            public void writeNumber(BigInteger v) throws IOException {

            }

            @Override
            public void writeNumber(double v) throws IOException {

            }

            @Override
            public void writeNumber(float v) throws IOException {

            }

            @Override
            public void writeNumber(BigDecimal v) throws IOException {

            }

            @Override
            public void writeNumber(String encodedValue) throws IOException {

            }

            @Override
            public void writeBoolean(boolean state) throws IOException {

            }

            @Override
            public void writeNull() throws IOException {

            }

            @Override
            public void writeObject(Object pojo) throws IOException {

            }

            @Override
            public void writeTree(TreeNode rootNode) throws IOException {

            }

            @Override
            public void flush() throws IOException {

            }

            @Override
            public boolean isClosed() {
                return false;
            }

            @Override
            public void close() throws IOException {

            }
        };

        JsonGenerationException ex = new JsonGenerationException(cause, mockGenerator);

        assertEquals(cause, ex.getCause());
        assertEquals(mockGenerator, ex.getProcessor());
        assertEquals("N/A",ex.getMessage());
    }

}
