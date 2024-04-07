# Projet Simulation

Ce projet est une simulation d'épidémie. Il vise à développer un modèle multi-agent simple de propagation d'une maladie au sein d'une population. Le but est de simuler la manière dont une maladie se propage parmi les individus dans un environnement donné.

## Arborescence des fichiers

Le dossier "projet-simu" contient les éléments suivants :

- [ ] src/ : Ce répertoire contient les codes sources du projet.
- Human.java : La classe représentant une entité humaine. Un humain peut avoir 4 status différents : Susceptible, Exposed, Infected ou Recovered. Son état varie en fonction de son entourage et de le temps passé dans chaque état.
- Monde.java : La classe décrivant le monde de la simulation. Ici, le monde est représenté par une grille 300x300. Cette classe contient aussi les méthodes de changement d'état d'un humain, ainsi que la méthode pour écrire les résultats dans un fichier .csv.
- Twister.java : Une classe pour le générateur de nombre aléatoires de Mersenne Twister. On a implémenté en plus dedans les méthodes genrand_int32, genrand_real1, genrand_real2 et negExp.
- Test.java : La classe permettant de démarrer la simulation. 100 fichiers .csv sont crées, correspondant chacun à une simulation (boucle for). 
- [ ] build/src/ : Ce dossier contient les fichiers .class générés à partir des fichiers sources.
- [ ] resultats/ : Ce répertoire est destiné à enregistrer les 100 fichiers .csv générés lors de la simulation.

## Compilation et exécution

Pour compiler les fichiers sources et générer les fichiers .class, utiliser la commande suivante

```
javac -d build src/*.java
```

Pour lancer la simulation, utiliser la commande suivante : 

```
java -cp build src/Test
```

## Résultats

Une fois la simulation terminée, les résultats sont ensuite affichés à l'aide d'un Jupyter Notebook. Les graphiques des 100 fichiers sont créés, ainsi que le graphique des courbes moyennes.

## Auteurs

Ce projet a été réalisé par Nallé BARTIN et Gabriel BOUDRON.

## Remerciements

Nous tenons à exprimer notre gratitude envers notre chargé de TP pour ses conseils et son aide précieuse lors du développement du projet.