# Nouveaux tests

# Documentation du Test `ByteSourceJsonHandleBOMTest`

Les nouveaux tests unitaires sont créés pour le parsing des fichiers JSON encodés avec différentes Byte Order Marks (BOM), en particulier pour les encodages UTF-32 et UTF-16. Les tests valident la détection correcte de l'encodage par la classe `ByteSourceJsonBootstrapper` en fonction des bytes de début d'un flux.

Fonction visée:

![HandleBom.png](NouvTests_Coverage%2FHandleBom.png)

# Documentation du Test `FilteringGeneratorDelegateTest`

Ce document décrit en détail les tests unitaires créés pour la classe `FilteringGeneratorDelegate`, qui permet de générer des représentations JSON tout en appliquant des filtres sur les données. Les tests valident le comportement de la génération de JSON avec différents types de filtres et scénarios. Plus précisément, le polymorphisme de WriteArray n'avait pas été testé. On utilise donc les filtres les plus simples, soit allow all ou none(null token)

Fonction visée:

![FilteringGen.png](NouvTests_Coverage%2FFilteringGen.png)

# Documentation du Test `ParserFeatureTokensTest`

Ce document décrit en détail les nouveaux tests unitaires créés pour le parser JSON Jackson, en particulier les fonctionnalités d'une classe de parser personnalisée `TestParser`, qui étend la classe `ParserMinimalBase` du package `com.fasterxml.jackson.core`. Les tests visent à vérifier le comportement du parsing des tokens et les valeurs par défaut.


