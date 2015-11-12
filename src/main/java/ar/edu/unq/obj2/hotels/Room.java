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
        return priceByDay.getOrDefault(date, defaultPrice);
    }

    public BigDecimal getPriceFor(DayRange range){
        return range.days().map(this::getPriceFor).reduce(new BigDecimal(0), BigDecimal::add);
    }

    public Room setPriceFor(LocalDate date, double price){
        priceByDay.put(date, new BigDecimal(price));
        return this;
    }

}
