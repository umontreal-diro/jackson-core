package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.JUnit5TestBase;
import com.fasterxml.jackson.core.util.InternCache;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddNameTest extends JUnit5TestBase {
    protected int[] _hashArea;
    protected InternCache _interner;

    @Test
    void addNameOneRepresentation() throws Exception {
        final ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(137);
        ByteQuadsCanonicalizer placeholder = root.makeChildOrPlaceholder(2);

        String name = "Oceane";
        int q1 = 2;

        String result = placeholder.addName(name,q1);
        assertEquals("Oceane",result);
    }

    @Test
    void addNameTwoRepresentation() throws Exception {
        final ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(137);
        ByteQuadsCanonicalizer placeholder = root.makeChildOrPlaceholder(2);

        String name = "Oceane";
        int q1 = 2;
        int q2 = 5;

        String result = placeholder.addName(name,q1,q2);
        assertEquals("Oceane",result);
    }
}
