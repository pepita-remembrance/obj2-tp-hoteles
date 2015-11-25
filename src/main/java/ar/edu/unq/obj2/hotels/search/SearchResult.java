package ar.edu.unq.obj2.hotels.search;

import ar.edu.unq.obj2.hotels.Hotel;
import ar.edu.unq.obj2.hotels.Room;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchResult {
    private Collection<Room> rooms;

    public SearchResult(Stream<Room> rooms) {
        this.rooms = rooms.collect(Collectors.toList());
    }

    public Map<Hotel,List<Room>> groupedByHotel(){
        return rooms.stream().collect(Collectors.groupingBy(Room::getHotel));
    }

    public Collection<Room> rooms(){
        return rooms.stream().collect(Collectors.toList());
    }
}
