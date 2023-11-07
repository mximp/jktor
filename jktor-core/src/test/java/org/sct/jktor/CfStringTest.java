package org.sct.jktor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CfStringTest {

    public static void main(String[] args) {
        CfString classifier = new CfString("name:a.b.c.d.e");
        long start = System.currentTimeMillis();
        for(int i = 0; i < 1_000_000; i++) {
            classifier.name();
            classifier.parent();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "class:ABC.def",
        "class:abc",
        "class:abc.def.ghi",
        ":a.b.c",
        "class:",
        "PPP:two words",
        ":"
    })
    void validSource(final String source) {
        Classifier cfr = new CfString(source);
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
        Classifier cfr = new CfString(source);
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
            new CfString(source).depth()
        );
    }

    @ParameterizedTest
    @CsvSource({
        "A:a.b.c, A",
        ":d.e.f, ",
        ":,  ",
    })
    void hasCorrectName(final String source, final String expected) {
        Assertions.assertEquals(
            expected == null ? "" : expected,
            new CfString(source).name()
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
            new CfString(expected),
            new CfString(source).parent()
        );
    }
}
