package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.amenities.Amenity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private int capacity;
    private Collection<Amenity> amenities;
    private BigDecimal defaultPrice;
    private final Map<LocalDate,BigDecimal> priceByDay = new HashMap<>();

    public BigDecimal getPriceFor(LocalDate date){
        return getPriceByDay().getOrDefault(date, getDefaultPrice());
    }

    public BigDecimal getPriceFor(DayRange range){
        return range.days().map(this::getPriceFor).reduce(new BigDecimal(0), BigDecimal::add);
    }

    public Room setPriceFor(LocalDate date, double price){
        getPriceByDay().put(date, new BigDecimal(price));
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public Collection<Amenity> getAmenities() {
        return amenities;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public Map<LocalDate, BigDecimal> getPriceByDay() {
        return priceByDay;
    }
}
