package org.sct.jktor;

/**
 * A hierarchy of classifiers.
 */
public interface Classifiers extends Iterable<Classifier> {

    /**
     * Number of classifiers in the hierarchy.
     * All interim classifiers are counted.
     * @return Number of classifiers.
     */
    long size();

    /**
     * Top-level classifiers of the hierarchy.
     * @return Classifiers
     */
    Iterable<Classifier> list();

    /**
     * Hierarchy rooted at given classifier
     * @param cfr Desired root classifier
     * @return A hierarchy
     */
    Classifiers rootedAt(Classifier cfr);
}
