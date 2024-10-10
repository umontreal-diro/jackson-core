package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.JUnit5TestBase;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.util.InternCache;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class AddNameTest extends JUnit5TestBase {

    @Test
    void addNameOneRepresentation() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(137);
        ByteQuadsCanonicalizer placeholder = root.makeChild(JsonFactory.Feature.collectDefaults());

        // Réflexion de méthode pour accéder à  _findOffsetForAdd qui est privée
        Method method = ByteQuadsCanonicalizer.class.getDeclaredMethod("_findOffsetForAdd", int.class);
        method.setAccessible(true);  // Permet l'accès à la méthode privée

        // Calcul du hash
        int q1 = 10;
        int hash = placeholder.calcHash(q1);
        int offset = (int) method.invoke(placeholder, hash);;

        Faker fake = new Faker();

        String name = fake.name().firstName();


        String result = placeholder.addName(name, q1);

        assertEquals(name, result);
        /*
         [com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer: size=1, hashSize=64, 1/0/0/0 pri/sec/ter/spill (=1), total:1]
                        placeholder                             longueur    taille      elem  1-> result
         */

        assertEquals(q1, placeholder._hashArea[offset]);

        assertEquals(1, placeholder._hashArea[offset + 3]);


        // Vérifier que le hash (q1) est bien stocké dans la zone de hachage
        if (offset >= placeholder._names.length) {
            System.out.println("Offset trop grand pour _names: " + offset);
        } else {
            assertEquals(name, placeholder._names[offset >> 2]);
        }

        // Vérifier l'état des compteurs internes
        assertEquals(1, placeholder._count);

    }

    @Test
    void addNameTwoRepresentation() throws Exception {
        ByteQuadsCanonicalizer root = ByteQuadsCanonicalizer.createRoot(137);
        ByteQuadsCanonicalizer placeholder = root.makeChild(JsonFactory.Feature.collectDefaults());

        // Réflexion de méthode pour accéder à  _findOffsetForAdd qui est privée
        Method method = ByteQuadsCanonicalizer.class.getDeclaredMethod("_findOffsetForAdd", int.class);
        method.setAccessible(true);  // Permet l'accès à la méthode privée

        // Calcul du hash
        int q1 = 10;
        int q2 = 5;
        int hash = placeholder.calcHash(q1,q2);
        int offset = (int) method.invoke(placeholder, hash);;

        Faker fake = new Faker();

        String name = fake.name().firstName();

        String result = placeholder.addName(name, q1, q2);

        assertEquals(name, result);


        assertEquals(q1, placeholder._hashArea[offset]);

        assertEquals(q2, placeholder._hashArea[offset + 1]);

        assertEquals(2, placeholder._hashArea[offset + 3]);


        // Vérifier que le hash (q1) est bien stocké dans la zone de hachage
        if (offset >= placeholder._names.length) {
            System.out.println("Offset trop grand pour _names: " + offset);
        } else {
            assertEquals(name, placeholder._names[offset >> 2]);
        }

        // Vérifier l'état des compteurs internes
        assertEquals(1, placeholder._count);
    }
}