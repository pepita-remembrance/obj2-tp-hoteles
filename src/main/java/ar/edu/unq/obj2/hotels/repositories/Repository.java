package ar.edu.unq.obj2.hotels.repositories;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface Repository<T> {

    public Collection<T> all();

    public Stream<T> filter(Predicate<T> comparator);

    public BasicRepository<T> add(T element);
}
