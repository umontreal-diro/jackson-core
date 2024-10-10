package com.fasterxml.jackson.core.type;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonToken;

/**
 * Classe de tests unitaires pour {@link WritableTypeId}.
 * La classe WritableTypeId est utilisée pour passer des informations sur les ID de type
 * lors de la sérialisation d'une seule valeur. Cela est utile pour (si j'ai bien compris)
 * commniqué les types entre le steaming (bas niveau) et le databinding (haut niveau) 
 * lors de la sérialisation ou la désérialisation.
 * <p>
 * Le test a pour objectif de vérifier que le constructeurs de la classe
 * initialise correctement les valeurs (ici des strings) des propriétés.
 */
public class WritableTypeIdTest {

    /**
     * Teste le constructeur {@link WritableTypeId#WritableTypeId(Object, JsonToken, Object)}.
     * On teste avec un string + une ID et le token qui correspond à une valeur (string).
     *
     * @throws Exception
     */
    @Test
    public void testConstructorWithValueShapeAndId() throws Exception {
        Object id = "8_cafés_dans_le_sang";
        Object value = "cbDeCafé?";
        JsonToken valueShape = JsonToken.VALUE_STRING;

        WritableTypeId writableTypeId = new WritableTypeId(value, valueShape, id);
        
        assertEquals(value, writableTypeId.forValue);
        assertEquals(valueShape, writableTypeId.valueShape);
        assertEquals(id, writableTypeId.id);
    }
}
