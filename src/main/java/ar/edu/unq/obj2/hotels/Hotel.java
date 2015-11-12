package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.amenities.Amenity;
import ar.edu.unq.obj2.hotels.payments.PaymentMethod;

import java.util.Calendar;
import java.util.Collection;

public class Hotel {
    private String name;
    private Location location;
    private Contact contactInformation;
    private Category category;
    private Collection<Amenity> amenities;
    private Calendar checkInHour;
    private Calendar checkOutHour;
    private Collection<PaymentMethod> paymentMethods;
}
