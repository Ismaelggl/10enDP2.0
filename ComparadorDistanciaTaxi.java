import java.util.Comparator;

/**
 * Compare a taxis by distance to the target location.
 * 
 * @author (Ismael González Loro) 
 * @version (a version number or a date)
 */
public class ComparadorDistanciaTaxi implements Comparator<Taxi>
{ 
   public int compare(Taxi tx1, Taxi tx2)
    {
        int distancia1 = tx1.distanceToTheTargetLocation();
        int distancia2 = tx2.distanceToTheTargetLocation();
        if(distancia1 == distancia2){
            return (tx1.getName().compareTo(tx2.getName() ));
        }else if(distancia1 > distancia2){
            return 1;
        }else
        return -1;
    }
}


