# InputCoercionException

Class Location: jackson-core/src/main/java/com/fasterxml/jackson/core/exc/InputCoercionException.java

## Justification for Tested Methods:
1. **Constructor and Property Accessors**: It is essential to verify that the input and target types provided during instantiation are correctly stored and can be accessed using `getInputType()` and `getTargetType()`.
2. **withParser Method**: This method updates the internal parser reference in the exception, and it is important to ensure that it functions as expected.
3. **Exception Message**: Verifying the exception message ensures that the error information provided during instantiation is correctly preserved and returned.

These tests ensure that exception handling works correctly and covers key functionalities of the `InputCoercionException` class.


