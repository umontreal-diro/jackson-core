package com.fasterxml.jackson.core.io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;

import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// On a besoin d'une implementation simple de la classe
class TestDataOutput implements DataOutput {

    private final List<Integer> writtenBytes = new ArrayList<>();
    public List<Integer> getWrittenBytes() {
        return writtenBytes;
    }


    @Override
    public void write(int b) throws IOException {
        writtenBytes.add(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        for (byte value : b) {
            write((int) value);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (byte value : b) {
            write((int) value);
        }
    }

    @Override
    public void writeBoolean(boolean v) throws IOException {

    }

    @Override
    public void writeByte(int v) throws IOException {

    }

    @Override
    public void writeShort(int v) throws IOException {

    }

    @Override
    public void writeChar(int v) throws IOException {

    }

    @Override
    public void writeInt(int v) throws IOException {
        writtenBytes.add(v);
    }

    @Override
    public void writeLong(long v) throws IOException {

    }

    @Override
    public void writeFloat(float v) throws IOException {

    }

    @Override
    public void writeDouble(double v) throws IOException {

    }

    @Override
    public void writeBytes(String s) throws IOException {

    }

    @Override
    public void writeChars(String s) throws IOException {
    }

    @Override
    public void writeUTF(String s) throws IOException {

    }
}

public class DataOutputAsStreamTest {

    @Test
    public void TestDataOutputAsStreamWriteInt() throws IOException {
        TestDataOutput test = new TestDataOutput();
        DataOutputAsStream dataOutputAsStream = new DataOutputAsStream(test);

        int byteToWrite = 0x1A;

        dataOutputAsStream.write(byteToWrite);
        List<Integer> writtenBytes = test.getWrittenBytes();

        // On verifie que les bytes match en effet
        assertEquals(1, writtenBytes.size());
        assertEquals(Integer.valueOf(byteToWrite), writtenBytes.get(0));
    }

    @Test
    public void TestDataOutputAsStreamWriteArray() throws IOException {
        TestDataOutput test = new TestDataOutput();
        DataOutputAsStream dataOutputAsStream = new DataOutputAsStream(test);

        byte[] bytesToWrite = {0x1A, 0x1B, 0x1C};

        dataOutputAsStream.write(bytesToWrite);
        List<Integer> writtenBytes = test.getWrittenBytes();

        // On verifie que les bytes match en effet
        assertEquals(Integer.valueOf(bytesToWrite[0]), writtenBytes.get(0));
        assertEquals(Integer.valueOf(bytesToWrite[1]), writtenBytes.get(1));
        assertEquals(Integer.valueOf(bytesToWrite[2]), writtenBytes.get(2));
    }

}
