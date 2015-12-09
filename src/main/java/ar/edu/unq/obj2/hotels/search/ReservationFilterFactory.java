package ar.edu.unq.obj2.hotels.search;

import ar.edu.unq.obj2.hotels.Passenger;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;

import java.time.LocalDate;
import java.util.function.Predicate;

public class ReservationFilterFactory {

    public static Predicate<RoomReservation> belongsTo(Passenger passenger) {
        return (roomReservation) -> roomReservation.getOwner().equals(passenger);
    }

    public static Predicate<RoomReservation> isAfter(LocalDate aDate) {
        return (roomReservation) -> roomReservation.getRange().getFirst().isAfter(aDate);
    }

    public static Predicate<RoomReservation> includesDay(LocalDate aDate) {
        return (roomReservation) -> roomReservation.getRange().includes(aDate);
    }

    public static Predicate<RoomReservation> isLocatedAt(String aCity) {
        return (roomReservation) -> roomReservation.getHotel().isLocatedAt(aCity);
    }

}
