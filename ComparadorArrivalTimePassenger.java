import java.util.*; 
/**
 * Write a description of class ComparadorArrivalTimePassenger here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ComparadorArrivalTimePassenger implements Comparator<Passenger>
{
    public int compare(Passenger p1, Passenger p2){  
        if (p1.getArrivalTime() == p2.getArrivalTime()){
            return (p1.getName().compareTo(p2.getName()) );
        }
        else{
            return Integer.compare(p1.getArrivalTime(), p2.getArrivalTime());
        }
    } 
}
