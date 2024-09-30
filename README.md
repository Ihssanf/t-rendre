# Projet MySQL JDBC

Ce projet démontre l'utilisation de JDBC pour interagir avec une base de données MySQL. Le programme se connecte à une base de données, crée une table, insère des données, et exécute diverses requêtes SQL pour extraire des informations et afficher des résultats dans la console.

## Fonctionnalités
- Connexion à une base de données MySQL.
- Création d'une table `DevData` si elle n'existe pas.
- Insertion de données représentant des développeurs et le nombre de scripts qu'ils ont réalisés sur différentes journées.
- Affichage des développeurs ayant réalisé le plus de scripts pour chaque jour.
- Calcul et affichage du nombre total de scripts par développeur, classés par ordre décroissant.
- Calcul du nombre total de scripts réalisés en une semaine.
- Recherche du nombre total de scripts réalisés par un développeur spécifique à l'aide d'une saisie utilisateur.
- Exécution de requêtes SQL personnalisées saisies par l'utilisateur.

## Structure du projet
Le projet contient les classes suivantes :

- **ExoJDBC** : Gère les interactions avec la base de données (création de table, insertion, requêtes SQL).
- **DriverManager** : Gère la connexion à la base de données MySQL.
- **Test** : Contient la méthode `main` pour exécuter le programme.

## Prérequis
- **Java** 8 ou supérieur.
- **MySQL** installé et en cours d'exécution.
- Le driver JDBC pour MySQL doit être ajouté au classpath du projet (par exemple, `mysql-connector-java.jar`).

## Installation et configuration

1. **Téléchargez ou clonez** ce dépôt.

2. **Base de données** :
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

3. **Ajoutez le driver MySQL** à votre classpath :
   - Téléchargez le fichier `mysql-connector-java.jar` depuis le [site officiel de MySQL](https://dev.mysql.com/downloads/connector/j/).
   - Ajoutez-le à votre projet.

## Utilisation

1. Compilez et exécutez la classe `Test` pour lancer le programme.

2. Le programme effectuera les opérations suivantes :
   - Connexion à la base de données.
   - Insertion des données de développeurs dans la table `DevData`.
   - Affichage des développeurs ayant réalisé le plus de scripts pour chaque jour.
   - Affichage du nombre total de scripts réalisés par chaque développeur, trié par ordre décroissant.
   - Calcul et affichage du nombre total de scripts réalisés au cours de la semaine.
   - Demande à l'utilisateur d'entrer un nom de développeur pour voir combien de scripts ce développeur a réalisés.
   - Permet à l'utilisateur d'exécuter une requête SQL personnalisée.

![image](https://github.com/user-attachments/assets/6a359dec-ae6e-4431-9274-d85562bea9cc)
Figure 1:classe DriverManager

![image](https://github.com/user-attachments/assets/317de778-93fb-4188-b693-114f5ea70673)
![image](https://github.com/user-attachments/assets/00735445-a213-479b-88dc-96a398051888)

Figure 2:creatio et initialisation de la table DevData

![image](https://github.com/user-attachments/assets/471c780d-f6be-4957-a9c8-1b5473cc1516)

Figure 3:maximum de scripts en une journée

![image](https://github.com/user-attachments/assets/e7eaef4c-b317-4f28-860f-3374d9546e37)

Figure 4:•	la liste des personnes triée dans l’ordre décroissant selon leur nombre de scripts

![image](https://github.com/user-attachments/assets/bd2878fe-c52b-42b4-80e9-253a24f5a0a2)

Figure 5:calcule et affichage le nombre total de scripts réalisés en une semaine

![image](https://github.com/user-attachments/assets/813eec30-2e44-4fec-9258-ccdbb44e0aee)

Figure 6:calculer pour un programmeur donné le nombre total de scripts réalisés.

![image](https://github.com/user-attachments/assets/ba17b6d6-8600-44b6-ade7-bc3be553c0be)
![image](https://github.com/user-attachments/assets/747e4b36-ebfa-4ed7-b8d8-0172b793c5d6)

Figure 7:exécuter une requête SQL

![image](https://github.com/user-attachments/assets/8bd9699a-57cb-4a97-b7b0-24a83c404853)
![image](https://github.com/user-attachments/assets/67290219-ff0b-4986-a57c-5c99de03bac3)

Figure 8:la methode main

![image](https://github.com/user-attachments/assets/7b13d540-bef2-40c7-971e-e53116d0140d)
![image](https://github.com/user-attachments/assets/ffa013b5-316a-49a7-9e1f-bfddb8feb54b)

Figure 9:resultat finale lors de l'execution

![image](https://github.com/user-attachments/assets/40f51aaf-5bc8-4f3d-8a3c-a2dde43a0516)

Figure 10:ma base de donnes




















