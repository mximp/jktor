package org.sct.jktor;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Extracts family by a member.
 * A family defined by a member are all classifiers which have
 * the member as immediate or interim parent plus all its parents.
 */
public final class Family implements Classifiers {

    private final Classifiers rooted;

    public Family(final Classifiers origin, final Classifier member) {
        this.rooted = new Group(
            () -> origin.all()
                .filter(
                    cfr -> new Group(cfr.parent()).all().anyMatch(member::equals)
                ).collect(Collectors.toList())
        );
    }

    @Override
    public long size() {
        return this.rooted.size();
    }

    @Override
    public Set<String> names() {
        return this.rooted.names();
    }

    @Override
    public Stream<Classifier> all() {
       return this.rooted.all();
    }
}
