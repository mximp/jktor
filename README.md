# JKtor - Simple library for manipulating classifiers

Classifiers help to categorize entities. Classifier consists of _name_ and _path_.
Classifiers' paths comprise the hierarchy of classifiers. 

Creating classifier from classifier string:
```java
Classifier cfr = new CfSimple("counterparty:clients.domestic");
cfr.name();              // "counterparty"
cfr.value();             // "clients.domestic"
cfr.parent().toString(); // "counterparty:clients"
cfr.depth();             // 2
```

Working with hierarchy:
```java
Classifiers cfrs = new Classifiers(
    "cgry:c1.c2",
    "cgry:c1.c3",
    "cgry:c2.c4",
    "prj:p1.p2.p3",
);
cfrs.names()              // "cgry", "prj"
cfrs.size                 // 9
```
