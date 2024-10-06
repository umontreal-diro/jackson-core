package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.fasterxml.jackson.core.io.IOContext;


class UTF8JsonGeneratorTest extends JUnit5TestBase {
    private final JsonFactory JSON_F = new JsonFactory();

    @Test
    void utf8escapedSlashTest() throws Exception
    {
        //ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        IOContext ctxt = testIOContext();
        int feature = 0;
        byte[] outputBuffer = new byte[4096];
        ObjectCodec codec = null;
        OutputStream out = new ByteArrayOutputStream();
        char quoteChar = '"';
        int outputOffset = 0;
        boolean bufferRecyclable = false;

        UTF8JsonGenerator gen = new UTF8JsonGenerator(ctxt, feature, codec, out, quoteChar, outputBuffer,
                outputOffset, bufferRecyclable);

        String str = "Natuurlijk is alles gelukt en weer een tevreden klant\uD83D\uDE04";
        int length = 4000 - 38;

        for (int i = 1; i <= length; ++i) {
            gen.writeNumber(1);
        }
        gen.writeString(str);
        gen.flush();
        gen.close();

        JsonParser p = JSON_F.createParser(outputBuffer);

        assertToken(JsonToken.VALUE_NUMBER_INT, p.nextToken());
        assertEquals("1", p.getText());
        p.close();
    }


}
