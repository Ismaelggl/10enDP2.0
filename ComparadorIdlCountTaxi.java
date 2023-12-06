import java.util.Comparator;

/**
 * Compare a taxis by how often the vehicle has nothing to do.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparadorIdlCountTaxi implements Comparator<Taxi>

{
   public int compare(Taxi tx1, Taxi tx2)
    {
        int IdlCount1 = tx1.getIdleCount();
        int IdlCount2 = tx2.getIdleCount();
        if(IdlCount1 == IdlCount2){
            return 0;
        }else if(IdlCount1 > IdlCount2){
            return 1;
        }else
        return -1;
    }
}
