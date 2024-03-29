package org.sct.jktor;

/**
 * Simple {@link Classifier} constructed from a string.
 *
 * @since 1.0
 * @author Maxim Petrov
 */
public final class CfString implements Classifier {

    /**
     * Source classifier string.
     */
    private final String source;

    /**
     * Has the source been validated?
     */
    private boolean validated = false;

    /**
     * Ctor.
     * @param src Classifier string in a form 'name:part1[.part2][...]'
     */
    public CfString(final String src) {
        this.source = src;
    }

    @Override
    public String name() {
        final String src = this.valid();
        return src.substring(0, src.indexOf(":"));
    }

    @Override
    public int depth() {
        final String[] parts = this.valid().split("\\.");
        if (parts[parts.length - 1].endsWith(":")) {
            return 0;
        } else {
            return parts.length;
        }
    }

    @Override
    public Classifier parent() {
        final String valid = this.valid();
        final Classifier result;
        if (valid.equals(":")) {
            result = this;
        } else if (valid.split(":").length == 1) {
            result = new CfString(":");
        } else if (!valid.contains(".")) {
            result = new CfString(valid.substring(0, valid.indexOf(":") + 1));
        } else {
            result = new CfString(valid.substring(0, valid.lastIndexOf(".")));
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

        final CfString classifier = (CfString) o;

        return source.equals(classifier.source);
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
    private String valid() {
        if (this.validated) {
            return this.source;
        }
        final String pattern = "^[A-Za-z0-9 &&[^.]]*:[A-Za-z0-9 &&[^.]]+(?:\\.[A-Za-z0-9 &&[^.]]+)*$|^[A-Za-z0-9 &&[^.]]*:[A-Za-z0-9 &&[^.]]*";
        if (!this.source.matches(pattern)) {
            throw new IllegalArgumentException(
                String.format(
                    "Provided classifier string `%s` doesn't match format `%s`",
                    this.source,
                    pattern
                )
            );
        }
        this.validated = true;
        return this.source;
    }
}
