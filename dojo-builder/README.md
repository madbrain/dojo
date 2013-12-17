Dojo Builder
============

Le but de cet excercice est d'illustrer le patron de conception (Design Pattern) Builder,
en remplacant la construction d'un DOM XML par l'API DOM3, par un builder beaucoup
plus clair et orienté métier utilisant l'API Fluent ().

Dans un deuxième temps le builder pourra être améliorer pour exprimer un vrai DSL
(http://en.wikipedia.org/wiki/Domain-specific_language) et n'accepter que des constructions
viables, par exemple `newModel(...)` ne peut être appelé qu'après `newArticle(String)`.

Pour plus d'info :
http://blog.jooq.org/2012/01/05/the-java-fluent-api-designer-crash-course/
