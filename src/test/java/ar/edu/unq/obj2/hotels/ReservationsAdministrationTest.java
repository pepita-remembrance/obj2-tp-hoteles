package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.exceptions.DomainException;
import ar.edu.unq.obj2.hotels.notifications.EmailSender;
import ar.edu.unq.obj2.hotels.notifications.senders.DummyEmailSenderProvider;
import ar.edu.unq.obj2.hotels.payments.PaymentMethod;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class ReservationsAdministrationTest extends BasicHotelsTest implements DummyEmailSenderProvider {

    Room roomToReserve;
    final DayRange someDateRange          = new DayRange(LocalDate.of(2015, 12, 24), LocalDate.of(2015, 12, 26));
    final DayRange overlappingDateRange   = new DayRange(LocalDate.of(2015, 12, 15), LocalDate.of(2015, 12, 28));
    final PaymentMethod somePaymentMethod = new PaymentMethod();
    EmailSender emailSender;

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
        new RoomReservation(roomToReserve, someDateRange,        hotelsFixture.passenger, somePaymentMethod).register();
        new RoomReservation(roomToReserve, overlappingDateRange, hotelsFixture.passenger, somePaymentMethod).register();
    }



}
