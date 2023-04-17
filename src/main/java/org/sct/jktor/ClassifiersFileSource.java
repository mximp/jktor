package org.sct.jktor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ClassifiersFileSource implements Supplier<Iterable<Classifier>> {

    private final Path source;

    public ClassifiersFileSource(final Path source) {
        this.source = source;
    }

    @Override
    public Iterable<Classifier> get() {
        try {
            return Files.readAllLines(this.source).stream()
                .filter(s -> !s.isBlank())
                .map(StringClassifier::new)
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException(
                String.format(
                    "Cannot read classifiers from file `%s`",
                    this.source
                ),
                e
            );
        }
    }
}
