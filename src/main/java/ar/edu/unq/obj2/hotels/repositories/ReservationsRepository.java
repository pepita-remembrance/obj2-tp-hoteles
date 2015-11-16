package ar.edu.unq.obj2.hotels.repositories;

import ar.edu.unq.obj2.hotels.Passenger;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;

import java.util.stream.Stream;

public class ReservationsRepository extends BasicRepository<RoomReservation> {
    public Stream<RoomReservation> reservationsOf(Passenger owner) {
        return all().stream().filter(it -> it.getOwner() == owner);
    }
}
