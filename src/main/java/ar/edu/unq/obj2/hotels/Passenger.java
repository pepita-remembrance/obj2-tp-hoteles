package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.repositories.ReservationsRepository;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;

import java.time.LocalDate;
import java.util.stream.Stream;

public class Passenger {

    Contact contact;

    public Reservations reservations(ReservationsRepository repository) {
        return new Reservations(repository.reservationsOf(this));
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}

class Reservations {
    private final Stream<RoomReservation> all;

    Reservations(Stream<RoomReservation> all) {
        this.all = all;
    }

    public Stream<RoomReservation> all() {
        return all;
    }

    public Stream<RoomReservation> after(LocalDate date) {
        return this.all().filter(it -> it.getRange().beginsAfter(date));
    }

    public Stream<RoomReservation> ofCity(String city) {
        return this.all().filter(it -> it.getHotel().isLocatedAt(city));
    }

}
