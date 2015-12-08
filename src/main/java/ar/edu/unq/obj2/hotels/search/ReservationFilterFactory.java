package ar.edu.unq.obj2.hotels.search;

import ar.edu.unq.obj2.hotels.Passenger;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;

import java.util.function.Predicate;

public class ReservationFilterFactory {

    public static Predicate<RoomReservation> belongsTo(Passenger passenger) {
        return (roomReservation) -> roomReservation.getOwner().equals(passenger);
    }

}
