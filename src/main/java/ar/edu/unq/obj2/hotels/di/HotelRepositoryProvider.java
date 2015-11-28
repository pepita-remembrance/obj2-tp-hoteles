package ar.edu.unq.obj2.hotels.di;

import ar.edu.unq.obj2.hotels.Hotel;
import ar.edu.unq.obj2.hotels.repositories.Repository;

public interface HotelRepositoryProvider {
    public Repository<Hotel> newHotelRepository();
}
