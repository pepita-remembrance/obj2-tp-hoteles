package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.notifications.senders.DummyEmailSenderProvider;
import ar.edu.unq.obj2.hotels.search.Search;
import ar.edu.unq.obj2.hotels.search.SearchResult;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static ar.edu.unq.obj2.hotels.search.RoomFilterFactory.locationLike;
import static ar.edu.unq.obj2.hotels.search.RoomFilterFactory.nameLike;
import static org.junit.Assert.*;
import static ar.edu.unq.obj2.hotels.search.ProjectionsFactory.*;

public class SearchTest extends BasicHotelsTest implements
        DummyEmailSenderProvider {

    @Test
    public void searchByName() {
        Collection<Room> cheapRooms =
                Search.over(hotelsFixture)
                        .select(rooms)
                        .where(nameLike("Barato"))
                        .items();

        assertThat(cheapRooms, hasItems(hotelsFixture.hotelBarato.getRooms().toArray(new Room[5])));
    }

    @Test
    public void searchByNameOrLocation() {
        SearchResult<Room> searchResult =
                Search.over(hotelsFixture)
                        .select(rooms)
                        .where(nameLike("Barato").or(locationLike("Antartida")));

        assertTrue(searchResult.groupedBy(Room::getHotel).containsKey(hotelsFixture.hotelBarato));
        assertTrue(searchResult.groupedBy(Room::getHotel).containsKey(hotelsFixture.hotelLejano));
        assertEquals(2, searchResult.groupedBy(Room::getHotel).size());
    }
}
