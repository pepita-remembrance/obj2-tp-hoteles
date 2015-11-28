package ar.edu.unq.obj2.hotels.doubles;

import ar.edu.unq.obj2.hotels.Hotel;
import ar.edu.unq.obj2.hotels.Location;
import ar.edu.unq.obj2.hotels.Passenger;
import ar.edu.unq.obj2.hotels.di.HotelRepositoryProvider;
import ar.edu.unq.obj2.hotels.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;

public interface FixtureHotelRepository extends HotelRepositoryProvider {
    default HotelsFixture newHotelRepository(){
        return new HotelsFixture();
    }

    public class HotelsFixture implements Repository<Hotel>{

        public final Hotel hotelMirador = new Hotel("Mirador", new Location("AR", "", ""))
                .addBasicRoom(4, 80)
                .addBasicRoom(3, 50)
                .addBasicRoom(2, 30)
                ;

        public final Hotel hotelBarato = new Hotel("El Barato", new Location("AR", "", ""))
                .addBasicRoom(8,20)
                .addBasicRoom(6,15)
                .addBasicRoom(7, 15)
                .addBasicRoom(10,15)
                ;

        public final Hotel hotelLejano = new Hotel("Lejano", new Location("AR", "Antartida", ""))
                .addBasicRoom(2, 200)
                .addBasicRoom(1, 150)
                ;

        public final Passenger passenger = new Passenger();

        private Collection<Hotel> hotels = new ArrayList<>();

        public HotelsFixture() {
            this.hotels.add(this.hotelMirador);
            this.hotels.add(this.hotelBarato);
            this.hotels.add(this.hotelLejano);
        }

        @Override
        public Collection<Hotel> all() {
            return hotels;
        }
    }

}
