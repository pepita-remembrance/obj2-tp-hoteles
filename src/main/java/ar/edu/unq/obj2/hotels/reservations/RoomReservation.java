package ar.edu.unq.obj2.hotels.reservations;

import ar.edu.unq.obj2.hotels.DayRange;
import ar.edu.unq.obj2.hotels.Room;
import ar.edu.unq.obj2.hotels.WithPrice;

import java.math.BigDecimal;

public class RoomReservation implements WithPrice {
    private final Room room;
    private final DayRange days;

    public RoomReservation(Room room, DayRange days) {
        this.room = room;
        this.days = days;
    }

    @Override
    public BigDecimal getPrice() {
        return null;
    }
}
