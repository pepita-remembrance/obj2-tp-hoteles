package ar.edu.unq.obj2.hotels.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractRepository<T> {

    private Collection<T> elements = new ArrayList<T>();

    public Collection<T> all() {
        return elements;
    }

    public Collection<T> filter(Predicate<T> comparator) {
        return elements.stream().filter(comparator).collect(Collectors.<T>toList());
    }

    public AbstractRepository<T> add(T element) {
        elements.add(element);
        return this;
    }
}
