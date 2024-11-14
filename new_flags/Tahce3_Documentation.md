# Tâche 3 - Documentation

## Flags
1. ```-Xms512m -Xmx1024m```
    Ce flag permet à la JVM d’utiliser un minimum de 512 Mo et un maximum de 1024 Mo de mémoire. Ça aide à s’assurer que le programme dispose de suffisamment de mémoire au démarrage sans utiliser trop de ressources, ce qui peut éviter que d’autres applications soient affectées.
2. ```-XX:+UseG1GC```
   Ce flag active le Garbage Collector G1, qui est fait pour réduire les pauses causées par le nettoyage de la mémoire. Ça permet au programme de tourner plus fluidement, surtout si l’application a besoin de réagir rapidement sans interruptions. Cependant, pour les builds très intensifs, ce GC n’a pas toujours donné les meilleurs résultats en termes de débit.
3. ```-XX:+UseParallelGC```
   Ce flag active un mode où la "garbage collection" se fait en parallèle, c’est-à-dire avec plusieurs threads en même temps. Cela nous aide à améliorer le débit global du build pour les tâches intensives en ressources.
4. ```-XX:+UseZGC```
   Ce flag active le Z Garbage Collector, qui minimise les pauses même quand il y a beaucoup de données à gérer. Cette option s’est révélée particulièrement bénéfique pour les builds qui demandent des ressources importantes.
5. ```-Xdebug -Xrunjdwp=dt_socket,server=y,suspend=n,address=*:5005```
   Ce flag lance la JVM en mode débogage, ce qui permet aux développeurs de se connecter au programme en cours d’exécution sur le port 5005. Ça aide beaucoup pour repérer et corriger les problèmes en temps réel.```
