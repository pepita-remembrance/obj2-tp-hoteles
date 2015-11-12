package ar.edu.unq.obj2.hotels.reservations;

import ar.edu.unq.obj2.hotels.DayRange;
import ar.edu.unq.obj2.hotels.Room;
import ar.edu.unq.obj2.hotels.WithPrice;

import java.math.BigDecimal;

public class RoomReservation implements WithPrice {
    private final Room room;
    private final DayRange range;

    public RoomReservation(Room room, DayRange range) {
        this.room = room;
        this.range = range;
    }

    @Override
    public BigDecimal getPrice() {
        return room.getPriceFor(range);
    }
}
