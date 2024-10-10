### Mehtab-Ali Rajput (2025138) & Haoran Sun (20260543)

### Coverage Avant

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/coverage-avant.jpg"/>


### Coverage Apres

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/coverage-apres.jpg"/>



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

Ce test vérifie si la fonction enable() applique son effet à ses générateurs.

[Fonction enable()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/main/java/com/fasterxml/jackson/core/util/JsonGeneratorDelegate.java) (ligne 112) <br>
[Fonction shouldEnableWriteNumbersAsStringsFeature()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/test/java/com/fasterxml/jackson/core/util/DelegatesTest.java) (ligne 496)

### Avant

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/test4avant.png"/>

### Apres

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/test4apres.png"/>

# Test 5 testResetWithCopy():

Ce test vérifie si la fonction disable() n'applique pas son effet aux générateurs.

[Fonction resetWithCopy()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/main/java/com/fasterxml/jackson/core/util/JsonGeneratorDelegate.java) (ligne 118) <br>
[Fonction shouldDisableWriteNumbersAsStringsFeature()](https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/test/java/com/fasterxml/jackson/core/util/DelegatesTest.java) (ligne 517)

### Avant

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/test5avant.png"/>

### Apres

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/test5apres.png"/>

## Les 5 tests suivants sont conçus pour tester les fonctionnalités de NameN.java.

src: https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/main/java/com/fasterxml/jackson/core/sym/NameN.java

test: https://github.com/TibbySHR/jackson-core-tests/blob/2.18/src/test/java/com/fasterxml/jackson/core/NameNTest.java

### Avant

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/6-10%20tests-avant.jpg"/>

### Apres

<img src="https://github.com/TibbySHR/jackson-core-tests/blob/2.18/images/6-10%20tests-apres.jpg"/>

# Test 6 testEqualsSingleQuad():

Objectif : S'assurer que NameN ne correspond pas à un tableau contenant un seul quad, garantissant que les longueurs différentes sont bien gérées.

# Test 7 testEqualsTwoQuads():

Objectif : Valider que NameN ne correspond pas à un tableau de deux quads, évitant les correspondances incorrectes pour des entrées plus courtes.

# Test 8 testEqualsThreeQuads():

Objectif : Assurer que NameN ne correspond pas à un tableau de trois quads, testant davantage le traitement des longueurs d'entrée différentes.

# Test 9 testEqualsQuadArrayMatching():

Objectif : S'assurer que NameN reconnaît correctement un tableau de quads complet et correspondant, garantissant la fonctionnalité attendue.

# Test 10 testEqualsQuadArrayNonMatching():

Objectif : Valider que NameN ne correspond pas à un tableau de quads différent, garantissant que le mécanisme de comparaison est fiable.

