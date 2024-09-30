Projet MySQL JDBC

Ce projet démontre l'utilisation de JDBC pour interagir avec une base de données MySQL. Le programme se connecte à une base de données, crée une table, insère des données, et exécute diverses requêtes SQL pour extraire des informations et afficher des résultats dans la console.

 Fonctionnalités
- Connexion à une base de données MySQL.
- Création d'une table `DevData` si elle n'existe pas.
- Insertion de données représentant des développeurs et le nombre de scripts qu'ils ont réalisés sur différentes journées.
- Affichage des développeurs ayant réalisé le plus de scripts pour chaque jour.
- Calcul et affichage du nombre total de scripts par développeur, classés par ordre décroissant.
- Calcul du nombre total de scripts réalisés en une semaine.
- Recherche du nombre total de scripts réalisés par un développeur spécifique à l'aide d'une saisie utilisateur.
- Exécution de requêtes SQL personnalisées saisies par l'utilisateur.

 Structure du projet
Le projet contient les classes suivantes :

- ExoJDBC : Gère les interactions avec la base de données (création de table, insertion, requêtes SQL).
- DriverManager : Gère la connexion à la base de données MySQL.
- **Test** : Contient la méthode `main` pour exécuter le programme.

 Prérequis
- Java
- MySQL installé et en cours d'exécution.
- Le driver JDBC pour MySQL doit être ajouté au classpath du projet (par exemple, `mysql-connector-java.jar`).

 Installation et configuration

1. Téléchargez ou clonez ce dépôt.

2. Base de données :
   - Créez une base de données MySQL nommée `basef` :
     ```sql
     CREATE DATABASE basef;
     ```
   - Mettez à jour les informations de connexion (URL, nom d'utilisateur, mot de passe) dans la classe `DriverManager` si nécessaire :
     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/basef";
     private static final String USER = "root";
     private static final String PASSWORD = "";
     ```

3. Ajoutez le driver MySQL à votre classpath :
   - Téléchargez le fichier `mysql-connector-java.jar` depuis le [site officiel de MySQL](https://dev.mysql.com/downloads/connector/j/).
   - Ajoutez-le à votre projet.

 Utilisation

1. Compilez et exécutez la classe `Test` pour lancer le programme.

2. Le programme effectuera les opérations suivantes :
   - Connexion à la base de données.
   - Insertion des données de développeurs dans la table `DevData`.
   - Affichage des développeurs ayant réalisé le plus de scripts pour chaque jour.
   - Affichage du nombre total de scripts réalisés par chaque développeur, trié par ordre décroissant.
   - Calcul et affichage du nombre total de scripts réalisés au cours de la semaine.
   - Demande à l'utilisateur d'entrer un nom de développeur pour voir combien de scripts ce développeur a réalisés.
   - Permet à l'utilisateur d'exécuter une requête SQL personnalisée.
   - ![image](https://github.com/user-attachments/assets/04eeb799-6f82-4ada-83d3-d3c426f97728)




