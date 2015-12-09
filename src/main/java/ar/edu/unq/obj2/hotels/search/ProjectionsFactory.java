package ar.edu.unq.obj2.hotels.search;

import ar.edu.unq.obj2.hotels.Hotel;
import ar.edu.unq.obj2.hotels.Room;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;
import ar.edu.unq.obj2.hotels.reservations.WithLocation;

import java.util.function.Function;
import java.util.stream.Stream;

public class ProjectionsFactory {

    public static Function<Hotel, Stream<Room>> rooms = (hotel) -> hotel.getRooms().stream();

    public static Function<Stream<Room>, Stream<RoomReservation>> reservationsByRooms =
            (roomStream) -> roomStream.flatMap(r -> r.getReservations().stream());

    public static Function<Hotel, Stream<RoomReservation>> reservations = reservationsByRooms.compose(rooms);

    public static Function<WithLocation, String> city = (reservation) -> reservation.getLocation().getCity();

}
