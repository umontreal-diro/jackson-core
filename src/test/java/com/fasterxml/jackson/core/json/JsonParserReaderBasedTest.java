package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.testsupport.MockDataInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


    public void initJsonParserClosedCaseTest(String parserName, JsonParser parser) {
        this.parser = parser;
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



    private static Collection<Object[]> closeParsers(JsonParser... parsersToClose) throws IOException {
        List<Object[]> list = new ArrayList<>();
        for (JsonParser p : parsersToClose) {
            p.close();
            list.add(new Object[] { p.getClass().getSimpleName(), p });
        }
        return list;
    }
}
