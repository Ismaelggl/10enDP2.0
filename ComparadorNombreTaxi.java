import java.util.*;

/**
 * Compare two different taxis by name in ascending order.
 *
 * @author (Máximo Bueno Martínez)
 * @version (a version number or a date)
 */
public class ComparadorNombreTaxi implements Comparator<Taxi>
{
    public int compare(Taxi taxi1, Taxi taxi2){  
        return (taxi1.getName().compareTo(taxi2.getName()) );
    } 
}
