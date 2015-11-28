package ar.edu.unq.obj2.hotels.repositories;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface Repository<T> {

    public Collection<T> all();

    default public Stream<T> filter(Predicate<T> comparator) {
        return all().stream().filter(comparator);
    }

    default public Repository<T> save(T element) {
        all().add(element);
        return this;
    }

}
