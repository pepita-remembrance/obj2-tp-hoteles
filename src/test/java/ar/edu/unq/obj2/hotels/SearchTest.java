package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.notifications.senders.DummyEmailSenderProvider;
import ar.edu.unq.obj2.hotels.search.Search;
import ar.edu.unq.obj2.hotels.search.SearchResult;
import org.junit.Test;

import static ar.edu.unq.obj2.hotels.search.RoomFilterFactory.locationFilter;
import static ar.edu.unq.obj2.hotels.search.RoomFilterFactory.nameFilter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchTest extends BasicHotelsTest implements
        DummyEmailSenderProvider {

    @Test
    public void searchByName() {
        SearchResult searchResult = Search.over(hotelsFixture).by(nameFilter("Barato"));

        assertTrue(searchResult.groupedByHotel().containsKey(hotelsFixture.hotelBarato));
        assertEquals(4, searchResult.rooms().size());
    }

    @Test
    public void searchByNameOrLocation() {
        SearchResult searchResult =
                Search.over(hotelsFixture)
                        .by(nameFilter("Barato").or(locationFilter("Antartida")));

        assertTrue(searchResult.groupedByHotel().containsKey(hotelsFixture.hotelBarato));
        assertTrue(searchResult.groupedByHotel().containsKey(hotelsFixture.hotelLejano));
        assertEquals(2, searchResult.groupedByHotel().size());
    }
}
