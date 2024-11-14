# Tâche 3 - Documentation

Dans cette tâche, nous avons amélioré la GitHub Action pour exécuter les tests Java avec plusieurs configurations de flags JVM intégrés dans le fichier ```test.yml```, en utilisant la commande ```mvn test```. Cela permet d’optimiser la gestion de la mémoire, les performances, la visibilité et le traitement des erreurs.

### Commade de Maven Test
```Run ./mvnw -B -q -ff -ntp verify```

## Flags
1. ```-Xms512m -Xmx1024m``` :<br />
    Ce flag permet à la JVM d’utiliser un minimum de 512 Mo et un maximum de 1024 Mo de mémoire. Ça aide à s’assurer que le programme dispose de suffisamment de mémoire au démarrage sans utiliser trop de ressources, ce qui peut éviter que d’autres applications soient affectées.<br />
2. ```-XX:+UseG1GC``` :<br />
   Ce flag active le Garbage Collector G1, qui est fait pour réduire les pauses causées par le nettoyage de la mémoire. Ça permet au programme de tourner plus fluidement, surtout si l’application a besoin de réagir rapidement sans interruptions. Cependant, pour les builds très intensifs, ce GC n’a pas toujours donné les meilleurs résultats en termes de débit.<br />
3. ```-XX:+UseParallelGC``` :<br />
   Ce flag active un mode où la "garbage collection" se fait en parallèle, c’est-à-dire avec plusieurs threads en même temps. Cela nous aide à améliorer le débit global du build pour les tâches intensives en ressources.<br />
4. ```-XX:+UseZGC``` :<br />
   Ce flag active le Z Garbage Collector, qui minimise les pauses même quand il y a beaucoup de données à gérer. Cette option s’est révélée particulièrement bénéfique pour les builds qui demandent des ressources importantes.<br />
5. ```-Xdebug -Xrunjdwp=dt_socket,server=y,suspend=n,address=*:5005``` :<br />
   Cette option lance la JVM en mode débogage, ce qui permet aux développeurs de se connecter au programme en cours d’exécution sur le port 5005. Ça aide beaucoup pour repérer et corriger les problèmes en temps réel.```

### Références : 
https://maven.apache.org/run.html
https://wiki.openjdk.org/display/zgc/Main
