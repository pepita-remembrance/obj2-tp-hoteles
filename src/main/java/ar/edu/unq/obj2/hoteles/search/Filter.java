package ar.edu.unq.obj2.hoteles.search;

public interface Filter<T> {
    public abstract boolean applyFilter(T model);
}
