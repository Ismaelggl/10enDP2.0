/**
 * Model a passenger wishing to get from one
 * location to another.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023.10.10 DP classes 
 */
public abstract class Passenger
{
    private String name;
    private Location pickup;
    private Location destination;
    private String taxiName;
    private int arrivalTime;
    private int creditCard;

    /**
     * Constructor for objects of class Passenger
     * @param pickup The pickup location, must not be null.
     * @param destination The destination location, must not be null.
     * @param name The passenger's name
     * @throws NullPointerException If either location is null.
     */
    public Passenger(Location pickup, Location destination, String name)
    {
        if(pickup == null) {
            throw new NullPointerException("Pickup location");
        }
        if(destination == null) {
            throw new NullPointerException("Destination location");
        }
        this.pickup = pickup;
        this.destination = destination;
        this.name = name;
        this.taxiName = "";
    }
    
    /**
     * Constructor for objects of class Passenger
     * @param pickup The pickup location, must not be null.
     * @param destination The destination location, must not be null.
     * @param name The passenger's name
     * @param taxiName The taxi's name
     * @throws NullPointerException If either location is null.
     */
    public Passenger(Location pickup, Location destination, String name, String taxiName){
        if(pickup == null) {
            throw new NullPointerException("Pickup location");
        }
        if(destination == null) {
            throw new NullPointerException("Destination location");
        }
        this.pickup = pickup;
        this.destination = destination;
        this.name = name;
        this.taxiName = taxiName;
    }

    /**
     * @return The name of the passenger.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return The pickup location.
     */
    public Location getPickup(){
        return pickup;
    }

    /**
     * @return The destination location.
     */
    public Location getDestination()
    {
        return destination;
    }
    
    /**
     * @return The taxi name.
     */
    public String getTaxiName(){
        return taxiName;
    }    
    
    /**
     * Set the name of the passenger.
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Set the pickup location.
     */
    public void setPickup(Location pickup){
        this.pickup = pickup;
    }
    
    /**
     * Set the destination location.
     */
    public void setDestination(Location destination){
        this.destination = destination;
    }
    
    /**
     * Set the taxi's name.
     */
    public void setTaxiName(String taxiName){
        this.taxiName = taxiName;
    }
    
    /**
     * Return details of the passenger, such as where it is.
     * @return A string representation of the passenger.
     */
    public String toString()
    {
        return "Passenger "+getName()+" travelling from " +
        pickup + " to " + destination;
    }
    
    
    /**
     * Show the final information about the passenger, including the name of the taxi that used.
     */
    public void showFinalInfo()
    {
        System.out.println("Passenger "+ getName() + " in " + destination + 
            " transported by: " + taxiName);
    }
        public void act(){
        //pay()
        //int i=calculateEvaluationValue()
        //return i;
    }

}
