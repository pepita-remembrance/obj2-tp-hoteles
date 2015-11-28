package ar.edu.unq.obj2.hotels.repositories;

import ar.edu.unq.obj2.hotels.Hotel;
import ar.edu.unq.obj2.hotels.Room;
import ar.edu.unq.obj2.hotels.di.HotelRepositoryProvider;

interface BasicHotelRepositoryProvider extends HotelRepositoryProvider {
    default Repository<Hotel> newHotelRepository(){
        return new BasicRepository<Hotel>(){};
    }
}
