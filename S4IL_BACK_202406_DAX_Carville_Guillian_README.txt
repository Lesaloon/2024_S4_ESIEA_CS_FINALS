### Backend

#### Génération des Tables

JPA > Generate Tables from Entities

si elle ne fonctionne pas, il faut "Update Maven Project" et "Clean" le projet

#### Configuration de la base de données

par défaut, le projet est configuré pour une base de données MySQL

l'utilisateur par défaut est `root` et il y a pas de mot de passe

#### Test Unitaires

Je ne peut pas faire plus de 58% de couverture de service car 42% du code est des catch ( donc le code est testé mais pas couvert )

#### Log4J

Normalement la configuration de Log4J est bonne mais au lancement il me dit qu'il ne trouve pas l'appenders et donc il ne log rien

Les apelle de log sont dans les services.
Pour les voire il faut commenter toute la configuration de Log4J dans le fichier `log4j2.xml`