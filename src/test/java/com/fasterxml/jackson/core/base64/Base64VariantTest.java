package com.fasterxml.jackson.core.base64;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.Base64Variant.PaddingReadBehaviour;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;

public class Base64VariantTest {
    public static Collection<Object[]> base64Variants() {
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[] {Base64Variants.MIME, "MIME"});
        list.add(new Object[] {Base64Variants.MIME_NO_LINEFEEDS, "MIME-NO-LINEFEEDS"});
        list.add(new Object[] {Base64Variants.MODIFIED_FOR_URL, "MODIFIED-FOR-URL"});
        list.add(new Object[] {Base64Variants.PEM, "PEM"});

        return list;
    }

    // Teste les valeurs correctes pour le comportement de lecture pour les variantes de base64.
    // Utiles pour vérifier les changements apportés aux variants.

    @MethodSource("base64Variants")
    @ParameterizedTest(name = "{0}")
    void paddingReadBehaviourTest(Base64Variant variant, String name) {
        PaddingReadBehaviour paddingReadBehaviour = variant.paddingReadBehaviour();
        PaddingReadBehaviour expectedBehaviour;

        switch (name) {
            case "MIME":
                expectedBehaviour = PaddingReadBehaviour.PADDING_REQUIRED;
                break;
            case "MIME-NO-LINEFEEDS":
                expectedBehaviour = PaddingReadBehaviour.PADDING_REQUIRED;
                break;
            case "PEM":
                expectedBehaviour = PaddingReadBehaviour.PADDING_REQUIRED;
                break;
            case "MODIFIED-FOR-URL":
                expectedBehaviour = PaddingReadBehaviour.PADDING_FORBIDDEN;
                break;
            default:
                expectedBehaviour = null;
                break;
        }

        assert(expectedBehaviour == paddingReadBehaviour);
    }

    // Teste l'encodage avec l'ajout des guillemets anglais.

    @MethodSource("base64Variants")
    @ParameterizedTest(name = "{0}")
    void encodeAndAddQuotes(Base64Variant variant, String _name) {
        String PANGRAM_TEXT = "The quick brown fox jumped over the lazy dog.";
        String PANGRAM_ENCODED = "\"VGhlIHF1aWNrIGJyb3duIGZveCBqdW1wZWQgb3ZlciB0aGUgbGF6eSBkb2cu\"";
        byte[] PANGRAM_AS_BYTES;

        try{
            PANGRAM_AS_BYTES = PANGRAM_TEXT.getBytes("US-ASCII");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String encodedParagramWithQuotes = variant.encode(PANGRAM_AS_BYTES, true, "<%>");

        assert(encodedParagramWithQuotes.equals(PANGRAM_ENCODED));
    }

    // Teste les erreurs attendues pour des "strings" base64 invalides.

    @MethodSource("base64Variants")
    @ParameterizedTest(name = "{0}")
    void decodeInvalidBase64(Base64Variant variant, String name) {
        ByteArrayBuilder BYTE_ARRAY_BUILDER = new ByteArrayBuilder();

        String[] INVALID_B64_STRINGS = {
            ".",
            "===",
            "a  ="
        };

        String[] EXPECTED_ERRORS = getExpectedErrorsForVariant(name);

        for (int i = 0; i < INVALID_B64_STRINGS.length; i++) {
            try{
                variant.decode(INVALID_B64_STRINGS[i], BYTE_ARRAY_BUILDER);
            } catch(Exception e) {
                assert(e.getMessage().equals(EXPECTED_ERRORS[i]));
            }

        }
    }

    // Méthode donnant les erreurs attendues pour decodeInvalidBase64.

    public String[] getExpectedErrorsForVariant(String name) {
        String[] DEFAULT_EXPECTED_ERRORS = {
            "Illegal character \'.\' (code 0x2e) in base64 content",
            "Unexpected padding character (\'=\') as character #1 of 4-char base64 unit: padding only legal as 3rd or 4th character",
            "Illegal white space character (code 0x20) as character #2 of 4-char base64 unit: can only used between units"
        };

        String[] MODIFIED_FOR_URL_EXPECTED_ERRORS = {
            "Illegal character \'.\' (code 0x2e) in base64 content",
            "Illegal character \'=\' (code 0x3d) in base64 content",
            "Illegal white space character (code 0x20) as character #2 of 4-char base64 unit: can only used between units"
        };

        String[] expectedErrors;

        switch (name) {
            case "MIME":
            case "PEM":
            case "MIME-NO-LINEFEEDS":
                expectedErrors = DEFAULT_EXPECTED_ERRORS;
                break;
            case "MODIFIED-FOR-URL":
                expectedErrors = MODIFIED_FOR_URL_EXPECTED_ERRORS;
                break;
            default:
                expectedErrors = null;
                break;
        }

        return expectedErrors;
    }
}