package ar.edu.unq.obj2.hotels.repositories;

import ar.edu.unq.obj2.hotels.di.ReservationsRepositoryProvider;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;

public interface BasicReservationsRepositoryProvider extends ReservationsRepositoryProvider {
    default Repository<RoomReservation> newReservationRepository() {
        return new BasicRepository<RoomReservation>() {};
    }
}
