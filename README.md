# jktor
Simple classification library

### Usage
Creating classfier from classifier string:
```
Classifier cfy = new Classifier("counterparty:clients.domestic");

cfy.type();              // "counterparty"
cfy.value();             // "clients.domestic"
cfy.parent().toString(); // "counterparty:clients"
cfy.depth();             // 2
```

Creating child classifier:
```
Classifier child = new Classifier(
    new Classifier("counterparty:clients.domestic"), 
    "Max"
);

child.toString(); // "counterparty:clients.domestic.Max"
```
