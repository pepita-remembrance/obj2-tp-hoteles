package ar.edu.unq.obj2.hotels.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BasicRepository<T> {

    private Collection<T> elements = new ArrayList<T>();

    public Collection<T> all() {
        return elements;
    }

    public Stream<T> filter(Predicate<T> comparator) {
        return elements.stream().filter(comparator);
    }

    public BasicRepository<T> add(T element) {
        elements.add(element);
        return this;
    }
}
