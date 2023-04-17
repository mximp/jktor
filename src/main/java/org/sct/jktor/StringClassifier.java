package org.sct.jktor;

/**
 * Simple {@link Classifier} constructed from a string.
 *
 * @since 1.0
 * @author Maxim Petrov
 */
public final class StringClassifier implements Classifier {

    /**
     * Source classifier string.
     */
    private final String source;

    /**
     * Ctor.
     * @param src Classifier string in a form 'name:part1[.part2][...]'
     */
    public StringClassifier(final String src) {
        this.source = src;
    }

    @Override
    public String name() {
        final String src = this.validated();
        return src.substring(0, src.indexOf(":"));
    }

    @Override
    public int depth() {
        final String[] parts = this.validated().split("\\.");
        if (parts[parts.length - 1].endsWith(":")) {
            return 0;
        } else {
            return parts.length;
        }
    }

    @Override
    public Classifier parent() {
        final String valid = this.validated();
        final Classifier result;
        if (valid.equals(":")) {
            result = this;
        } else if (valid.split(":").length == 1) {
            result = new StringClassifier(":");
        } else if (!valid.contains(".")) {
            result = new StringClassifier(valid.substring(0, valid.indexOf(":") + 1));
        } else {
            result = new StringClassifier(valid.substring(0, valid.lastIndexOf(".")));
        }
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final StringClassifier stringClassifier = (StringClassifier) o;

        return source.equals(stringClassifier.source);
    }

    @Override
    public int hashCode() {
        return source.hashCode();
    }

    @Override
    public String toString() {
        return this.source;
    }

    /**
     * Make sure source string is in correct format.
     * @return Source string
     * @throws IllegalArgumentException in case validation is failed.
     */
    @SuppressWarnings("checkstyle:LineLength")
    private String validated() {
        final String pattern = "^[A-Za-z0-9&&[^.]]*:[A-Za-z0-9&&[^.]]+(?:\\.[A-Za-z0-9&&[^.]]+)*$|^[A-Za-z0-9&&[^.]]*:[A-Za-z0-9&&[^.]]*";
        if (!this.source.matches(pattern)) {
            throw new IllegalArgumentException(
                String.format(
                    "Provided classifier string `%s` doesn't match format `%s`",
                    this.source,
                    pattern
                )
            );
        }
        return this.source;
    }
}
