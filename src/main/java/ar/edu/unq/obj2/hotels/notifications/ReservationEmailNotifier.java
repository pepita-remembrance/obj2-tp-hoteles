package ar.edu.unq.obj2.hotels.notifications;

import ar.edu.unq.obj2.hotels.reservations.RoomReservation;

public class ReservationEmailNotifier implements Notifier<RoomReservation>{

    private EmailSender sender;

    public ReservationEmailNotifier(EmailSender sender) {
        this.sender = sender;
    }

    @Override
    public void sendNotification(RoomReservation roomReservation) {
        sender.send(new Email("Enterprise", roomReservation.getOwner().getContact().getEmail(), "Room reservation completed", ""));
    }
}
