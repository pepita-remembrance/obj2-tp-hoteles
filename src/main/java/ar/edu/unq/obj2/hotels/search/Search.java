package ar.edu.unq.obj2.hotels.search;

import ar.edu.unq.obj2.hotels.Hotel;
import ar.edu.unq.obj2.hotels.Room;
import ar.edu.unq.obj2.hotels.repositories.Repository;

import java.util.function.Predicate;

public class Search {
    private Repository<Hotel> hotels;

    public Search(Repository<Hotel> hotels) {
        this.hotels = hotels;
    }

    public static Search over(Repository<Hotel> repository){
        return new Search(repository);
    }

    public SearchResult by(Predicate<Room> searchFilter){
        return new SearchResult(hotels.all().stream().flatMap((hotel)->hotel.getRooms().stream()).filter(searchFilter));
    }
}
