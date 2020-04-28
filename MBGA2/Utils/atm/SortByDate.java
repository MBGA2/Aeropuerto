package Utils.atm;

import java.util.Comparator;

import Datos.Flight;

public class SortByDate implements Comparator<Flight>{

    @Override
    public int compare(Flight a, Flight b) {
        return (int) (a.getArrival_time().getTime()-b.getArrival_time().getTime());
    }

}