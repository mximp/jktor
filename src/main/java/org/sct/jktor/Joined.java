package org.sct.jktor;

import java.util.Set;
import java.util.stream.Stream;

/**
 * Joining two groups.
 */
public class Joined implements Classifiers {

    private final Classifiers joined;

    public Joined(Classifiers left, Classifiers right) {
        this.joined = new Group(
            () -> Stream.concat(
                left.all(),
                right.all()
            ).toList()
        );
    }

    public Joined(Classifiers group, Classifier classifier) {
        this.joined = new Group(
            () -> Stream.concat(
                group.all(),
                new Group(classifier).all()
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
}
