
/**
 * Write a description of class TaxiShuttle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TaxiShuttle extends Taxi
{
    
    /**
     * Constructor for objects of class TaxiShuttle
     */
    public TaxiShuttle(TransportCompany company, Location location, String name)
    {
        super(company, location, name);
    }

    /**
     * Carry out a taxi's actions.
     */
    public void act() 
    {
        if(getTargetLocation() == null){
         incrementIdleCount();
        }
        else{
           this.setLocation(getLocation().nextLocation(getTargetLocation()));
           System.out.println("@@@ Taxi: " + getName() + " moving to: " + getLocation().getX() + " - " + getLocation().getY());
           if(getTargetLocation().equals(getLocation()) ){
              if (getPassenger() != null){
                 if(getPassenger().getDestination().equals(getLocation()) ){
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
    
    /**
     * Calculates the fuel consumption of the taxi.
     * @return The fuel consumption of the taxi.
     */
    public int obtainConsumption(){
        return (getFuel().getValor())*getInitialLocation().distance(getLocation());
    }
}
