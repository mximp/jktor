package org.sct.jktor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classifiers within the file.
 */
public class FileClassifiers implements Classifiers {

    /**
     * Path to a file.
     */
    private final Classifiers source;

    public FileClassifiers(final Path source) {
        this.source = new Group(
            () -> {
                try {
                    return Files.readAllLines(source).stream()
                        .filter(s -> !s.isBlank())
                        .map(StringClassifier::new)
                        .collect(Collectors.toList());
                } catch (IOException e) {
                    throw new IllegalArgumentException(
                        String.format(
                            "Cannot read classifiers from file `%s`",
                            source
                        ),
                        e
                    );
                }
            }
        );
    }

    @Override
    public long size() {
        return this.source.size();
    }

    @Override
    public Set<String> names() {
        return this.source.names();
    }

    @Override
    public Stream<Classifier> all() {
        return this.source.all();
    }
}
