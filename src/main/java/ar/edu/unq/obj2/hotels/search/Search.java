package ar.edu.unq.obj2.hotels.search;

import ar.edu.unq.obj2.hotels.Hotel;
import ar.edu.unq.obj2.hotels.Room;
import ar.edu.unq.obj2.hotels.repositories.HotelsRepository;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Search {
    private String hotelOrLocation;
    private Collection<Predicate<Room>> roomFilters;

    public Stream<Room> perform(HotelsRepository repository) {
        return repository.all().stream()
            .filter(this::hotelSatisfy)
            .flatMap(hotel -> hotel.roomsThatSatisfy(getRoomFilters()));
    }

    private boolean hotelSatisfy(Hotel hotel) {
        return hotelNameMatchesQuery(hotel) || hotelLocationMatchesQuery(hotel);
    }

    private boolean hotelLocationMatchesQuery(Hotel hotel) {
        return hotel.isLocatedAt(getHotelOrLocation());
    }

    private boolean hotelNameMatchesQuery(Hotel hotel) {
        return hotel.getName().equals(getHotelOrLocation());
    }

    public String getHotelOrLocation() {
        return hotelOrLocation;
    }

    public Collection<Predicate<Room>> getRoomFilters() {
        return roomFilters;
    }
}
