# Purgatoire

Projet d’étudiants en 1ère année de Polytech Grenoble en filière Informatique.

## But du jeu

Purgatoire est un jeu de plateau/action/aventure en **solo**. Le but du jeu est d’obtenir des points de karma (positifs ou négatifs) dans le but de devenir Dieu ou Satan et ainsi de régner sur le **paradis** ou sur l’**enfer** le plus rapidement possible.

## Contrôles

On déplace le personnage avec les **flèches directionnelles**, on peut activer le pouvoir **wizz** avec la touche **W**, **pop** avec **X** et **hit** avec **C**.

- **Dans l’enfer**, wizz est une attaque de zone, pop est un sort qui augmente pendant 30 secondes les dégâts que le joueur inflige (mais il prend plus de dégâts pendant ce temps) et hit permet de lancer des boules de feu.
- **Dans le paradis**, wizz permet de caresser, pop permet d’effectuer un dash et hit de donner un coup à une case de distance.

## Règles

Le joueur commence la partie en enfer ou au paradis. Si le joueur est en enfer, il lui faudra combattre des âmes damnées et d’autres menaces présentes dans ce monde infernal. Au paradis, il lui faudra esquiver les âmes chétives.

Le jeu se déroule dans un environnement **vertical** composé d’obstacles et d’âmes. Le joueur dispose de plusieurs capacités qui varient en fonction du monde dans lequel il est. Toutes les **30 secondes**, la valeur de son **karma** détermine s’il reste dans l’univers actuel ou s’il change (si le karma est positif, le joueur se retrouvera au paradis, sinon en enfer) et il gagne en **expérience** la valeur absolue de son karma (il en gagne 10 s’il a +10 ou -10 de karma). Son expérience est **partagée** entre l’enfer et le paradis.

À la **transition** lorsqu’on passe de l’enfer au paradis et inversement, des bonus/malus infligés : on perd forcément 10 d’expérience, pour aller en enfer on passe à 50% de points de vie et pour aller au paradis la jauge de points de vie maximum augmente de 10.

### En enfer

En enfer, le joueur est confronté à des âmes damnées qui **attaqueront le joueur** à vue. Le joueur dispose de plusieurs attaques pour se défendre et détruire les différentes menaces de ce monde. En enfer, le joueur doit à tout prix éliminer les menaces présentes afin de diminuer sa quantité de karma au maximum. Le joueur peut marcher dans des flaques d’**eau bénite** (qui serait tombée depuis le paradis) afin de **régénérer ses points de vie** (cependant cela augmente son karma, le joueur devra donc bien réfléchir avant de se soigner).

### Au paradis

Au paradis, le joueur ne doit **pas toucher les âmes** qui l’entourent et il lui faut en plus **caresser** des chats pour augmenter son karma. Son karma diminue s’il ne caresse pas de chat pendant 5 secondes, s’il inflige des dégâts ou s’il tue. Au contact du joueur, les âmes qu’il doit éviter lui font des dégâts et disparaissent (cela tue l’âme).

### En résumé

| Pouvoir | Enfer | Paradis |
|:-------:|-------|---------|
| Pop     | Faiblesse ×2 et dégâts ×2 (cooldown de 30s) | Dash |
| Wizz    | Attaque de zone | Caresse |


| Karma    | Enfer | Paradis |
|:--------:|-------|---------|
| Augmente | Eau bénite ou ne tue pas pendant 5s | Caresse |
| Diminue  | Tue ou blesse | Tue, blesse ou ne caresse pas pendant 5s |

- **Enfer → paradis** : +10 PV max et -10 XP
- **Paradis → enfer** : 50% PV et -10 XP
