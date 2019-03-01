
# Dojo LCR Contact

Le but de cet exercice est de mettre en pratique des techniques de refactoring de code permettant
de rendre un code testable et par la même de réduire sa dette technique.

Dans un premier temps il faut isoler la partie métier de l'application de toutes les parties périphériques
difficilement testables en suivant les préceptes de [l'architecture hexagonale](https://blog.octo.com/architecture-hexagonale-trois-principes-et-un-exemple-dimplementation/).
Le chemin est semé d'embuches, mais nous avons une panoplie d'armes à notre disposition :

 * après avoir isoler à l'aide d'interfaces les parties de code non testable (les «Ports»), les mocker par polymorphisme,
 * l'injection de dépendances pour pouvoir controller l'assemblage de l'application avec nos mock,
 * l'élimination des fonctions statiques en service singleton puis injection,
 * utilisation du pattern [MVP](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter)
 * ne pas hésiter à réduire l'interface visible d'une classe à son minimum pour simplifier son test,
 * etc.

Dans un deuxième temps couvrir le code de tests, et enfin réfactorer allègrement.
