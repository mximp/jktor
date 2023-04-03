# JKtor - Simple library for creating classifiers
Classifiers helps to categorize entities. Classifier consists of _name_ and _path_.
Classifiers' paths comprise the hierarchy of classfiers. 

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
