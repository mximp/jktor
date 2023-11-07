package org.sct.jktor;

import java.util.Set;
import java.util.stream.Stream;

/**
 * A bunch of classifiers.
 */
public interface Group {

    /**
     * Number of classifiers in the hierarchy.
     * All interim classifiers are counted.
     * @return Number of classifiers.
     */
    long size();

    /**
     * Family names within the group
     * @return Strings representing the names
     */
    Set<String> names();

    /**
     * All distinct classifiers as stream.
     * @return Classifier stream.
     */
    Stream<Classifier> all();

    /**
     * If this group contains given classifiers.
     * @param classifier Element to check.
     * @return True if present, false otherwise.
     */
    boolean contains(Classifier classifier);
}
