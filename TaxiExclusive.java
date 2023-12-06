
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
    public TaxiExclusive(TransportCompany company, Location location, String name, FuelConsumption fuelConsumption, int weight)
    {
        super(company, location, name,fuelConsumption);
        setWeight(weight);
        popularidad = 6;
        occupation = 1;
    }
    
    /**
     * Calculates the fuel consumption of the taxi.
     * @return The fuel consumption of the taxi.
     */
    public int obtainConsumption(){
        return (getFuel().getValor())*getInitialLocation().distance(getLocation())*(weight/2);
    }
    
     /**
     * Get the taxi weight.
     * @return The taxi weight.
     */
    public int getWeight(){
        return weight;
    }
    
    /**
     * Set the taxi weight.
     */
    public void setWeight(int weight1){
        weight = weight1;
    }
    
    /**
     * Calculates the new popularity given from the passenger.
     */
    //PRIV: He quitado el override (Samuel)
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
           System.out.println("@@@ Taxi: " + getName() + " moving to: " + getLocation().getX() + "," + getLocation().getY());
           if(getTargetLocation().equals(getLocation()) ){
              if (getPassenger() != null){
                 if(getPassenger().getDestination().equals(getLocation()) ){
                  modificarPopularidad();
                  notifyPassengerArrival(getPassenger());
                  offloadPassenger();
                  if(getPassenger() != null){
                    setTargetLocation(getPassenger().getPickup());
                    }
                  incrementPassengersTransported();
                 }
                 else{
                notifyPickupArrival();
              }
              }
              
            }
        }
    }
    
    public String showFinalInfo()
    {
        return super.showFinalInfo() + " - popularity: " + popularidad;
    }
}
