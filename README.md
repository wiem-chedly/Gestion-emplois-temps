
Gestion-emplois-temps

I.	Présentation de l’application :
L'application Java SE, développée avec l'IDE Eclipse et reliée à une base de données MySQL nommée "emploidutemps_db.sql", est conçue pour faciliter
la gestion des emplois du temps au sein de l'ISI/UVT. Elle comprend deux interfaces principales : une interface administrateur, qui permet d'accéder 
aux fonctionnalités de gestion, et une interface d'inscription pour permettre aux utilisateurs de s'inscrire et de se connecter. 
Cette application offre plusieurs fonctionnalités clés : enregistrer les enseignants intervenant dans l'établissement, saisir les séances de cours 
pour chaque classe, afficher les séances hebdomadaires d'une matière donnée dans une classe spécifique, et générer les emplois du temps hebdomadaires par classe.
Cette solution centralisée simplifie la gestion des données et assure une organisation efficace des plannings.
II.	 Présentation des interfaces graphiques :
1.	Interface graphique 1 :
<img width="535" height="332" alt="image" src="https://github.com/user-attachments/assets/0685070f-17a8-4412-8162-f8925de059a1" />
               Figure 1 : Interface d’administrateur


o	Fonctionnement de l’interface administrateur
	Interface utilisateur 
o	Fenêtre avec champs de login et mot de passe, boutons "Connexion" et "Annuler", et un lien vers une page d'inscription.
o	Organisation via BorderLayout et GridBagLayout, avec un design simple et une couleur de fond bleue.
	Connexion et vérification :
o	Les informations saisies sont validées via la méthode verifyLogin, qui exécute une requête SQL sécurisée (préparée) pour vérifier la présence des identifiants dans la base de données.
o	Si les identifiants sont corrects, une nouvelle fenêtre (Cours) s'ouvre ; sinon, un message d’erreur s’affiche.
	Gestion des événements :
o	Le bouton "Annuler" réinitialise les champs.
o	Le lien "Inscription" redirige vers un formulaire d'inscription (RegisterForm).

	Connexion à la base de données :
o	Utilisation de JDBC pour interagir avec MySQL. La base emploidutemps_db est hébergée localement.

2.	Interface graphique 2

 <img width="537" height="617" alt="image" src="https://github.com/user-attachments/assets/d7e8d4bb-8a0e-4404-9980-00e5dbbb6e8a" />

           Figure2 : Interface d’inscription


Fonctionnement de l'interface d'inscription :
	Structure de l'interface :
o	Formulaire composé de champs pour saisir le nom, prénom, email, adresse, login et mot de passe.
o	Boutons :
	"S'inscrire" : pour valider l'inscription.
	"Annuler" : pour réinitialiser tous les champs.
o	Lien cliquable "Retour à la page de connexion" pour revenir à l'écran de login.
	Validation et actions :
o	Vérifier que tous les champs sont remplis avant de procéder à l'inscription.
o	Si un champ est vide, afficher un message d'erreur.
o	Si tous les champs sont valides, enregistrer les données saisies dans la base de données emploidutemps_db.
	Enregistrement des données :
o	Utiliser une requête SQL INSERT avec des paramètres sécurisés (requêtes préparées).
o	Si l'enregistrement réussit, afficher un message de confirmation et rediriger vers la page de connexion.
o	Si l'enregistrement échoue, afficher un message d'erreur.
	Navigation et réinitialisation :
o	Retour à la page de connexion : le lien redirige vers l'interface AdminLogin.

3.	Interface graphique 3
 <img width="827" height="487" alt="image" src="https://github.com/user-attachments/assets/8f55b135-63d9-472c-aee7-21689dfd9cbc" />

                 Figure 3 : Interface Cours


Fonctionnement de l'interface Cours
	Structure principale
o	La classe Cours hérite de JFrame et représente une fenêtre graphique.
o	Elle utilise des composants Swing pour créer une interface utilisateur, notamment des champs de texte (JTextField), des étiquettes (JLabel), des tables (JTable), des menus déroulants (JComboBox) et des boutons (JButton).
	Objectifs fonctionnels
 	Gestion des enseignants :
o	Ajouter, modifier ou supprimer les informations d'un enseignant dans la base de données.
o	Rechercher un enseignant par son matricule.
o	Afficher une liste des enseignants enregistrés dans un tableau.
 	Gestion des séances de cours :
o	Enregistrer les informations d'une séance de cours (classe, matière, jour, heure, matricule de l'enseignant).
o	Afficher les séances de cours dans un tableau, classées par ordre décroissant d'ID.
o	Assigner un numéro aux jours de la semaine via une mise à jour SQL.
 	Affichage des données :
o	Les données des enseignants et des séances de cours sont affichées dans deux tableaux distincts.
o	Les tableaux sont remplis dynamiquement avec des données issues des tables tb_enseignant et tb_cours de la base de données.
 	Requêtes supplémentaires :
