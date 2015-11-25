package ar.edu.unq.obj2.hotels.search;

import ar.edu.unq.obj2.hotels.Hotel;
import ar.edu.unq.obj2.hotels.Room;
import ar.edu.unq.obj2.hotels.repositories.HotelsRepository;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Search {
    private HotelsRepository hotels;

    public Search(HotelsRepository repository) {
        this.hotels = repository;
    }

    public static Search over(HotelsRepository repository){
        return new Search(repository);
    }

    public SearchResult by(Predicate<Room> searchFilter){
        return new SearchResult(hotels.all().stream().flatMap((hotel)->hotel.getRooms().stream()).filter(searchFilter));
    }
}
