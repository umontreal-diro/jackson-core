Mehtab-Ali Rajput (2025138) & Haoran Sun (20260543)

# Test 1 skipChildren():

Cette méthode permet de skip les tableaux, objets JSON et de sauter au prochain élément hors de ces structures.

[Fonction skipChildren()](src\main\java\com\fasterxml\jackson\core\util\JsonParserSequence.java) (ligne 163 ) <br>
[Fonction testSkipChilden()](src\test\java\com\fasterxml\jackson\core\read\ParserSequenceTest.java) (ligne 130)

### Avant

<img src="tache2_test\Test1Avant.png"/>

### Apres

<img src="tache2_test\Test1Apres.png"/>

# Test 2 writeStartObject():

Ce test verifie si la méthode writeStartObject dans UTF8JsonGenerator.java roule sans probleme(ligne 393)

[Fonction writeStartObject()](src\main\java\com\fasterxml\jackson\core\json\UTF8JsonGenerator.java) (ligne 393) <br>
[Fonction testWriteStartObjectWithParameters()](src\test\java\com\fasterxml\jackson\core\write\UTF8GeneratorTest.java) (ligne 155)

### Avant

<img src="tache2_test\test2avant.png"/>

### Apres

<img src="tache2_test\test2apres.png"/>

# Test 3 size():

Ce test s'assure que la méthode dans ByteArrayBuilder size donne le nombre global exact dans le buffer.

[Fonction size()](src\main\java\com\fasterxml\jackson\core\util\ByteArrayBuilder.java) (ligne 91) <br>
[Fonction testBufferSize()](src\test\java\com\fasterxml\jackson\core\util\ByteArrayBuilderTest.java) (ligne 78)

### Avant

<img src="tache2_test\test4avant.png"/>

### Apres

<img src="tache2_test\test4Apres.png"/>

# Test 4 JsonGenrator enable():

This test checks if the functions enable() applies its effect on its generators.

[Fonction enable()](src\main\java\com\fasterxml\jackson\core\util\JsonGeneratorDelegate.java) (ligne 112) <br>
[Fonction shouldEnableWriteNumbersAsStringsFeature()](src\test\java\com\fasterxml\jackson\core\util\DelegatesTest.java) (ligne 496)

### Avant

### Apres

# Test 5 testResetWithCopy():

This test checks if the functions disable() does not applies its effect on the genrators.

[Fonction resetWithCopy()](src\main\java\com\fasterxml\jackson\core\util\JsonGeneratorDelegate.java) (ligne 118) <br>
[Fonction shouldDisableWriteNumbersAsStringsFeature()](src\test\java\com\fasterxml\jackson\core\util\DelegatesTest.java) (ligne 517)

### Avant

### Apres
