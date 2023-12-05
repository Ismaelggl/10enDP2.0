import java.util.Comparator;

/**
 * Write a description of class ComparadorIdlCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparadorIdlCountTaxi implements Comparator<Taxi>

{
   public int compare(Taxi tx1, Taxi tx2)
    {
        int IdlCount1 = tx1.getValuation();
        int IdlCount2 = tx2.getValuation();
        if(IdlCount1 == IdlCount2){
            return 0;
        }else if(IdlCount1 > IdlCount2){
            return 1;
        }else
        return -1;
    }
}
