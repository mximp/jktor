package org.sct.jktor;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FileGroupTest {

    @Test
    void readsClassifiersFromFile() throws URISyntaxException {
        Assertions.assertEquals(
            new SimpleGroup(
                new CfString("A:a.b.c"),
                new CfString("B:d.e.f.k"),
                new CfString("S:m.l"),
                new CfString("P:p1.p2.p3.p4.p5")
            ).all().collect(Collectors.toUnmodifiableSet()),
            new FileGroup(
                Paths.get(this.getClass().getClassLoader().getResource("sample.txt").toURI())
            ).all().collect(Collectors.toUnmodifiableSet())
        );
    }

    @Test
    void failsOnIncorrectFileFormat() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new FileGroup(
                Paths.get(this.getClass().getClassLoader().getResource("sample-incorrect.txt").toURI())
            ).size()
        );
    }

    @Test
    void failsOnMissingFile() {
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new FileGroup(Paths.get("/a/b/c.txt")).names()
        );
    }
}
