package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.exceptions.DomainException;
import ar.edu.unq.obj2.hotels.notifications.senders.SpyEmailSenderProvider;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


public class RoomReservationTest extends BasicHotelsTest implements SpyEmailSenderProvider {

    Room roomToReserve;
    final DayRange someDateRange        = new DayRange(LocalDate.of(2015, 12, 24), LocalDate.of(2015, 12, 26));
    final DayRange overlappingDateRange = new DayRange(LocalDate.of(2015, 12, 15), LocalDate.of(2015, 12, 28));
    SpyEmailSender emailSender;

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

    @Test(expected=DomainException.class)
    public void exceptionShouldBeThrownWhenAttemptingToReserveAnAlreadyReservedRoom() {
        new RoomReservation(roomToReserve, someDateRange,        hotelsFixture.passenger).register();
        new RoomReservation(roomToReserve, overlappingDateRange, hotelsFixture.passenger).register();
    }

    @Test
    public void notificationShouldBeSentAfterSuccessfulRegistration() {
        String emailAddress = "some@email.com";
        hotelsFixture.passenger.setContact(new Contact(emailAddress));
        RoomReservation reservation =
                new RoomReservation(roomToReserve, someDateRange, hotelsFixture.passenger)
                .usingNotifier(emailSender);

        reservation.register();

        assertEquals("Room reservation completed", emailSender.email.subject);
        assertEquals(emailAddress, emailSender.email.to);
    }


}
