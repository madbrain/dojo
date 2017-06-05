Dojo Builder
============

Le but de cet excercice est d'illustrer le patron de conception (Design Pattern) Builder,
en remplacant la construction d'une arboresence d'objets, par un builder beaucoup
plus clair et orienté métier utilisant l'API Fluent (https://en.wikipedia.org/wiki/Fluent_interface).

Ensuite le builder pourra être améliorer pour exprimer un vrai DSL
(http://en.wikipedia.org/wiki/Domain-specific_language) et n'accepter que des constructions
viables, par exemple `model(...)` ne peut être appelé qu'après `article(String)`.

Enfin l'aspect déclaratif du builder pourra être mixé avec un aspect plus impératif
en incluant des lambdas Java 8.

Pour plus d'info :
http://blog.jooq.org/2012/01/05/the-java-fluent-api-designer-crash-course/
