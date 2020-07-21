package pl.bzagozda.wikifootball;

import java.util.Optional;

public interface Searcher<T> {
    Optional<T> find(String text);
}
