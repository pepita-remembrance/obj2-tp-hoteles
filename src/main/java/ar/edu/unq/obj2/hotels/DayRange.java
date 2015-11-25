package ar.edu.unq.obj2.hotels;

import java.time.LocalDate;
import java.time.Period;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A range of days, where the first and last day of the interval are both included.
 * [first,last]
 */
public class DayRange {
    private final LocalDate first;
    private final LocalDate last;

    public DayRange(LocalDate first, LocalDate last) {
        this.first = first;
        this.last = last;
    }

    public boolean includes(LocalDate aDay){
        return (getFirst().isBefore(aDay) && getLast().isAfter(aDay)) || getFirst().isEqual(aDay) || getLast().isEqual(aDay);
    }

    public Stream<LocalDate> days(){
        return IntStream
                .range(0, Period.between(getFirst(), getLast()).getDays())
                .mapToObj(nthDay -> LocalDate.from(getFirst()).plusDays(nthDay));
    }

    public LocalDate getFirst() {
        return first;
    }

    public LocalDate getLast() {
        return last;
    }

    public boolean overlapsWith(DayRange anotherRange) {
        return (first.isBefore(anotherRange.first) && last.isAfter(anotherRange.first))
                || (first.isBefore(anotherRange.last) && last.isAfter(anotherRange.last))
                || (first.isAfter(anotherRange.first) && last.isBefore(anotherRange.last));
    }

    public boolean beginsAfter(LocalDate date) {
        return getFirst().isAfter(date);
    }
}
