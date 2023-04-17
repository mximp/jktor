package org.sct.jktor;

import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FamilyTest {

    @Test
    void definesFamilyCorrectly() {
        Assertions.assertEquals(
            new Group(
                "A:a.b.c.f",
                "A:a.b.d.e"
            ).all().collect(Collectors.toSet()),
            new Family(
                new Group(
                    "A:a.b.c.f",
                    "B:e.f",
                    "A:h",
                    "A:a.b.d.e"
                ),
                new StringClassifier("A:a.b")
            ).all().collect(Collectors.toSet())
        );
    }

    @Test
    void returnsCorrectNames() {
        Assertions.assertEquals(
            Set.of(
                "A"
            ),
            new Family(
                new Group(
                    "A:a.b.c",
                    "B:e.f",
                    "A:h",
                    "A:a.b.d"
                ),
                new StringClassifier("A:a.b")
            ).names()
        );
    }
}
