import java.util.Comparator;

/**
 * Write a description of class ComparadorValoracionTaxi here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ComparadorValoracionTaxi implements Comparator<Taxi>
{
   public int compare(Taxi tx1, Taxi tx2)
    {
        int valoracion1 = tx1.getValuation();
        int valoracion2 = tx2.getValuation();
        if(valoracion1 == valoracion2){
            return 0;
        }else if(valoracion1 > valoracion2){
            return 1;
        }else
        return -1;
    }
}
