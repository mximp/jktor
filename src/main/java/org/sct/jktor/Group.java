package org.sct.jktor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Simple group of classifiers.
 * May contain different families.
 */
public final class Group implements Classifiers {

    /**
     * Classifiers.
     */
    private final Supplier<Iterable<Classifier>> source;

    /**
     * Ctor.
     * @param classifiers Sources.
     */
    public Group(final Classifier... classifiers) {
        this(() -> List.of(classifiers));
    }

    public Group(final String... classifiers) {
        this(() -> Arrays.stream(classifiers)
                .map(StringClassifier::new)
                .collect(Collectors.toList())
        );
    }

    public Group(final Supplier<Iterable<Classifier>> source) {
        this.source = source;
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
        source.get().forEach(classifiers::add);
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
        while (!parent.equals(new StringClassifier(":"))) {
            result.add(parent);
            parent = parent.parent();
        }
        return result;
    }
}
