package ar.edu.unq.obj2.hoteles;

import ar.edu.unq.obj2.hoteles.amenities.Amenity;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private int capacity;
    private Collection<Amenity> amenities;
    private BigDecimal defaultPrice;
    private final Map<Date,BigDecimal> priceByDay = new HashMap<Date, BigDecimal>();

    public BigDecimal getPriceFor(Date date){
        return priceByDay.getOrDefault(date, defaultPrice);
    }

    public Room setPriceFor(Date date, double price){
        priceByDay.put(date, new BigDecimal(price));
        return this;
    }


}
