package org.sct.jktor;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A family defined by a member.
 * Contains only classifiers from single family.
 */
public final class Family implements Classifiers {

    private final Classifiers rooted;

    public Family(final Classifiers origin, final Classifier member) {
        this.rooted = new Group(
            () -> origin.all()
                .filter(
                    cfr -> cfr.parent().equals(member)
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

    @Override
    public Iterator<Classifier> iterator() {
        return this.rooted.iterator();
    }
}
