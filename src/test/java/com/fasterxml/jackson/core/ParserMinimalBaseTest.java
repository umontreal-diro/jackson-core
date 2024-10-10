package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.testsupport.TestSupport;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;


public class ParserMinimalBaseTest {

    @Test
    public void testMinimalBase() throws IOException {

        ParserMinimalBase p = new ParserMinimalBase() {
            @Override
            public JsonToken nextToken() throws IOException {
                JsonToken tk = JsonToken.VALUE_NUMBER_INT;
                return JsonToken.VALUE_NUMBER_INT;
            }

            @Override
            protected void _handleEOF() throws JsonParseException {

            }

            @Override
            public String getCurrentName() throws IOException {
                return "";
            }

            @Override
            public void close() throws IOException {

            }

            @Override
            public boolean isClosed() {
                return false;
            }

            @Override
            public JsonStreamContext getParsingContext() {
                return null;
            }

            @Override
            public void overrideCurrentName(String name) {

            }

            @Override
            public String getText() throws IOException {
                return "";
            }

            @Override
            public char[] getTextCharacters() throws IOException {
                return new char[0];
            }

            @Override
            public boolean hasTextCharacters() {
                return false;
            }

            @Override
            public int getTextLength() throws IOException {
                return 0;
            }

            @Override
            public int getTextOffset() throws IOException {
                return 0;
            }

            @Override
            public byte[] getBinaryValue(Base64Variant b64variant) throws IOException {
                return new byte[0];
            }

            @Override
            public ObjectCodec getCodec() {
                return null;
            }

            @Override
            public void setCodec(ObjectCodec oc) {

            }

            @Override
            public Version version() {
                return null;
            }

            @Override
            public JsonLocation getCurrentLocation() {
                return null;
            }

            @Override
            public JsonLocation getTokenLocation() {
                return null;
            }

            @Override
            public Number getNumberValue() throws IOException {
                return null;
            }

            @Override
            public NumberType getNumberType() throws IOException {
                return null;
            }

            @Override
            public int getIntValue() throws IOException {
                return 0;
            }

            @Override
            public long getLongValue() throws IOException {
                return 0;
            }

            @Override
            public BigInteger getBigIntegerValue() throws IOException {
                return null;
            }

            @Override
            public float getFloatValue() throws IOException {
                return 0;
            }

            @Override
            public double getDoubleValue() throws IOException {
                return 0;
            }

            @Override
            public BigDecimal getDecimalValue() throws IOException {
                return null;
            }
        };
        p.nextToken();
        System.out.println(p.getNumberValue());
    }

}
