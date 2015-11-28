package ar.edu.unq.obj2.hotels.di;

import ar.edu.unq.obj2.hotels.repositories.Repository;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;

public interface ReservationsRepositoryProvider {
    public Repository<RoomReservation> newReservationRepository();
}

