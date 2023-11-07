package org.sct.jktor;

import java.util.Set;
import java.util.stream.Stream;

/**
 * Joining two groups.
 */
public class Joined implements Group {

    /**
     * Join result.
     */
    private final Group joined;

    /**
     * Ctor.
     * @param left Left
     * @param right Right
     */
    public Joined(Group left, Group right) {
        this.joined = new SimpleGroup(
            () -> Stream.concat(
                left.all(),
                right.all()
            ).toList()
        );
    }

    /**
     * Ctor.
     * @param group Group
     * @param classifier Classifier being added
     */
    public Joined(Group group, Classifier classifier) {
        this.joined = new SimpleGroup(
            () -> Stream.concat(
                group.all(),
                new SimpleGroup(classifier).all()
            ).toList()
        );
    }

    @Override
    public long size() {
        return this.joined.size();
    }

    @Override
    public Set<String> names() {
        return this.joined.names();
    }

    @Override
    public Stream<Classifier> all() {
        return this.joined.all();
    }

    @Override
    public boolean contains(final Classifier classifier) {
        return false;
    }
}
