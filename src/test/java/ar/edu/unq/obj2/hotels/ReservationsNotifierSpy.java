package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.notifications.ReservationsNotifier;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;

public class ReservationsNotifierSpy implements ReservationsNotifier {

    RoomReservation reservation;

    @Override
    public void sendNotificationsFor(RoomReservation reservation) {
        this.reservation = reservation;
    }

    public RoomReservation getReservation() {
        return reservation;
    }
}
