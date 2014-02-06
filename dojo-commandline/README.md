Dojo Builder
============

Le but de cet excercice est d'illustrer une approche déclarative de la programmation,
au travers d'un exemple de code écrit de façon très impérative et de le réusiner en deux étape :
 * Dans un premier temps, scinder le code en deux parties distinctes un partie générique (indépendante de l'objet MyOptions)
 et une partie spécifique à l'application.
 La partie générique reposera sur un modèle (pouvant être considéré dans ce contexte comme un méta-modèle)
 de spécification de lignes de commandes à établir (quel nom d'option, combien d'argument, quels sont leur type, etc.),
 la partie spécifique à l'application étant une instanciation de ce modèle.

 * Dans un deuxième temps l'instance de modèle spécifique à l'application pourra être automatique
 dérivée de l'objet MyOption (ou plus plécisemment de sa classe) grâce aux annotations.

Pour plus d'info :
http://javapapers.com/core-java/java-reflection-cheat-sheet/
