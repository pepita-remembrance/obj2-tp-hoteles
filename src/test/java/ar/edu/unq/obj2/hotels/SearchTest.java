package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.notifications.senders.DummyEmailSenderProvider;
import ar.edu.unq.obj2.hotels.search.Search;
import ar.edu.unq.obj2.hotels.search.SearchResult;
import org.junit.Test;

import static ar.edu.unq.obj2.hotels.search.RoomFilterFactory.locationLike;
import static ar.edu.unq.obj2.hotels.search.RoomFilterFactory.nameLike;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ar.edu.unq.obj2.hotels.search.ProjectionsFactory.*;

public class SearchTest extends BasicHotelsTest implements
        DummyEmailSenderProvider {

    @Test
    public void searchByName() {
        SearchResult<Room> searchResult =
                Search.over(hotelsFixture)
                        .select(rooms)
                        .where(nameLike("Barato"));

        assertTrue(searchResult.groupedBy(Room::getHotel).containsKey(hotelsFixture.hotelBarato));
        assertEquals(4, searchResult.items().size());
    }

    @Test
    public void searchByNameOrLocation() {
        SearchResult<Room> searchResult =
                Search.over(hotelsFixture)
                        .select(rooms)
                        .where(nameLike("Barato").or(locationLike("Antartida")) );

        assertTrue(searchResult.groupedBy(Room::getHotel).containsKey(hotelsFixture.hotelBarato));
        assertTrue(searchResult.groupedBy(Room::getHotel).containsKey(hotelsFixture.hotelLejano));
        assertEquals(2, searchResult.groupedBy(Room::getHotel).size());
    }
}
