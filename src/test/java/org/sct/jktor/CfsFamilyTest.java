package org.sct.jktor;

import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CfsFamilyTest {

    @Test
    void definesRootedCorrectly() {
        Assertions.assertEquals(
            new CfsGroup(
                "A:a.b.c",
                "A:a.b.d"
            ).all().collect(Collectors.toSet()),
            new CfsFamily(
                new CfsGroup(
                    "A:a.b.c",
                    "B:e.f",
                    "A:h",
                    "A:a.b.d"
                ),
                new CfSimple("A:a.b")
            ).all().collect(Collectors.toSet())
        );
    }

    @Test
    void returnsCorrectNames() {
        Assertions.assertEquals(
            Set.of(
                "A",
                "B"
            ),
            Set.of(
                new CfsFamily(
                    new CfsGroup(
                        "A:a.b.c",
                        "B:e.f",
                        "A:h",
                        "A:a.b.d"
                    ),
                    new CfSimple("A:a.b")
                ).names()
            )
        );
    }
}
