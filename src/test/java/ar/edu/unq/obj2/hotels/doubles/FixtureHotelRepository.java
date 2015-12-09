package ar.edu.unq.obj2.hotels.doubles;

import ar.edu.unq.obj2.hotels.*;
import ar.edu.unq.obj2.hotels.di.HotelRepositoryProvider;
import ar.edu.unq.obj2.hotels.payments.PaymentMethod;
import ar.edu.unq.obj2.hotels.repositories.Repository;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public interface FixtureHotelRepository extends HotelRepositoryProvider {
    default HotelsFixture newHotelRepository(){
        return new HotelsFixture();
    }

    public class HotelsFixture implements Repository<Hotel>{

        public final DayRange someDateRange          = new DayRange(LocalDate.of(2015, 12, 24), LocalDate.of(2015, 12, 26));
        public final DayRange overlappingDateRange   = new DayRange(LocalDate.of(2015, 12, 15), LocalDate.of(2015, 12, 28));
        public final PaymentMethod somePaymentMethod = new PaymentMethod();

        public final Hotel hotelMirador = new Hotel("Mirador", new Location("AR", "Carlos Paz", ""))
                .addBasicRoom(4, 80)
                .addBasicRoom(3, 50)
                .addBasicRoom(2, 30)
                ;

        public final Hotel hotelBarato = new Hotel("El Barato", new Location("AR", "Sourigues", ""))
                .addBasicRoom(8,20)
                .addBasicRoom(6,15)
                .addBasicRoom(7, 15)
                .addBasicRoom(10,15)
                ;

        public final Room habitacionBarata = new Room(2, hotelBarato, new BigDecimal(20));
        public final Room habitacionCara = new Room(2, hotelMirador, new BigDecimal(2000));

        public final Hotel hotelLejano = new Hotel("Lejano", new Location("AR", "Antartida", ""))
                .addBasicRoom(2, 200)
                .addBasicRoom(1, 150)
                ;

        public final Passenger passenger = new Passenger();

        private Collection<Hotel> hotels = new ArrayList<>();

        public final RoomReservation someReservation;

        public HotelsFixture() {
            this.hotels.add(this.hotelMirador);
            this.hotels.add(this.hotelBarato);
            this.hotels.add(this.hotelLejano);
            hotelBarato.getRooms().add(habitacionBarata);
            hotelMirador.getRooms().add(habitacionCara);
            someReservation = new RoomReservation(habitacionBarata, someDateRange, passenger, somePaymentMethod);
            someReservation.register();

        }

        @Override
        public Collection<Hotel> all() {
            return hotels;
        }
    }

}
