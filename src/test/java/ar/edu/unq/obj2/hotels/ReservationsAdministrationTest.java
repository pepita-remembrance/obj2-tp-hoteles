package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.notifications.EmailSender;
import ar.edu.unq.obj2.hotels.notifications.senders.DummyEmailSenderProvider;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;
import ar.edu.unq.obj2.hotels.search.Search;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static ar.edu.unq.obj2.hotels.search.ProjectionsFactory.city;
import static ar.edu.unq.obj2.hotels.search.ProjectionsFactory.reservations;
import static ar.edu.unq.obj2.hotels.search.ReservationFilterFactory.*;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

public class ReservationsAdministrationTest extends BasicHotelsTest implements DummyEmailSenderProvider {

    Room roomToReserve;
    EmailSender emailSender;
    RoomReservation futureReservation;
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
        futureReservation = new RoomReservation(hotelsFixture.habitacionCara,futureReservationDate, hotelsFixture.passenger, hotelsFixture.somePaymentMethod).register();
    }

    @Test
    public void aPassengerCanSearchForHisReservations() {

        assertThat(

            Search.over(hotelsFixture)
                .select(reservations)
                .where(belongsTo(hotelsFixture.passenger))
                .items()

        , hasItems(hotelsFixture.someReservation, futureReservation));
    }

    @Test
    public void aPassengerCanSearchAllHisFutureReservationsGivenACurrentDate() {

        assertThat(

            Search.over(hotelsFixture)
                .select(reservations)
                .where(belongsTo(hotelsFixture.passenger).and(isAfter(today)))
                .items()

        , hasItems(futureReservation));
    }

    @Test
    public void aPassengerCanSearchAllHisCurrentReservations() {

        assertThat(

            Search
                .over(hotelsFixture)
                .select(reservations)
                .where(belongsTo(hotelsFixture.passenger).and(includesDay(today)))
                .items()

        , hasItems(hotelsFixture.someReservation));
    }

    @Test
    public void aPassengerCanSearchAllHisReservationsForAGivenCity() {

        assertThat(

            Search.over(hotelsFixture)
                .select(reservations)
                .where(belongsTo(hotelsFixture.passenger).and(isLocatedAt("Carlos Paz")))
                .items()

        , hasItems(futureReservation));
    }

    @Test
    public void aPassengerCanSearchForTheCitiesWhereHeHasAReservation() {

        assertThat(

            Search.over(hotelsFixture)
                .select(reservations)
                .where(belongsTo(hotelsFixture.passenger))
                .groupedBy(city)
                .keySet()

        ,hasItems("Carlos Paz", "Sourigues"));
    }



}