o	Un bouton "REQUETES" permet d’ouvrir une autre interface pour exécuter des requêtes SQL spécifiques.
	Composants clés et leur rôle
	Champs d’entrée pour les enseignants :
	Matricule : Champ pour entrer ou rechercher le matricule.
	Nom : Champ pour entrer ou afficher le nom de l’enseignant.
	Contact : Champ pour entrer ou afficher les coordonnées.

	Champs pour les séances de cours :
	Classe : Sélection d’une classe à l’aide d’une liste déroulante (JComboBox).
	Matière : Champ de texte pour entrer la matière.
	Jour : Sélection du jour via une liste déroulante.
	Heure : Sélection d’une heure via une liste déroulante.
	Matricule enseignant : Champ pour associer une séance de cours à un enseignant.
	Fonctionnalités des boutons
    Pour les enseignants :
	CHERCHER :
o	Recherche un enseignant dans tb_enseignant par matricule.
o	Remplit les champs avec les données correspondantes si trouvées, ou affiche une erreur si l’enregistrement n'existe pas.
	ENREGISTRER :
o	Ajoute un nouvel enseignant dans la base de données.
o	Vérifie que tous les champs sont remplis avant d’insérer.
	MODIFIER :
o	Met à jour les informations d’un enseignant existant, basé sur le matricule.
o	Nécessite que tous les champs soient remplis.
	SUPPRIMER :
o	Supprime un enseignant par son matricule après confirmation.


Pour les séances de cours :
	ENREGISTRER :
o	Insère une nouvelle séance de cours dans tb_cours.
o	Associe les jours à des numéros via plusieurs requêtes SQL (num_jour).
	REQUETES :
o	Ouvre une nouvelle fenêtre pour exécuter des requêtes SQL.
 Affichage des tableaux
o	Les enseignants sont listés dans un tableau avec les colonnes Matricule, Nom, et Contact.
o	Les séances de cours sont listées dans un autre tableau avec les colonnes Classe, Matière, Jour, Heure, et Enseignant.
 Connexion à la base de données
o	La classe utilise une instance de la classe Connexion pour établir une connexion SQL.
o	Les requêtes SQL sont exécutées à l’aide d’objets Statement et ResultSet.






4.	Interface graphique 4
 <img width="822" height="481" alt="image" src="https://github.com/user-attachments/assets/2334f396-2a40-4d75-aa27-bf4afca91539" />

           Figure 4 : Interface Requetes


Fonctionnement de l'interface Requetes
	Structure générale
o	Classe principale Requetes : Hérite de JFrame pour créer une fenêtre d'application graphique.
o	Attributs principaux :
o	Objets pour la gestion de la base de données : Statement, ResultSet et une instance de la classe Connexion.
o	Composants de l'interface graphique : JTable, JComboBox, JButton, JTextField, JLabel, etc.
	Configuration de la fenêtre
o	Définir le titre de la fenêtre (setTitle) et sa taille (setSize).
o	Désactiver le redimensionnement (setResizable(false)).
o	Ajouter un panneau principal (JPanel) avec un fond coloré en bleu clair (Color(173, 216, 230)).
	Interface pour les recherches
Recherche des séances d'une matière dans une classe
o	Composants :
o	Une liste déroulante (JComboBox) pour sélectionner une classe.
o	Un champ texte (JTextField) pour saisir la matière.
o	Un bouton "CHERCHER" pour effectuer la recherche.
o	Un bouton "ANNULER" pour réinitialiser les champs.
o	Logique :
o	Lorsque l'utilisateur clique sur "CHERCHER", une requête SQL est exécutée pour récupérer les séances correspondantes dans la base de données.
o	Les résultats sont affichés dans un tableau (JTable), avec les colonnes : ID, Classe, Matière, Jour, Heure, Nom enseignant, Contact enseignant.
 Recherche de l'emploi du temps d'une classe
o	Composants :
o	Une liste déroulante (JComboBox) pour sélectionner une classe.
o	Un bouton "CHERCHER" pour lancer la recherche.
o	Un bouton "ANNULER" pour réinitialiser la sélection.
o	Logique :
o	Cliquer sur "CHERCHER" exécute une requête SQL pour récupérer l'emploi du temps complet de la classe.
o	Les résultats sont affichés dans un tableau similaire, triés par jour et heure.

	Suppression d'une séance
o	Composants :
o	Un champ texte (JTextField) pour saisir l'ID de la séance.
o	Un bouton "SUPPRIMER" pour supprimer l'enregistrement correspondant.
o	Un bouton "ANNULER" pour réinitialiser le champ ID.
o	Logique :
o	Lorsque l'utilisateur clique sur "SUPPRIMER", une requête SQL est exécutée pour supprimer la séance identifiée par l'ID.
o	Une confirmation est demandée via une boîte de dialogue (JOptionPane).
o	Un message de succès ou d'erreur est affiché.

	Gestion de la base de données
o	Connexion : Utiliser la méthode laConnection() de la classe Connexion pour établir la connexion à la base de données.
o	Requêtes SQL :
o	Rechercher des séances ou l'emploi du temps dans la table enseignant_cours.
o	Supprimer une séance de la table tb_cours.
o	Gestion des erreurs :
o	Capturer et afficher les exceptions SQL (SQLException) en cas de problème.

	Gestion des tables
•	Initialisation (init2) :
o	Créer une instance de JTable pour afficher les résultats des requêtes.
o	Ajouter la table dans un conteneur défilable (JScrollPane) pour gérer les données volumineuses.


