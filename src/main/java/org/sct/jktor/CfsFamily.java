package org.sct.jktor;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CfsFamily implements Classifiers {

    private final Classifiers origin;
    private final Classifier root;

    public CfsFamily(final Classifiers origin, final Classifier root) {
        this.origin = origin;
        this.root = root;
    }

    @Override
    public long size() {
        return this.all().count();
    }

    @Override
    public Set<String> names() {
        return this.all()
            .map(Classifier::name).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Stream<Classifier> all() {
        return new CfsGroup(
            this.origin.all()
                .filter(
                    cfr -> cfr.parent().equals(this.root)
                ).toArray(Classifier[]::new)
        ).all();
    }

    @Override
    public Iterator<Classifier> iterator() {
        return this.all().iterator();
    }
}
