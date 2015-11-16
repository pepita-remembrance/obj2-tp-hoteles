package ar.edu.unq.obj2.hotels.amenities;

import java.math.BigDecimal;

public class Amenity {
    private String name;
    private String description;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
