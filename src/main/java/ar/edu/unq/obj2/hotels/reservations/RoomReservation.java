package ar.edu.unq.obj2.hotels.reservations;

import ar.edu.unq.obj2.hotels.DayRange;
import ar.edu.unq.obj2.hotels.Hotel;
import ar.edu.unq.obj2.hotels.Passenger;
import ar.edu.unq.obj2.hotels.Room;
import ar.edu.unq.obj2.hotels.notifications.DummyNotifier;
import ar.edu.unq.obj2.hotels.notifications.ReservationsNotifier;

import java.util.ArrayList;
import java.util.Collection;

public class RoomReservation {
    private final DayRange range;
    private final Room room;
    private final Passenger owner;
    private final Collection<Passenger> otherOccupants = new ArrayList<>();
    private ReservationsNotifier notifier = new DummyNotifier();

    public RoomReservation(Room room, DayRange range, Passenger owner) {
        this.room = room;
        this.range = range;
        this.owner = owner;
    }

    public RoomReservation usingNotifier(ReservationsNotifier notifier){
        this.notifier = notifier;
        return this;
    }

    public DayRange getRange() {
        return range;
    }

    public Room getRoom() {
        return room;
    }

    public Passenger getOwner() {
        return owner;
    }

    public Collection<Passenger> getOtherOccupants() {
        return otherOccupants;
    }

    public Hotel getHotel() {
        return room.getHotel();
    }

    public void register(){
        room.addReservation(this);
        notifier.sendNotificationsFor(this);
    }

}
