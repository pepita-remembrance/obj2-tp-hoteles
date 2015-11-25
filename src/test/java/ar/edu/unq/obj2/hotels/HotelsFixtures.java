package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.repositories.BasicRepository;
import ar.edu.unq.obj2.hotels.repositories.Repository;

public class HotelsFixtures {

    Hotel hotelMirador = new Hotel("Mirador", new Location("AR", "", ""))
            .addBasicRoom(4, 80)
            .addBasicRoom(3, 50)
            .addBasicRoom(2, 30)
            ;

    Hotel hotelBarato = new Hotel("El Barato", new Location("AR", "", ""))
            .addBasicRoom(8,20)
            .addBasicRoom(6,15)
            .addBasicRoom(7,15)
            .addBasicRoom(10,15)
            ;

    Hotel hotelLejano = new Hotel("Lejano", new Location("AR", "Antartida", ""))
            .addBasicRoom(2,200)
            .addBasicRoom(1,150)
            ;

    Repository<Hotel> repositoryHotelBasicFixture  =
            new BasicRepository<Hotel>().add(hotelMirador).add(hotelBarato).add(hotelLejano);

    Passenger passenger = new Passenger();

}
