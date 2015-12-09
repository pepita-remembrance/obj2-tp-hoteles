package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.amenities.Amenity;
import ar.edu.unq.obj2.hotels.payments.PaymentMethod;
import ar.edu.unq.obj2.hotels.reservations.WithLocation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Hotel implements WithLocation {
    private String name;
    private Location location;
    private Contact contactInformation;
    private Category category;
    private Collection<Amenity> amenities;
    private Calendar checkInHour;
    private Calendar checkOutHour;
    private Collection<PaymentMethod> paymentMethods;
    private Collection<Room> rooms = new ArrayList<>();

    public Hotel(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public Contact getContactInformation() {
        return contactInformation;
    }

    public Category getCategory() {
        return category;
    }

    public Collection<Amenity> getAmenities() {
        return amenities;
    }

    public Calendar getCheckInHour() {
        return checkInHour;
    }

    public Calendar getCheckOutHour() {
        return checkOutHour;
    }

    public Collection<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public Collection<Room> getRooms() {
        return rooms;
    }


    public boolean isLocatedAt(String place) {
        return location.isAt(place);
    }

    public Hotel addBasicRoom(int capacity, double defaultPrice){
        this.rooms.add(new Room(capacity, this, new BigDecimal(defaultPrice)));
        return this;
    }
}
