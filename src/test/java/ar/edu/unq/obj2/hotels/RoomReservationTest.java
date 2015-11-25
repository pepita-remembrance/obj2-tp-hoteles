package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.exceptions.DomainException;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;
import ar.edu.unq.obj2.hotels.search.Search;
import ar.edu.unq.obj2.hotels.search.SearchResult;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static ar.edu.unq.obj2.hotels.search.RoomFilterFactory.nameFilter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class RoomReservationTest {


    HotelsFixtures hotelFixtures;

    @Before
    public void setUp(){
        hotelFixtures = new HotelsFixtures();
    }

    @Test(expected=DomainException.class)
    public void notificationShouldBeSentAfterSuccessfulRegistration() {
        Room room = hotelFixtures.hotelBarato.getRooms().stream().findFirst().get();
        new RoomReservation(room, new DayRange(LocalDate.of(2015,12,24), LocalDate.of(2015,12,26)),hotelFixtures.passenger).register();
        new RoomReservation(room, new DayRange(LocalDate.of(2015,12,15), LocalDate.of(2015,12,28)),hotelFixtures.passenger).register();
    }

}
