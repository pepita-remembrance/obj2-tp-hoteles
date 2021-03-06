package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.amenities.Amenity;
import ar.edu.unq.obj2.hotels.exceptions.DomainException;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Room {

    private int capacity;
    private Collection<Amenity> amenities = new ArrayList<>();;
    private BigDecimal defaultPrice;

    private final Map<LocalDate,BigDecimal> priceByDay = new HashMap<>();
    private final Collection<RoomReservation> reservations = new ArrayList<>();
    private final Hotel hotel;

    public Room(int capacity, Hotel hotel, BigDecimal defaultPrice) {
        this.capacity = capacity;
        this.hotel = hotel;
        this.defaultPrice = defaultPrice;
    }

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

    public void addReservation(RoomReservation reservation) {
        this.checkBelongsToThisRoom(reservation);
        this.checkRangeAvailable(reservation.getRange());
        this.reservations.add(reservation);
    }

    private void checkBelongsToThisRoom(RoomReservation reservation) {
        if (reservation.getRoom() != this) {
            throw new DomainException("The reservation doesn't belong to this room");
        }
    }

    private void checkRangeAvailable(DayRange range) {
        if (!this.isAvailableFor(range)) {
            throw new DomainException("Room not available for range " + range);
        }
    }

    public boolean isAvailableFor(DayRange range) {
        return this.reservations.stream().allMatch(it -> !it.getRange().overlapsWith(range));
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Boolean hasAmenity(Amenity amenity){
        return amenities.contains(amenity);
    }

    public Collection<RoomReservation> getReservations() {
        return reservations;
    }
}
