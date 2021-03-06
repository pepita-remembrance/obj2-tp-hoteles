package ar.edu.unq.obj2.hotels.reservations;

import ar.edu.unq.obj2.hotels.*;
import ar.edu.unq.obj2.hotels.notifications.Email;
import ar.edu.unq.obj2.hotels.notifications.EmailSender;
import ar.edu.unq.obj2.hotels.notifications.Notifier;
import ar.edu.unq.obj2.hotels.payments.PaymentMethod;

import java.util.ArrayList;
import java.util.Collection;

public class RoomReservation implements WithLocation {
    private final DayRange range;
    private final Room room;
    private final Passenger owner;
    private final Collection<Passenger> otherOccupants = new ArrayList<>();
    private Collection<Notifier<RoomReservation>> observers = new ArrayList<>();
    private PaymentMethod paymentMethod;

    public RoomReservation(Room room, DayRange range, Passenger owner, PaymentMethod paymentMethod) {
        this.room = room;
        this.range = range;
        this.owner = owner;
        this.paymentMethod = paymentMethod;
    }

    public DayRange getRange() {
        return range;
    }

    public Room getRoom() {
        return room;
    }

    public Passenger getOwner() {
        return owner;
    }

    public Collection<Passenger> getOtherOccupants() {
        return otherOccupants;
    }

    public Hotel getHotel() {
        return room.getHotel();
    }

    public Location getLocation(){ return getHotel().getLocation();}

    public RoomReservation register(){
        room.addReservation(this);
        sendAllNotifications();
        return this;
    }

    public void addNotifier(Notifier<RoomReservation> notifier){
        observers.add(notifier);
    }

    protected void sendAllNotifications(){
        observers.forEach((notifier)->notifier.sendNotification(this));
    }
}
