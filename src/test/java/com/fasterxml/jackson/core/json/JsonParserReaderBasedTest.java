package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.core.json.async.NonBlockingUtf8JsonParserBase;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests asserts that using closed `JsonParser` doesn't cause ArrayIndexOutOfBoundsException
 * with `nextXxx()` methods but returns `null` as expected.
 */
public class JsonParserReaderBasedTest {
    private static final JsonFactory JSON_F = new JsonFactory();

    JsonParser parser;

    /**
     * Creates a parser to test.
     *
     * @return a reader based json parser
     * @throws IOException when closing stream fails.
     */
    public static ReaderBasedJsonParser newParser() throws IOException {
        // Ici on va controller le stream de inputs pour le faire crash
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[] { '-', 'I' });

        return (ReaderBasedJsonParser) JSON_F.createParser(new InputStreamReader(inputStream));

    }


    @Test
    void ReaderBasedJsonParserEOFTest(){
        boolean wentTroughtExpectedError = false;
        try {
            parser = newParser();
            // Nous savons que le prochain token sera -, suivi par I, ce qui doit provoquer une exeption qui na pas
            // Ete testee
            parser.nextToken();
        }
        catch (JsonEOFException expectedError){
            wentTroughtExpectedError = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertTrue(wentTroughtExpectedError);
    }
}
