package ar.edu.unq.obj2.hotels.reservations;

import ar.edu.unq.obj2.hotels.DayRange;
import ar.edu.unq.obj2.hotels.Room;

import java.math.BigDecimal;

public class RoomReservation {
    private final Room room;
    private final DayRange range;

    public RoomReservation(Room room, DayRange range) {
        this.room = room;
        this.range = range;
    }

    public DayRange getRange() {
        return range;
    }

    public Room getRoom() {
        return room;
    }

    public BigDecimal getPrice() {
        return room.getPriceFor(range);
    }
}
