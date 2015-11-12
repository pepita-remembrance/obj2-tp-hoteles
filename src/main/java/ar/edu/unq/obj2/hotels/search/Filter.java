package ar.edu.unq.obj2.hotels.search;

public interface Filter<T> {
    public abstract boolean applyFilter(T model);
}
