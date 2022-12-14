package org.sct.jktor;

/**
 * Base implementation of {@link Classifier}.
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
     * Ctor.
     * @param src Classifier string in a form 'name:part1[.part2][...]'
     */
    public CfString(final String src) {
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

        final CfString cfString = (CfString) o;

        return source.equals(cfString.source);
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
        final String pattern = "^[A-Za-z&&[^.]]*:[A-Za-z&&[^.]]+(?:\\.[A-Za-z&&[^.]]+)*$|^[A-Za-z&&[^.]]*:[A-Za-z&&[^.]]*";
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
