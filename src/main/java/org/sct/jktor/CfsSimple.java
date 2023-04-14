package org.sct.jktor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Simple group of classifiers.
 */
public class CfsSimple implements Classifiers {

    /**
     * Classifiers.
     */
    private final Iterable<Classifier> source;

    /**
     * Ctor.
     * @param classifiers Sources.
     */
    public CfsSimple(final Classifier... classifiers) {
        this.source = List.of(classifiers);
    }

    public CfsSimple(final String... classifiers) {
        this(Arrays.stream(classifiers).map(CfSimple::new).toArray(Classifier[]::new));
    }

    @Override
    public long size() {
        return StreamSupport.stream(this.all().spliterator(), false).count();
    }

    @Override
    public Iterable<String> names() {
        return StreamSupport.stream(this.all().spliterator(), false)
            .map(Classifier::name).distinct().toList();
    }

    @Override
    public Iterator<Classifier> iterator() {
        return this.all().iterator();
    }

    /**
     * Get all distinct classifiers in the group.
     * @return Classifiers.
     */
    private Iterable<Classifier> all() {
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
        ).distinct().toList();
    }
}
