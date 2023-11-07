package org.sct.jktor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Simple group of classifiers.
 * May contain different families (names).
 */
public final class SimpleGroup implements Group {

    /**
     * Classifiers.
     */
    private final Supplier<Iterable<Classifier>> source;

    /**
     * Ctor.
     * @param classifiers Sources.
     */
    public SimpleGroup(final Classifier... classifiers) {
        this(() -> List.of(classifiers));
    }

    /**
     * Ctor.
     * @param classifiers Members
     */
    public SimpleGroup(final String... classifiers) {
        this(() -> Arrays.stream(classifiers)
                .map(CfString::new)
                .collect(Collectors.toList())
        );
    }

    /**
     * Ctor.
     * @param source Members
     */
    public SimpleGroup(final Supplier<Iterable<Classifier>> source) {
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
        return StreamSupport.stream(source.get().spliterator(), false)
            .flatMap(cfr -> this.parents(cfr).stream())
            .sorted(Comparator.comparing(Classifier::toString))
            .distinct();
    }

    @Override
    public boolean contains(final Classifier classifier) {
        return this.all().anyMatch(classifier::equals);
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
        while (!parent.equals(new CfString(":"))) {
            result.add(parent);
            parent = parent.parent();
        }
        return result;
    }
}
