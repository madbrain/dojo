Dojo Reverse Gui
================

Le but de cet excercice est développer un outil de rétro-conception d'IHM.
L'entrée de l'outil est un ensemble de widgets typés et leurs coordonnées à l'écran,
la sortie est une arborescence composée des objets du package `com.open.dojo.reversegui.mode`.

Les règles à suivre sont les suivantes :
 * un `Formfield` est composé d'un label à gauche et de un ou plusieurs champs texte sur la même ligne à droite,
 * un `GroupBox` est composé d'un label sur le bord haut d'une widget frame, le groupe contient des `FormField` dans l'ordre de haut en bas,
 * une `ToolBar` est un ensemble de boutons sur la même ligne, de gauche à droite.