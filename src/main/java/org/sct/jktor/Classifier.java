package org.sct.jktor;

/**
 * A classifier.
 *
 * @since 1.0
 * @author Maxim Petrov
 */
public interface Classifier {

    /**
     * Name.
     * @return A string with name
     */
    String name();

    /**
     * Depth of classifier path.
     * @return Depth
     */
    int depth();

    /**
     * Get parent of this.
     * @return Parent {@link Classifier}
     */
    Classifier parent();
}
