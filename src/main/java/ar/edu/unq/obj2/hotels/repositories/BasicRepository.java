package ar.edu.unq.obj2.hotels.repositories;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BasicRepository<T> implements Repository<T> {
    private Collection<T> elements = new ArrayList<>();
    @Override public Collection<T> all() { return elements; }
}
