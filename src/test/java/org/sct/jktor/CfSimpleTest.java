package org.sct.jktor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CfSimpleTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "class:ABC.def",
        "class:abc",
        "class:abc.def.ghi",
        ":a.b.c",
        "class:",
        ":"
    })
    void validSource(final String source) {
        Classifier cfr = new CfSimple(source);
        Assertions.assertDoesNotThrow(cfr::name);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "class:.",
        ".",
        "",
        " asdf",
        "class:sdflsk%ˆ",
        "sab.ssd:sdfs.dfs"
    })
    void invalidSource(final String source) {
        Classifier cfr = new CfSimple(source);
        Assertions.assertThrows(IllegalArgumentException.class, cfr::name);
    }

    @ParameterizedTest
    @CsvSource({
        "class:a.b.c, 3",
        "class:, 0",
        "class:abc, 1",
        ":, 0"
    })
    void depthTest(final String source, final String expected) {
        Assertions.assertEquals(
            Integer.valueOf(expected),
            new CfSimple(source).depth()
        );
    }

    @ParameterizedTest
    @CsvSource({
        ":, :",
        "class:, :",
        "class:a.b.c, class:a.b",
        "class:a, class:",
        ":a.b.c, :a.b"
    })
    void parentTest(final String source, final String expected) {
        Assertions.assertEquals(
            new CfSimple(expected),
            new CfSimple(source).parent()
        );
    }
}
