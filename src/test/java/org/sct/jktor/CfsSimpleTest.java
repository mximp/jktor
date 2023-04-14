package org.sct.jktor;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
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
                new CfSimple(classifier)
            ).size()
        );
    }
}
