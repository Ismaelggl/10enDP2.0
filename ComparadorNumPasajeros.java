import java.util.*;

/**
 * Write a description of class ComparadorNumPasajerosTaxi here.
 *
 * @author (Máximo Bueno Martínez)
 * @version (a version number or a date)
 */
public class ComparadorNumPasajeros implements Comparator<Taxi>
{      
   public int compare(Taxi taxi1, Taxi taxi2){  
       ComparadorNombreTaxi compNombre = new ComparadorNombreTaxi();
       if (taxi1.passengersTransported() == taxi2.passengersTransported()){
           return compNombre.compare(taxi1,taxi2);
       }
       else if (taxi1.passengersTransported() > taxi2.passengersTransported()){
           return 1;
       }
       else{
           return -1;
       }
    } 
    
}
