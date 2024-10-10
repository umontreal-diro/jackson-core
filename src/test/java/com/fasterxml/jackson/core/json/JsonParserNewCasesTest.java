package com.fasterxml.jackson.core.json;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.testsupport.MockDataInput;

public class JsonParserNewCasesTest {
    private static final JsonFactory JSON_F = JsonFactory.builder()
        .enable(JsonReadFeature.ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS)
        .build();

    public static Collection<Object> positiveParsers() throws IOException {
        final String JSON = "{\"a\":+1}";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(JSON.getBytes());
        List<Object> list = new ArrayList<>();

        list.add((ReaderBasedJsonParser) JSON_F.createParser(new InputStreamReader(inputStream)));
        list.add((UTF8StreamJsonParser) JSON_F.createParser(inputStream));
        list.add((UTF8DataInputJsonParser) JSON_F.createParser(new MockDataInput(JSON)));
        
        return list;
    }

    public static Collection<Object> negativeParsers() throws IOException {
        final String JSON = "{\"a\":-1}";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(JSON.getBytes());
        List<Object> list = new ArrayList<>();

        list.add((ReaderBasedJsonParser) JSON_F.createParser(new InputStreamReader(inputStream)));
        list.add((UTF8StreamJsonParser) JSON_F.createParser(inputStream));
        list.add((UTF8DataInputJsonParser) JSON_F.createParser(new MockDataInput(JSON)));
        
        return list;
    }

    // Test de "parsing" de JSON avec un nombre positif, précédé d'un +.

    @MethodSource("positiveParsers")
    @ParameterizedTest(name = "{0}")
    void parserPositiveNumbers(JsonParser parser) {
        try {
            JsonToken firstToken = parser.nextToken();
            if (firstToken != null) {
                assert(firstToken.asString().equals("{"));
                parser.nextToken();
                assert(parser.nextIntValue(0) == 1);
                assert(parser.nextToken().asString().equals("}"));
            }
        } catch (Exception e) {
            assert(false);
        }
    }

    // Test de "parsing" de JSON avec un nombre négatif.

    @MethodSource("negativeParsers")
    @ParameterizedTest(name = "{0}")
    void parserNegativeNumbers(JsonParser parser) {
        try {
            JsonToken firstToken = parser.nextToken();
            if (firstToken != null) {
                assert(firstToken.asString().equals("{"));
                parser.nextToken();
                assert(parser.nextIntValue(0) == -1);
                assert(parser.nextToken().asString().equals("}"));
            }
        } catch (Exception e) {
            assert(false);
        }
    }
}
