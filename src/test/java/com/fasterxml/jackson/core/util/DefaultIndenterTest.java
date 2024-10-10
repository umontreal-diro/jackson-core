package com.fasterxml.jackson.core.util;

import org.junit.jupiter.api.Test;
import java.io.StringWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for class {@link DefaultIndenter}.
 *
 * @date 2017-07-31
 * @see DefaultIndenter
 **/
class DefaultIndenterTest {

    @Test
    void withLinefeed() {
    DefaultIndenter defaultIndenter = new DefaultIndenter();
    DefaultIndenter defaultIndenterTwo = defaultIndenter.withLinefeed("-XG'#x");
    DefaultIndenter defaultIndenterThree = defaultIndenterTwo.withLinefeed("-XG'#x");

    assertEquals("-XG'#x", defaultIndenterThree.getEol());
    assertNotSame(defaultIndenterThree, defaultIndenter);
    assertSame(defaultIndenterThree, defaultIndenterTwo);
  }

    @Test
    void withIndent() {
    DefaultIndenter defaultIndenter = new DefaultIndenter();
    DefaultIndenter defaultIndenterTwo = defaultIndenter.withIndent("9Qh/6,~n");
    DefaultIndenter defaultIndenterThree = defaultIndenterTwo.withIndent("9Qh/6,~n");

    assertEquals(System.lineSeparator(), defaultIndenterThree.getEol());
    assertNotSame(defaultIndenterThree, defaultIndenter);
    assertSame(defaultIndenterThree, defaultIndenterTwo);
  }
  /**
   * Teste la méthode {@link DefaultIndenter#writeIndentation(JsonGenerator, int)} 
   * pour un niveau d'indentation négatif. Le niveau d'indentation représente le nombre de token d'indentation
   * (généralement un 'doubles espace' en JSON) ne peux être négatif.
   * On teste si la méthode ne génère rien lorsqu'on lui passe un niveau négatif. 
   *
   * @throws IOException si une erreur d'entrée/sortie se produit lors de l'écriture.
   */
  @Test
  void testWriteIndentationNegativeLevel() throws IOException {
    // On crée un StringWriter pour stocker la sortie
    StringWriter writer = new StringWriter();
    JsonGenerator generator = new JsonFactory().createGenerator(writer);
    
    DefaultIndenter indenter = new DefaultIndenter();

    // niveau négatif d'indentation (ne devrait rien écrire) 
    indenter.writeIndentation(generator, -1);
    generator.flush();
    
    // on vérifie que rien n'a été écrit, donc si il y a un "\n"
    assertEquals("\n", writer.toString());
 }
}