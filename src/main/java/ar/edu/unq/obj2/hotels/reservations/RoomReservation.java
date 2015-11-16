package ar.edu.unq.obj2.hotels.reservations;

import ar.edu.unq.obj2.hotels.DayRange;
import ar.edu.unq.obj2.hotels.Passenger;
import ar.edu.unq.obj2.hotels.Room;

import java.util.Collection;

public class RoomReservation {
    private final DayRange range;
    private final Room room;
    private final Passenger owner;
    private final Collection<Passenger> otherOccupants;

    public RoomReservation(Room room, DayRange range, Passenger owner, Collection<Passenger> otherOccupants) {
        this.room = room;
        this.range = range;
        this.owner = owner;
        this.otherOccupants = otherOccupants;
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
}
