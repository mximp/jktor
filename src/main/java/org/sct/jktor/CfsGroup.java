package org.sct.jktor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Simple group of classifiers.
 */
public class CfsGroup implements Classifiers {

    /**
     * Classifiers.
     */
    private final Iterable<Classifier> source;

    /**
     * Ctor.
     * @param classifiers Sources.
     */
    public CfsGroup(final Classifier... classifiers) {
        this.source = List.of(classifiers);
    }

    public CfsGroup(final String... classifiers) {
        this(
            Arrays.stream(classifiers)
                .map(CfSimple::new)
                .toArray(Classifier[]::new)
        );
    }

    @Override
    public long size() {
        return this.all().count();
    }

    @Override
    public Set<String> names() {
        return this.all()
            .map(Classifier::name)
            .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Stream<Classifier> all() {
        final List<Classifier> classifiers = new ArrayList<>();
        source.forEach(classifiers::add);
        return classifiers.stream().flatMap(
            cfr -> this.parents(cfr).stream()
        ).distinct();
    }

    @Override
    public Iterator<Classifier> iterator() {
        return this.all().iterator();
    }

    /**
     * All parents for given classifier.
     *
     * @param cfr Base classifier
     * @return All parents
     */
    private Set<Classifier> parents(final Classifier cfr) {
        Classifier parent = cfr;
        Set<Classifier> result = new HashSet<>();
        while (!parent.equals(new CfSimple(":"))) {
            result.add(parent);
            parent = parent.parent();
        }
        return result;
    }
}
