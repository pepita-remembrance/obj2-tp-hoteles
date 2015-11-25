package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.exceptions.DomainException;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


public class RoomReservationTest {

    HotelsFixtures hotelFixtures;
    Room roomToReserve;

    @Before
    public void setUp(){
        hotelFixtures = new HotelsFixtures();
        roomToReserve = hotelFixtures.hotelBarato.getRooms().stream().findFirst().get();
    }

    @Test(expected=DomainException.class)
    public void exceptionShouldBeThrownWhenAttemptingToReserveAnAlreadyReservedRoom() {
        new RoomReservation(roomToReserve, new DayRange(LocalDate.of(2015,12,24), LocalDate.of(2015,12,26)),hotelFixtures.passenger).register();
        new RoomReservation(roomToReserve, new DayRange(LocalDate.of(2015,12,15), LocalDate.of(2015,12,28)),hotelFixtures.passenger).register();
    }

    @Test
    public void notificationShouldBeSentAfterSuccessfulRegistration() {
        ReservationsNotifierSpy notifier = new ReservationsNotifierSpy();
        RoomReservation reservation =
                new RoomReservation(roomToReserve, new DayRange(LocalDate.of(2015,12,24), LocalDate.of(2015,12,26)),hotelFixtures.passenger)
                .usingNotifier(notifier);

        reservation.register();

        assertEquals(reservation, notifier.getReservation());
    }


}
