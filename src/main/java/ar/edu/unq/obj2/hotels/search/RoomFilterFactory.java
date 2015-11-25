package ar.edu.unq.obj2.hotels.search;

import ar.edu.unq.obj2.hotels.Room;
import ar.edu.unq.obj2.hotels.amenities.Amenity;

import java.util.function.Predicate;

public class RoomFilterFactory {

    public static Predicate<Room> identity(){
        return (room)-> true;
    }

    public static Predicate<Room> byName(final String hotelName){
        return (room)-> room.getHotel().getName().contains(hotelName);
    }

    public static Predicate<Room> byLocation(final String placeName){
        return (room)-> room.getHotel().isLocatedAt(placeName);
    }

    public static Predicate<Room> hasAmenity(final Amenity anAmenity){
        return (room)-> room.hasAmenity(anAmenity);
    }
}
