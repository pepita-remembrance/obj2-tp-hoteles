package ar.edu.unq.obj2.hotels.search;

import ar.edu.unq.obj2.hotels.Hotel;
import ar.edu.unq.obj2.hotels.Passenger;
import ar.edu.unq.obj2.hotels.Room;
import ar.edu.unq.obj2.hotels.repositories.Repository;
import ar.edu.unq.obj2.hotels.reservations.RoomReservation;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Search<T> {
    private Repository<T> repository;

    public Search(Repository<T> hotels) {
        this.repository = hotels;
    }

    public <R> ProjectionalSearch<R> select(Function<T, Stream<R>> extractor) {
        return new ProjectionalSearch<>(extractor);
    }

    public Stream<T> getAll() {
        return repository.all().stream();
    }

    public static <T> Search<T> over(Repository<T> repository) {
        return new Search<>(repository);
    }

    public class ProjectionalSearch<R> {
        private final Function<T, Stream<R>> extractor;

        public ProjectionalSearch(Function<T, Stream<R>> extractor) {
            this.extractor = extractor;
        }

        public SearchResult<R> where(Predicate<R> condition) {
            return new SearchResult<>(getAll().flatMap(extractor).filter(condition));
        }
    }

}
