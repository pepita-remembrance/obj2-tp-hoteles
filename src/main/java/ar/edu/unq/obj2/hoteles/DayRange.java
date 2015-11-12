package ar.edu.unq.obj2.hoteles;

import java.util.Date;

/**
 * A range of days, where the first and last day of the interval are both included.
 * [first,last]
 */
public class DayRange {
    private Date first;
    private Date last;

    public DayRange(Date first, Date last) {
        this.first = first;
        this.last = last;
    }

    public boolean includes(Date aDay){
        return aDay.getTime() >= first.getTime() && aDay.getTime() <= last.getTime();
    }
}
