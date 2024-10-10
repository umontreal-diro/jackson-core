# Nouveaux tests

# 1. Documentation du Test `ByteSourceJsonHandleBOMTest`

Les nouveaux tests unitaires sont créés pour le parsing des fichiers JSON encodés avec différentes Byte Order Marks (BOM), en particulier pour les encodages UTF-32 et UTF-16. Les tests valident la détection correcte de l'encodage par la classe `ByteSourceJsonBootstrapper` en fonction des bytes de début d'un flux.

Fonction visée:

![HandleBom.png](NouvTests_Coverage%2FHandleBom.png)

# 2. Documentation du Test `FilteringGeneratorDelegateTest`

Ce document décrit en détail les tests unitaires créés pour la classe `FilteringGeneratorDelegate`, qui permet de générer des représentations JSON tout en appliquant des filtres sur les données. Les tests valident le comportement de la génération de JSON avec différents types de filtres et scénarios. Plus précisément, le polymorphisme de WriteArray n'avait pas été testé. On utilise donc les filtres les plus simples, soit allow all ou none(null token)

Fonction visée:

![FilteringGen.png](NouvTests_Coverage%2FFilteringGen.png)

# 3. Documentation du Test `JsonWriteFeatureDefaultsTest`

Ces tests unitaire est créé pour la classe `JsonWriteFeature`, qui gère les fonctionnalités d'écriture JSON. Le test valide que les masques de fonctionnalités par défaut sont correctement définis et que chaque fonctionnalité a un masque associé.

Fonction visée:

![DefaultFeat.png](NouvTests_Coverage%2FDefaultFeat.png)


# 4. Documentation du Test `JsonParserReaderBasedTest`

Ce document fournit une description détaillée des tests unitaires créés pour la classe `JsonParserReaderBasedTest`, qui vérifie le comportement d'un `JsonParser` basé sur des lecteurs (readers) lorsqu'il est fermé et utilise les méthodes `nextXxx()`. Il test plusieurs Exeptions qui n'ont jamais été covered

![ParserBaseEOF.png](NouvTests_Coverage%2FParserBaseEOF.png)

# 5. Documentation du Test `DataOutputAsStreamTest`

ces tests unitaires sont créés pour la classe `DataOutputAsStream`, qui permet d'écrire des données à partir d'une instance de `DataOutput`. Les tests vérifient que les valeurs écrites par `DataOutputAsStream` correspondent à celles attendues.

fonctions visées:

![DataOutputStream.png](NouvTests_Coverage%2FDataOutputStream.png)


# 6. Documentation du Test `ParserFeatureTokensTest`

Ces nouveaux tests unitaires sont créés pour le parser JSON Jackson, en particulier les fonctionnalités d'une classe de parser personnalisée `TestParser`, qui étend la classe `ParserMinimalBase` du package `com.fasterxml.jackson.core`. Les tests visent à vérifier le comportement du parsing des tokens et les valeurs par défaut.

Fonctions visées:

![ParserBase.png](NouvTests_Coverage%2FParserBase.png)

# 7. Documentation du Test `ParseNumberInputTest`

Les tests unitaires créés pour la classe `ParseNumberInputTest`, vérifient le comportement de la méthode `parseAsInt` de la classe `NumberInput` pour différents types d'entrées.

Fonctions visées:

![InputValid.png](NouvTests_Coverage%2FInputValid.png)

# 8. Documentation du Test `UTF8JsonGeneratorTest`

Ces tests unitaires sont créés pour la classe `UTF8JsonGeneratorTest`, qui vérifie le comportement du générateur JSON UTF-8 dans le cadre de l'écriture de chaînes contenant des caractères spéciaux et des nombres. On testait ici particulièrement certains constructeurs neuf de la version 2.10, non couvert.

Fonctions visées:

![UTF8.png](NouvTests_Coverage%2FUTF8.png)

# 9. Documentation du Test `AddNameTest`

Ces tests unitaires sont créés pour la classe `AddNameTest`, qui vérifie le comportement de l'ajout de noms dans le `ByteQuadsCanonicalizer`, utilisé pour la gestion des chaînes de caractères au sein de la bibliothèque Jackson.

Fonctions visées:

![AddName.png](NouvTests_Coverage%2FAddName.png)

