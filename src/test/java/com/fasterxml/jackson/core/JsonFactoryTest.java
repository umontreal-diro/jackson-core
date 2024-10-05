package com.example;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class JsonFactoryTest {

  
    @Test
    public void should_CreateJsonParser_FromInputStream() throws IOException {
        String jsonData = "{\"name\":\"test\"}";
        InputStream inputStream = new ByteArrayInputStream(jsonData.getBytes());
        JsonFactory factory = new JsonFactory();

        JsonParser parser = factory.createParser(inputStream);

        assertNotNull(parser);
        assertFalse(parser.isClosed());
    }

    @Test
    public void should_CreateJsonGenerator_FromOutputStream() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonFactory factory = new JsonFactory();

        JsonGenerator generator = factory.createGenerator(outputStream, JsonEncoding.UTF8);

        assertNotNull(generator);
        assertFalse(generator.isClosed());
    }

    @Test
    public void should_CreateJsonGenerator_FromWriter() throws IOException {
        StringWriter stringWriter = new StringWriter();
        JsonFactory factory = new JsonFactory();

        JsonGenerator generator = factory.createGenerator(stringWriter);

        assertNotNull(generator);
        assertFalse(generator.isClosed());
    }

    @Test
    public void should_EnableAndDisableFactoryFeature() {
        JsonFactory factory = new JsonFactory();

        factory.enable(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES);
        factory.disable(JsonFactory.Feature.INTERN_FIELD_NAMES);

        assertTrue(factory.isEnabled(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES));
        assertFalse(factory.isEnabled(JsonFactory.Feature.INTERN_FIELD_NAMES));
    }

    @Test
    public void should_ParseJsonString() throws IOException {
        String jsonString = "{\"key\":\"value\"}";
        JsonFactory factory = new JsonFactory();

        JsonParser parser = factory.createParser(jsonString);

        assertNotNull(parser);
        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
    }

    @Test
    public void should_CreateJsonParser_FromByteArray() throws IOException {
        byte[] jsonData = "{\"key\":\"value\"}".getBytes();
        JsonFactory factory = new JsonFactory();

        JsonParser parser = factory.createParser(jsonData, 0, jsonData.length);

        assertNotNull(parser);
        assertEquals(JsonToken.START_OBJECT, parser.nextToken());
    }

    @Test
    public void should_CreateJsonGenerator_ForFile() throws IOException {
        File tempFile = File.createTempFile("test", ".json");
        JsonFactory factory = new JsonFactory();

        JsonGenerator generator = factory.createGenerator(tempFile, JsonEncoding.UTF8);

        assertNotNull(generator);
        assertFalse(generator.isClosed());
    }

    @Test
    public void should_SetAndGetObjectCodec() {
        JsonFactory factory = new JsonFactory();
        ObjectMapper codec = new ObjectMapper();

        factory.setCodec(codec);
        ObjectMapper retrievedCodec = (ObjectMapper) factory.getCodec();

        assertSame(codec, retrievedCodec);
    }

    @Test
    public void should_SetCharacterEscapes() throws IOException {
        JsonFactory factory = new JsonFactory();
        StringWriter writer = new StringWriter();
        CustomCharacterEscapes customEscapes = new CustomCharacterEscapes();

        JsonGenerator generator = factory.createGenerator(writer);
        generator.setCharacterEscapes(customEscapes);

        assertSame(customEscapes, generator.getCharacterEscapes());
    }

    // 内部类 CustomCharacterEscapes 的实现
    public static class CustomCharacterEscapes extends CharacterEscapes {

        private final int[] asciiEscapes;

        public CustomCharacterEscapes() {
            // 获取默认的ASCII转义规则
            asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
            // 自定义 '<' 和 '>' 的转义
            asciiEscapes['<'] = CharacterEscapes.ESCAPE_STANDARD;
            asciiEscapes['>'] = CharacterEscapes.ESCAPE_STANDARD;
        }

        @Override
        public int[] getEscapeCodesForAscii() {
            return asciiEscapes;
        }

        @Override
        public SerializableString getEscapeSequence(int ch) {
            // 自定义转义序列
            if (ch == '<') {
                return new SerializedString("\\u003C");
            }
            if (ch == '>') {
                return new SerializedString("\\u003E");
            }
            return null;
        }
    }
}
