package org.sct.jktor;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JoinedTest {

    @Test
    void joinsTwoGroups() {
        this.assertClassifiersEqual(
            Stream.of(
                new StringClassifier("A:"),
                new StringClassifier("A:a"),
                new StringClassifier("A:a.b"),
                new StringClassifier("A:a.b.c"),
                new StringClassifier("B:"),
                new StringClassifier("B:d"),
                new StringClassifier("B:d.e"),
                new StringClassifier("B:d.e.f")
            ),
            new Joined(
                new Group("A:a.b.c"),
                new Group("B:d.e.f")
            ).all()
        );
    }

    @Test
    void joinsGroupAndClassifier() {
        this.assertClassifiersEqual(
            Stream.of(
                new StringClassifier("A:"),
                new StringClassifier("A:a"),
                new StringClassifier("A:a.b"),
                new StringClassifier("A:a.b.c"),
                new StringClassifier("B:"),
                new StringClassifier("B:d")
            ),
            new Joined(
                new Group("A:a.b.c"),
                new StringClassifier("B:d")
            ).all()
        );
    }

    @Test
    void ignoresDuplicates() {
        this.assertClassifiersEqual(
            Stream.of(
                new StringClassifier("A:"),
                new StringClassifier("A:a"),
                new StringClassifier("A:a.b"),
                new StringClassifier("A:a.b.c"),
                new StringClassifier("A:a.b.c.d")
            ),
            new Joined(
                new Group("A:a.b.c"),
                new Group("A:a.b.c.d")
            ).all()
        );
    }

    private void assertClassifiersEqual(
        final Stream<Classifier> expected,
        final Stream<Classifier> actual) {

        Assertions.assertIterableEquals(
            expected.map(Object::toString).sorted().toList(),
            actual.map(Object::toString).sorted().toList()
        );
    }
}
