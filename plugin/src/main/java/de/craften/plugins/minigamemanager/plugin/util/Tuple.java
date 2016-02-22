package de.craften.plugins.minigamemanager.plugin.util;

/**
 * An immutable tuple.
 */
public class Tuple<T, U> {
    private final T first;
    private final U second;

    private Tuple(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        return first.hashCode() + 3 * second.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Tuple && ((Tuple) o).first.equals(first) && ((Tuple) o).second.equals(second);
    }

    public static Tuple<String, String> of(String gameId, String levelId) {
        return new Tuple<>(gameId, levelId);
    }
}
