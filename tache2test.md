Mehtab-Ali Rajput (2025138) & Haoran Sun (20260543)

# Test 1 skipChildren():

Cette méthode permet de skip les tableaux, objets JSON et de sauter au prochain élément hors de ces structures.

[Fonction skipChildren()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/main/java/com/fasterxml/jackson/core/util/JsonParserSequence.java) (ligne 163 ) <br>
[Fonction testSkipChilden()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/test/java/com/fasterxml/jackson/core/read/ParserSequenceTest.java) (ligne 130)

### Avant

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/Test1Avant.png"/>

### Apres

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/Test1Apres.png"/>

# Test 2 writeStartObject():

Ce test verifie si la méthode writeStartObject dans UTF8JsonGenerator.java roule sans probleme(ligne 393)

[Fonction writeStartObject()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/main/java/com/fasterxml/jackson/core/json/UTF8JsonGenerator.java) (ligne 393) <br>
[Fonction testWriteStartObjectWithParameters()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/test/java/com/fasterxml/jackson/core/write/UTF8GeneratorTest.java) (ligne 155)

### Avant

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/test2avant.png"/>

### Apres

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/test2apres.png"/>

# Test 3 size():

Ce test s'assure que la méthode dans ByteArrayBuilder size donne le nombre global exact dans le buffer.

[Fonction size()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/main/java/com/fasterxml/jackson/core/util/ByteArrayBuilder.java) (ligne 91) <br>
[Fonction testBufferSize()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/test/java/com/fasterxml/jackson/core/util/ByteArrayBuilderTest.java) (ligne 78)

### Avant

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/test3avant.png"/>

### Apres

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/test3Apres.png"/>

# Test 4 JsonGenrator enable():

This test checks if the functions enable() applies its effect on its generators.

[Fonction enable()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/main/java/com/fasterxml/jackson/core/util/JsonGeneratorDelegate.java) (ligne 112) <br>
[Fonction shouldEnableWriteNumbersAsStringsFeature()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/test/java/com/fasterxml/jackson/core/util/DelegatesTest.java) (ligne 496)

### Avant

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/test4avant.png"/>

### Apres

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/test4apres.png"/>

# Test 5 testResetWithCopy():

This test checks if the functions disable() does not apply its effect on the genrators.

[Fonction resetWithCopy()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/main/java/com/fasterxml/jackson/core/util/JsonGeneratorDelegate.java) (ligne 118) <br>
[Fonction shouldDisableWriteNumbersAsStringsFeature()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/test/java/com/fasterxml/jackson/core/util/DelegatesTest.java) (ligne 517)

### Avant

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/test5avant.png"/>

### Apres

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/test5Apres.png"/>
