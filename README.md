# JKtor - Simple library for manipulating classifiers

Classifiers help to categorize entities. Classifier consists of _name_ and _path_.
Classifiers' paths comprise the hierarchy of classifiers. 

Classifier is defined by a string:
```java
Classifier classifier = new StringClassifier("counterparty:clients.domestic");
classifier.name();              // "counterparty"
classifier.parent().toString(); // "counterparty:clients"
classifier.depth();             // 2
```

Working with group:
```java
Classifiers group = new Group(
    "cgry:c1.c2",
    "cgry:c1.c3",
    "cgry:c2.c4",
    "prj:p1.p2.p3",
);
group.names()              // "cgry", "prj"
group.size                 // 9
```

Single classifier defines a family:
```java
Classifiers group = new Group(
    "A:a.b.c"
).all();                  // "A:", 
                          // |- "A:a", 
                          //    |- "A:a.b",
                          //       |- "A:a.b.c"
```

Family can be extracted from a group by providing its member:
```java
new Family(
    new Group(
        "A:a.b.c.f",
        "B:e.f",
        "A:h",
        "A:a.b.d.e"
    ),
    new StringClassifier("A:a.b")
).all().collect(Collectors.toSet()) // "A:"
                                    // |- "A:a"
                                    //    |- "A:a.b"
                                    //       |- "A:a.b.c"
                                    //       |  |- "A:a.b.c.f"
                                    //       |- "A:a.b.d"
                                    //          |- "A:a.b.d.e"

```
