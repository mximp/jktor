# JKtor - Simple library for creating classifiers
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
    new CfSimple("cgry:c1.c2"),
    new CfSimple("cgry:c1.c3"),
    new CfSimple("cgry:c2.c4"),
    new CfSimple("prj:p1.p2.p3"),
);
cfrs.list()                    // "cgry:", "prj:"
cfrs.rootedAt("cgry:").list()  // "cgry:c1", "cgry:c2"
cfrs.rootedAt("prj:p1").list() // "prj:p1.p2"
```
