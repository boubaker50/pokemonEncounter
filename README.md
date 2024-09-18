# Application Pokemon Encounter

Ce projet a été réalisé dans le cadre d'une préparation technique pour un entretien d'embauche.
L'application Pokemon Encounter permet aux utilisateurs de visualiser une liste de Pokémon et d'afficher des informations détaillées sur un Pokémon spécifique lorsqu'il est sélectionné.

## Fonctionnalités

Liste des Pokémons : L'application récupère les données d'une API externe et affiche une liste de rencontres de Pokémon.
Détails du Pokémon : En cliquant sur un Pokémon dans la liste, l'utilisateur peut voir des informations détaillées sur ce Pokémon, notamment son espèce, son ordre, sa taille, son poids et ses images.
Pagination des API : Les réponses de l'API sont paginées pour améliorer les performances et l'expérience utilisateur.

## Architecture

Architecture MVVM : Le projet suit le modèle MVVM (Modèle-Vue-VueModèle) pour séparer les préoccupations et assurer une modularité et une évolutivité du code.
Injection de dépendances : Hilt est utilisé pour gérer les dépendances dans toute l'application, assurant une architecture propre et testable.

## Stack Technique

- Kotlin : Langage principal utilisé pour ce projet.
- Jetpack Compose : Utilisé pour construire l'interface utilisateur de manière déclarative.
- Retrofit : Pour effectuer les appels API et récupérer les données des Pokémon.
- Coroutines : Pour gérer les opérations asynchrones de manière efficace.
- Hilt : Pour l'injection de dépendances.
- JUnit : Utilisés pour écrire des tests unitaires.

L'application récupère les données depuis l'API Pokémon (https://pokeapi.co/), qui fournit des informations détaillées sur divers Pokémon.

## Écrans

Liste des Rencontres Pokémon : Affiche une liste paginée de Pokémon.
Écran de Détails du Pokémon : Affiche les détails d'un Pokémon sélectionné dans la liste.

## Comment Exécuter

### Cloner la repo :
``` shell
git clone https://github.com/boubaker50/pokemonEncounter.git
```

### Ouvrir le projet dans Android Studio.
Compiler et exécuter l'application sur un appareil Android ou un émulateur.

### Tests

Des tests unitaires sont écrits pour les logiques métier essentielles mais pas pour mocker les données 

## Améliorations Futures

- Mode Hors-ligne : Mettre en place un mécanisme de cache pour consulter les détails des Pokémon hors-ligne.
- Fonctionnalité de Recherche : Ajouter la possibilité de rechercher un Pokémon par nom ou par type.
- Amélioration de l'interface utilisateur : Ajouter des animations et des transitions plus fluides entre les écrans.
