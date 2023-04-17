package org.sct.jktor;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClassifiersFileSourceTest {

    @Test
    void readsClassifiersFromFile() throws URISyntaxException {
        Assertions.assertEquals(
            new Group(
                new StringClassifier("A:a.b.c"),
                new StringClassifier("B:d.e.f.k"),
                new StringClassifier("S:m.l"),
                new StringClassifier("P:p1.p2.p3.p4.p5")
            ).all().collect(Collectors.toUnmodifiableSet()),
            new Group(
                new ClassifiersFileSource(
                    Paths.get(this.getClass().getClassLoader().getResource("sample.txt").toURI())
                )
            ).all().collect(Collectors.toUnmodifiableSet())
        );
    }

    @Test
    void failsOnIncorrectFileFormat() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new Group(
                new ClassifiersFileSource(
                    Paths.get(this.getClass().getClassLoader().getResource("sample-incorrect.txt").toURI())
                )
            ).size()
        );
    }
}
