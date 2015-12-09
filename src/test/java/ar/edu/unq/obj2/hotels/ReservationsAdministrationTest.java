package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.notifications.EmailSender;
import ar.edu.unq.obj2.hotels.notifications.senders.DummyEmailSenderProvider;
import ar.edu.unq.obj2.hotels.payments.PaymentMethod;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;
import ar.edu.unq.obj2.hotels.search.Search;
import ar.edu.unq.obj2.hotels.search.SearchResult;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static ar.edu.unq.obj2.hotels.search.ReservationFilterFactory.*;
import static ar.edu.unq.obj2.hotels.search.ProjectionsFactory.*;

import java.time.LocalDate;

public class ReservationsAdministrationTest extends BasicHotelsTest implements DummyEmailSenderProvider {

    Room roomToReserve;
    EmailSender emailSender;
    private final LocalDate today = LocalDate.of(2015, 12, 25);
    private final DayRange  futureReservationDate = new DayRange(LocalDate.of(2016, 1, 1), LocalDate.of(2016, 1, 25));


    @Override
    protected void initializeDependencies() {
        super.initializeDependencies();
        emailSender = newEmailSender();
    }

    @Before
    public void setUp(){
        super.setUp();
        roomToReserve = hotelsFixture.hotelBarato.getRooms().stream().findFirst().get();
    }

    @Test
    public void aPassengerCanSearchForHisReservations() {
        SearchResult<RoomReservation> result =
                Search.over(hotelsFixture).select(reservations).where(belongsTo(hotelsFixture.passenger));

        assertEquals(hotelsFixture.someReservation, result.items().stream().findFirst().get());
    }

    @Test
    public void aPassangerCanSearchAllHisFutureReservationsGivenACurrentDate() {

        RoomReservation futureReservation = new RoomReservation(hotelsFixture.habitacionCara,futureReservationDate, hotelsFixture.passenger, hotelsFixture.somePaymentMethod);
        futureReservation.register();

        SearchResult<RoomReservation> result =
                Search.over(hotelsFixture).select(reservations).where(
                        belongsTo(hotelsFixture.passenger).and(isAfter(today))
                );

        assertEquals(futureReservation, result.items().stream().findFirst().get());
        assertEquals(1, result.items().size());

    }

    @Test
    public void aPassangerCanSearchAllHisCurrentReservations() {
        new RoomReservation(hotelsFixture.habitacionCara,futureReservationDate, hotelsFixture.passenger, hotelsFixture.somePaymentMethod).register();

        SearchResult<RoomReservation> result =
                Search.over(hotelsFixture).select(reservations).where(
                        belongsTo(hotelsFixture.passenger).and(includesDay(today))
                );

        assertEquals(hotelsFixture.someReservation, result.items().stream().findFirst().get());
        assertEquals(1, result.items().size());
    }


}
