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
        return this.validated().split("\\.").length;
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

    /**
     * Make sure source string is in correct format.
     * @return Source string
     * @throws IllegalArgumentException in case validation is failed.
     */
    @SuppressWarnings("checkstyle:LineLength")
    private String validated() {
        if (!this.source.matches(
            "^[A-Za-z&&[^.]]*:[A-Za-z&&[^.]]+(?:\\.[A-Za-z&&[^.]]+)*$|^[A-Za-z&&[^.]]*:[A-Za-z&&[^.]]*"
        )) {
            throw new IllegalArgumentException(
                String.format("Not a valid classifier string: %s", this.source)
            );
        }
        return this.source;
    }
}
