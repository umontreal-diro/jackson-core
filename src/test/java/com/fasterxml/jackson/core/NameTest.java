package com.example;

/**
 * Base class for tokenized names (key strings in objects) that have
 * been tokenized from byte-based input sources (like
 * {@link java.io.InputStream}.
 *
 * @author Tatu Saloranta
 */
abstract class Name {
    protected final String _name;
    protected final int _hashCode;

    protected Name(String name, int hashCode) {
        _name = name;
        _hashCode = hashCode;
    }

    public String getName() {
        return _name;
    }

    /*
    /**********************************************************
    /* Methods for package/core parser
    /**********************************************************
     */

    public abstract boolean equals(int q1);

    public abstract boolean equals(int q1, int q2);

    public abstract boolean equals(int q1, int q2, int q3);

    public abstract boolean equals(int[] quads, int qlen);

    /*
    /**********************************************************
    /* Overridden standard methods
    /**********************************************************
     */

    @Override
    public String toString() {
        return _name;
    }

    @Override
    public final int hashCode() {
        return _hashCode;
    }

    @Override
    public boolean equals(Object o) {
        return (o == this);
    }
}

/**
 * Implémentation concrète de la classe abstraite {@link Name}
 * pour les besoins des tests unitaires.
 */
class NameImpl extends Name {

    public NameImpl(String name, int hashCode) {
        super(name, hashCode);
    }

    @Override
    public boolean equals(int q1) {
        return this._hashCode == q1;
    }

    @Override
    public boolean equals(int q1, int q2) {
        return this._hashCode == q1 + q2;
    }

    @Override
    public boolean equals(int q1, int q2, int q3) {
        return this._hashCode == q1 + q2 + q3;
    }

    @Override
    public boolean equals(int[] quads, int qlen) {
        int sum = 0;
        for (int i = 0; i < qlen; i++) {
            sum += quads[i];
        }
        return this._hashCode == sum;
    }
}

/**
 * Classe de test pour la classe {@link Name} et son implémentation {@link NameImpl}.
 */
public class NameTest {

    private Name name;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        name = new NameImpl("TestName", 12345);
    }

    @org.junit.jupiter.api.Test
    public void testGetName() {
        assert name.getName().equals("TestName");
    }

    @org.junit.jupiter.api.Test
    public void testEqualsSingleQuad() {
        assert name.equals(12345);
    }

    @org.junit.jupiter.api.Test
    public void testEqualsTwoQuads() {
        assert name.equals(6000, 6345);
    }

    @org.junit.jupiter.api.Test
    public void testEqualsThreeQuads() {
        assert name.equals(4000, 4000, 4345);
    }

   

    @org.junit.jupiter.api.Test
    public void testHashCode() {
        assert name.hashCode() == 12345;
    }

    @org.junit.jupiter.api.Test
    public void testToString() {
        assert name.toString().equals("TestName");
    }


}
