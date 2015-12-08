package ar.edu.unq.obj2.hotels.search;

import ar.edu.unq.obj2.hotels.Hotel;
import ar.edu.unq.obj2.hotels.Room;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchResult<T> {
    private Collection<T> items;

    public SearchResult(Stream<T> rooms) {
        this.items = rooms.collect(Collectors.toList());
    }

    public<R> Map<R,List<T>> groupedBy(Function<T,R> relation){
        return items.stream().collect(Collectors.groupingBy(relation));
    }

    public Collection<T> items(){
        return items.stream().collect(Collectors.toList());
    }
}
