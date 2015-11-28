package ar.edu.unq.obj2.hotels.di;


public abstract class BasicService implements
        EmailSenderProvider,
        HotelRepositoryProvider,
        ReservationsRepositoryProvider {

    public BasicService() {
        initializeDependencies();
    }

    protected abstract void initializeDependencies();
}