# JKtor - Simple library for manipulating classifiers

Classifiers help to categorize entities. Classifier consists of _name_ and _path_.
Name of a classifier defines a family. Within family classifiers are organized
hierarchically based on their path.

Classifier can be defined by a string:
```java
Classifier classifier = new CfString("counterparty:clients.domestic");
classifier.name();              // "counterparty"
classifier.parent().toString(); // "counterparty:clients"
classifier.depth();             // 2
```

Working with group:
```java
Classifiers group = new SimpleGroup(
    "cgry:c1.c2",
    "cgry:c1.c3",
    "cgry:c2.c4",
    "prj:p1.p2.p3",
);
group.names()              // "cgry", "prj"
group.size                 // 9
```

Single classifier defines a group/family:
```java
Classifiers group = new SimpleGroup(
    "A:a.b.c"
)  // "A:", 
   // |- "A:a", 
   //    |- "A:a.b",
   //       |- "A:a.b.c"
```

Family can be extracted from a group by providing its member:
```java
new Family(
    new SimpleGroup(
        "A:a.b.c.f",
        "B:e.f",
        "A:h",
        "A:a.b.d.e"
    ),
    new CfString("A:a.b")
)   // "A:"
    // |- "A:a"
    //    |- "A:a.b"
    //       |- "A:a.b.c"
    //       |  |- "A:a.b.c.f"
    //       |- "A:a.b.d"
    //          |- "A:a.b.d.e"
```

Classifiers can be read from a file:
`hierarchy.txt`
```
project:ALPHA
project:ALPHA.Y
importance:high
```
```java
new FileGroup("hierarchy.txt")
```
