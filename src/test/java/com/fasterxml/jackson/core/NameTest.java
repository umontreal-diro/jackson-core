package com.fasterxml.jackson.core.sym;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// 主测试类
public class NameTest {

    private Name name;

    @BeforeEach
    public void setUp() {
        name = new NameImpl("TestName", 12345);
    }

    @Test
    public void testGetName() {
        assertEquals("TestName", name.getName(), "Le nom retourné doit être 'TestName'.");
    }

    @Test
    public void testEqualsSingleQuad() {
        assertTrue(name.equals(12345), "L'égalité avec un seul entier doit être vraie.");
    }

    @Test
    public void testEqualsTwoQuads() {
        assertTrue(name.equals(6000, 6345), "La somme des deux entiers doit correspondre au hashCode.");
    }

    @Test
    public void testEqualsThreeQuads() {
        assertTrue(name.equals(4000, 4000, 4345), "La somme des trois entiers doit correspondre au hashCode.");
    }



    @Test
    public void testHashCode() {
        assertEquals(12345, name.hashCode(), "Le hashCode doit être égal à 12345.");
    }

    @Test
    public void testToString() {
        assertEquals("TestName", name.toString(), "La méthode toString doit retourner 'TestName'.");
    }

    @Test
    public void testEqualsObject() {
        Name otherName = new NameImpl("TestName", 12345);
        assertTrue(name.equals(otherName), "Les deux objets doivent être identiques.");
    }

    // 内部类 NameImpl 作为测试类的实现
    private static class NameImpl extends Name {
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
            if (quads == null || quads.length != qlen) {
                return false;
            }
            int sum = 0;
            for (int i = 0; i < qlen; i++) {
                sum += quads[i];
            }
            return this._hashCode == sum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NameImpl nameImpl = (NameImpl) o;
            return _hashCode == nameImpl._hashCode && _name.equals(nameImpl._name);
        }
    }
}
