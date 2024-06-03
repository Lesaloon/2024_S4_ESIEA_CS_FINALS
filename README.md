# 2024_S4_ESIEA_CS_FINALS

## Installation

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


### Frontend

#### Dépendances

Bulma CSS
QuillJS version 25

#### Configuration

Le frontend est configuré pour appeler l'API sur `localhost:8080`

dans le fichier `angular.json`, remplacer "style" par

```json
"styles": [
  "src/styles.css",
  "node_modules/bulma/css/bulma.min.css",
  "node_modules/quill/dist/quill.snow.css"
],
```


aussi le style.css doit contenir

```css
/* You can add global styles to this file, and also import other style files */
@import "bulma/css/bulma.css";
@import "quill/dist/quill.snow.css";

body, html {
	min-height: 100vh;
	margin: 0;
	display: flex;
	flex-direction: column;
}
```


# Si vous voullez tout le projet

Il est disponible sur [GitHub](https://github.com/Lesaloon/2024_S4_ESIEA_CS_FINALS)
il suffit de cloner le projet et de lancer les commandes suivantes

```bash
cd 2024_S4_ESIEA_CS_FINALS
cd frontend
npm install
ng serve
```

pour le back utiliser Eclipse pour lancer le projet.