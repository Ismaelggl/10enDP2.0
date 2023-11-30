
/**
 * Write a description of class TaxiExclusive here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TaxiExclusive extends Taxi implements SerPopularEnRedes
{
    private int weight;
    private int popularidad;

    /**
     * Constructor for objects of class TaxiExclusive
     */
    public TaxiExclusive(TransportCompany company, Location location, String name)
    {
        super(company, location, name);
        weight = 0;
        popularidad = 6;
    }
    
    /**
     * Calculates the fuel consumption of the taxi.
     * @return The fuel consumption of the taxi.
     */
    public int obtainConsumption(){
        return (getFuel().getValor())*getInitialLocation().distance(getLocation());
    }
    
    /**
     * Calculates the new popularity given from the passenger.
     */
    @Override
    public void modificarPopularidad(){
        if (getPassenger().getCreditCard() > 20000){
            popularidad += 4;
        }
        else{
            popularidad--;
        }
    }
    
    /**
     * Carry out a taxi's actions.
     */
    public void act() {
        if(getTargetLocation() == null){
         incrementIdleCount();
        }
        else{
           this.setLocation(getLocation().nextLocation(getTargetLocation()));
           System.out.println("@@@ Taxi: " + getName() + " moving to: " + getLocation().getX() + " - " + getLocation().getY());
           if(getTargetLocation().equals(getLocation()) ){
              if (getPassenger() != null){
                 if(getPassenger().getDestination().equals(getLocation()) ){
                  modificarPopularidad();
                  notifyPassengerArrival(getPassenger());
                  offloadPassenger();
                  incrementPassengersTransported();
                 }
              }
              else{
                notifyPickupArrival();
              }
            }
        }
    }
}
