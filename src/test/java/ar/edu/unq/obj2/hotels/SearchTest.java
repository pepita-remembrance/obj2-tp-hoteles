package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.search.Search;
import ar.edu.unq.obj2.hotels.search.SearchResult;
import org.junit.Before;
import org.junit.Test;

import static ar.edu.unq.obj2.hotels.search.RoomFilterFactory.locationFilter;
import static ar.edu.unq.obj2.hotels.search.RoomFilterFactory.nameFilter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchTest {

    HotelsFixtures hotelFixtures;

    @Before
    public void setUp(){
        hotelFixtures = new HotelsFixtures();
    }

    @Test
    public void searchByName() {
        SearchResult searchResult = Search.over(hotelFixtures.repositoryHotelBasicFixture).by( nameFilter("Barato"));

        assertTrue(searchResult.groupedByHotel().containsKey(hotelFixtures.hotelBarato));
        assertEquals(4, searchResult.rooms().size());
    }

    @Test
    public void searchByNameOrLocation() {
        SearchResult searchResult =
                Search.over(hotelFixtures.repositoryHotelBasicFixture)
                        .by(nameFilter("Barato").or(locationFilter("Antartida")));

        assertTrue(searchResult.groupedByHotel().containsKey(hotelFixtures.hotelBarato));
        assertTrue(searchResult.groupedByHotel().containsKey(hotelFixtures.hotelLejano));
        assertEquals(2, searchResult.groupedByHotel().size());
    }
}
