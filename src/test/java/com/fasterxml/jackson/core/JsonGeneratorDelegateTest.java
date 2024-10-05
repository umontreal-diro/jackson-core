package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

/**
 * Unit tests for the JsonGeneratorDelegate class, ensuring correct behavior
 * for delegating calls to the underlying JsonGenerator.
 */
class JsonGeneratorDelegateTest {

    /**
     * Test that verifies the delegation of writeString method.
     * 
     * Arrange: Mock JsonGenerator instance.
     * Act: Call writeString method on delegate.
     * Assert: Check if the underlying generator's writeString was called.
     */
    @Test
    void testWriteStringDelegation() throws IOException {
        JsonGenerator mockDelegate = new MockJsonGenerator();
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);
        delegate.writeString("test");
        assertTrue(((MockJsonGenerator) mockDelegate).wasWriteStringCalled());
    }

    /**
     * Test that checks the delegation of the flush method.
     * 
     * Arrange: Create mock delegate.
     * Act: Call flush on delegate.
     * Assert: Ensure the flush is called on underlying generator.
     */
    @Test
    void testFlushDelegation() throws IOException {
        JsonGenerator mockDelegate = new MockJsonGenerator();
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);
        delegate.flush();
        assertTrue(((MockJsonGenerator) mockDelegate).wasFlushCalled());
    }

    /**
     * Test for the getCodec method delegation.
     * 
     * Arrange: Set up mock codec in delegate.
     * Act: Call getCodec on the delegate.
     * Assert: Verify if the correct codec is returned.
     */
    @Test
    void testGetCodecDelegation() {
        JsonGenerator mockDelegate = new MockJsonGenerator();
        ObjectCodec mockCodec = new MockObjectCodec();
        mockDelegate.setCodec(mockCodec);
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);
        assertEquals(mockCodec, delegate.getCodec());
    }

    /**
     * Test that checks the override behavior of writeObject.
     * 
     * Arrange: Mock delegate with custom behavior.
     * Act: Call writeObject on delegate with an object.
     * Assert: Ensure object was passed to the underlying generator.
     */
    @Test
    void testWriteObjectDelegation() throws IOException {
        JsonGenerator mockDelegate = new MockJsonGenerator();
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);
        delegate.writeObject("sampleObject");
        assertTrue(((MockJsonGenerator) mockDelegate).wasWriteObjectCalled());
    }

    /**
     * Test that verifies copyCurrentEvent method delegation.
     * 
     * Arrange: Mock JsonGenerator and JsonParser.
     * Act: Call copyCurrentEvent on delegate.
     * Assert: Verify that the method is delegated correctly.
     */
    @Test
    void testCopyCurrentEventDelegation() throws IOException {
        JsonGenerator mockDelegate = new MockJsonGenerator();
        JsonParser mockParser = new MockJsonParser();
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);
        delegate.copyCurrentEvent(mockParser);
        assertTrue(((MockJsonGenerator) mockDelegate).wasCopyCurrentEventCalled());
    }

    /**
     * Test that verifies the close method delegation.
     * 
     * Arrange: Create a mock delegate.
     * Act: Call close on the delegate.
     * Assert: Ensure close was called on the underlying generator.
     */
    @Test
    void testCloseDelegation() throws IOException {
        JsonGenerator mockDelegate = new MockJsonGenerator();
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);
        delegate.close();
        assertTrue(((MockJsonGenerator) mockDelegate).wasCloseCalled());
    }

    /**
     * Test for delegateCopyMethods behavior.
     * 
     * Arrange: Mock JsonGenerator with delegateCopyMethods set to false.
     * Act: Call writeObject.
     * Assert: Ensure delegateCopyMethods prevents delegation.
     */
    @Test
    void testDelegateCopyMethodsFalse() throws IOException {
        JsonGenerator mockDelegate = new MockJsonGenerator();
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate, false);
        delegate.writeObject("sampleObject");
        assertFalse(((MockJsonGenerator) mockDelegate).wasWriteObjectCalled());
    }

    /**
     * Test that checks delegation for writeBoolean method.
     * 
     * Arrange: Mock delegate.
     * Act: Call writeBoolean on delegate.
     * Assert: Ensure the method is delegated.
     */
    @Test
    void testWriteBooleanDelegation() throws IOException {
        JsonGenerator mockDelegate = new MockJsonGenerator();
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);
        delegate.writeBoolean(true);
        assertTrue(((MockJsonGenerator) mockDelegate).wasWriteBooleanCalled());
    }

    /**
     * Test that checks if the getOutputContext method is delegated.
     * 
     * Arrange: Mock JsonGenerator.
     * Act: Call getOutputContext.
     * Assert: Verify delegation of method call.
     */
    @Test
    void testGetOutputContextDelegation() {
        JsonGenerator mockDelegate = new MockJsonGenerator();
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);
        delegate.getOutputContext();
        assertTrue(((MockJsonGenerator) mockDelegate).wasGetOutputContextCalled());
    }

    /**
     * Test that checks writeBinary method delegation.
     * 
     * Arrange: Mock JsonGenerator.
     * Act: Call writeBinary on the delegate.
     * Assert: Verify correct delegation.
     */
    @Test
    void testWriteBinaryDelegation() throws IOException {
        JsonGenerator mockDelegate = new MockJsonGenerator();
        JsonGeneratorDelegate delegate = new JsonGeneratorDelegate(mockDelegate);
        byte[] binaryData = {1, 2, 3};
        delegate.writeBinary(Base64Variants.MIME, binaryData, 0, binaryData.length);
        assertTrue(((MockJsonGenerator) mockDelegate).wasWriteBinaryCalled());
    }
}
