Dojo LCR Cocktail
=================

Le but de cet excercice est de mettre en pratique des techniques de refactoring de code
permettant de rendre un code testable et par la même de réduire sa dette technique.

Dans un premier temps il faut isoler la partie métier de l'application de toutes les parties périphériques
difficilement testables en suivant les préceptes de l'architecture hexagonale (http://alistair.cockburn.us/Hexagonal+architecture).
Le chemin est semé d'embuches, mais nous avons une panoplie d'arme à notre disposition :
 * après avoir isoler à l'aide d'interfaces les parties de code non testable (les «Ports»), les mocker par polymorphisme,
 * l'injection de dépendances pour pouvoir controller l'assemblage de l'application avec nos mock,
 * l'élimination des fonctions statiques en service singleton puis injection,
 * ne pas hésiter à réduire l'interface visible d'une classe à son minimum pour simplifier son test,
 * etc.

Dans un deuxième temps couvrir le code de tests, et enfin réfactorer allègrement.
