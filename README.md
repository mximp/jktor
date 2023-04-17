# JKtor - Simple library for manipulating classifiers

Classifiers help to categorize entities. Classifier consists of _name_ and _path_.
Classifiers' paths comprise the hierarchy of classifiers. 

Classifier is defined by a string:
```java
Classifier cfr = new CfSimple("counterparty:clients.domestic");
cfr.name();              // "counterparty"
cfr.parent().toString(); // "counterparty:clients"
cfr.depth();             // 2
```

Working with group:
```java
Classifiers cfrs = new CfsGroup(
    "cgry:c1.c2",
    "cgry:c1.c3",
    "cgry:c2.c4",
    "prj:p1.p2.p3",
);
cfrs.names()              // "cgry", "prj"
cfrs.size                 // 9
```

Single classifier defines a family:
```java
Classifiers group = new CfsGroup(
    "A:a.b.c"
).all();                  // "A:", "A:a", "A:a.b", "A:a.b.c"
```
