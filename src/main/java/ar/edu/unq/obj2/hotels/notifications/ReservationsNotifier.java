package ar.edu.unq.obj2.hotels.notifications;

import ar.edu.unq.obj2.hotels.reservations.RoomReservation;

public interface ReservationsNotifier {
    public void sendNotificationsFor(RoomReservation reservation);
}
