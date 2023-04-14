package org.sct.jktor;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CfsSimpleTest {

    @ParameterizedTest
    @CsvSource({
        "cgry:c1.c2.c3, 4",
        "cgry:, 1",
        ":, 0"
    })
    void hasValidSizeForSingleClassifier(String classifier, String size) {
        Assertions.assertEquals(
            Long.valueOf(size),
            new CfsSimple(
                classifier
            ).size()
        );
    }

    @Test
    void hasValidSizeForSeveral() {
        Assertions.assertEquals(
            Long.valueOf(7),
            new CfsSimple(
                "A:a1.a2.a3", // 4
                "A:a1.a2",    // ignored
                "B:b1",       // 2
                "C:",         // 1
                ":"           // 0
            ).size()
        );
    }

    @Test
    void calculatesCorrectNames() {
        Assertions.assertEquals(
            Set.of(
                "C",
                "B",
                "A"
            ),
            StreamSupport.stream(
                new CfsSimple(
                    "A:a1.a2.a3", // 4
                    "A:a1.a2",    // ignored
                    "B:b1",       // 2
                    "C:",         // 1
                    ":"           // 0
                ).names().spliterator(),
                false
            ).collect(Collectors.toSet())
        );
    }
}
