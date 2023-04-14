package org.sct.jktor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CfsSimple implements Classifiers {

    private Iterable<Classifier> source;

    public CfsSimple(final CfSimple cfSimple) {
        this.source = List.of(cfSimple);
    }

    @Override
    public long size() {
        final List<Classifier> classifiers = new ArrayList<>();
        source.forEach(classifiers::add);
        return classifiers.stream().flatMap(
            cfr -> {
                Classifier parent = cfr;
                List<Classifier> list = new ArrayList<>();
                while (!parent.equals(new CfSimple(":"))) {
                    list.add(parent);
                    parent = parent.parent();
                }
                return list.stream();
            }
        ).distinct().count();
    }

    @Override
    public Iterable<Classifier> list() {
        return null;
    }

    @Override
    public Classifiers rootedAt(final Classifier cfr) {
        return null;
    }

    @Override
    public Iterator<Classifier> iterator() {
        return null;
    }
}
