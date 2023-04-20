package org.sct.jktor;

import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JoinedTest {

    @Test
    void joinsTwoGroups() {
        this.assertClassifiersEqual(
            Stream.of(
                new CfString("A:"),
                new CfString("A:a"),
                new CfString("A:a.b"),
                new CfString("A:a.b.c"),
                new CfString("B:"),
                new CfString("B:d"),
                new CfString("B:d.e"),
                new CfString("B:d.e.f")
            ),
            new Joined(
                new SimpleGroup("A:a.b.c"),
                new SimpleGroup("B:d.e.f")
            ).all()
        );
    }

    @Test
    void joinsGroupAndClassifier() {
        this.assertClassifiersEqual(
            Stream.of(
                new CfString("A:"),
                new CfString("A:a"),
                new CfString("A:a.b"),
                new CfString("A:a.b.c"),
                new CfString("B:"),
                new CfString("B:d")
            ),
            new Joined(
                new SimpleGroup("A:a.b.c"),
                new CfString("B:d")
            ).all()
        );
    }

    @Test
    void ignoresDuplicates() {
        this.assertClassifiersEqual(
            Stream.of(
                new CfString("A:"),
                new CfString("A:a"),
                new CfString("A:a.b"),
                new CfString("A:a.b.c"),
                new CfString("A:a.b.c.d")
            ),
            new Joined(
                new SimpleGroup("A:a.b.c"),
                new SimpleGroup("A:a.b.c.d")
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
