# JKtor - Simple library for creating classifiers
Classifiers helps to categorize entities. Classifier consists of name and path.

### Usage
Creating classfier from classifier string:
```
Classifier cfy = new CfSimple("counterparty:clients.domestic");

cfy.name();              // "counterparty"
cfy.value();             // "clients.domestic"
cfy.parent().toString(); // "counterparty:clients"
cfy.depth();             // 2
```

Creating child classifier:
```
Classifier child = new CfSimple(
    new Classifier("counterparty:clients.domestic"), 
    "Max"
);

child.toString(); // "counterparty:clients.domestic.Max"
```
