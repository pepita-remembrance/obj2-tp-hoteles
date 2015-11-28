package ar.edu.unq.obj2.hotels.doubles;

import ar.edu.unq.obj2.hotels.Hotel;
import ar.edu.unq.obj2.hotels.di.HotelRepositoryProvider;
import ar.edu.unq.obj2.hotels.repositories.Repository;

import java.util.ArrayList;

public interface FixtureImmutableHotelRepository extends HotelRepositoryProvider {
    @Override
    default Repository<Hotel> newHotelRepository(){
        return ArrayList::new;
    }
}
