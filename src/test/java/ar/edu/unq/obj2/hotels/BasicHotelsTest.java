package ar.edu.unq.obj2.hotels;

import ar.edu.unq.obj2.hotels.di.BasicService;
import ar.edu.unq.obj2.hotels.doubles.FixtureHotelRepository;
import ar.edu.unq.obj2.hotels.repositories.BasicReservationsRepositoryProvider;
import ar.edu.unq.obj2.hotels.repositories.Repository;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;
import org.junit.After;
import org.junit.Before;

public abstract class BasicHotelsTest  extends BasicService
        implements FixtureHotelRepository,
        BasicReservationsRepositoryProvider {

    protected HotelsFixture hotelsFixture;
    private Repository<RoomReservation> roomReservationRepository;

    @Before
    public void setUp() {
        initializeDependencies();
    }

    @Override
    protected void initializeDependencies() {
        hotelsFixture = newHotelRepository();
        roomReservationRepository = newReservationRepository();
    }
}