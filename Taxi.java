 
import java.util.*;
/**
 * Model the common elements of taxis and shuttles.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023.10.10 DP classes 
 */
public abstract class Taxi 
{
    // The Taxi Company of this Taxi.
    private TransportCompany company;
    // Where the vehicle is.
    private Location location;
    // Where the vehicle was created
    private Location initialLocation;
    // Where the vehicle is headed.
    private  Location targetLocation;
    // Record how often the vehicle has nothing to do.
    private int idleCount;
    //name of the taxi
    private String name;
    //Person who is carried
    private TreeSet<Passenger> passengers;
    //private Passenger passenger;
    //Number of passengers transported
    private int passengersTransported;
    //The average fuel consumption of the taxi.
    private FuelConsumption fuelConsumption;
    //Valuation of the passengers.
    int valuation;
    //Maximum occupation of the taxi.
    int occupation;
    /**
     * Constructor of class Vehicle
     * @param company The taxi company. Must not be null.
     * @param location The vehicle's starting point. Must not be null.
     * @throws NullPointerException If company or location is null.
     */
    public Taxi(TransportCompany company, Location location, String name, FuelConsumption fuelConsumption)
    {
        if(company == null) {
            throw new NullPointerException("company");
        }
        if(location == null) {
            throw new NullPointerException("location");
        }
        this.company = company;
        this.initialLocation = location;
        this.location = location;
        targetLocation = null;
        this.idleCount = 0;
        this.name = name;
        this.passengers = new TreeSet<> (new ComparadorArrivalTimePassenger());
        this.passengersTransported = 0;  
        this.valuation = valuation; //priv y esto? 
        this.occupation = 1;
        this.fuelConsumption = fuelConsumption;
    }

    
    /**
     * @return the name of the taxi
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the location.
     * @return Where this taxi is currently located.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Get the initial location.
     * @return Where this taxi was created.
     */
    public Location getInitialLocation()
    {
        return initialLocation;
    }
    
    /**
     * Get the passenger.
     * @return The passenger who is being transported.
     */
    public Passenger getPassenger()
    {
        if (!isFree()){
        return passengers.first();
        }   
        else{
            return null;
        }
    }
    
    /**
     * @return The maximum occupation of the taxi.
     */
    public int getOccupation()
    {
        return occupation;
    }
    
    /**
     * Adds a passenger to the TreeSet.
     */
    public void addPassenger(Passenger passenger){
        passengers.add(passenger);
        resetTargetLocation();
    }
    
    /**
     * Removes the first passenger of the TreeSet.
     */
    public void removePassenger(){
        passengers.pollFirst();
    }
    
    /**
     * Sets the target location to the first passenger destination.
     */
    public void resetTargetLocation(){
        setTargetLocation(passengers.first().getPickup());
    }
    
    /**
     * Get the fuel consumption.
     * @return The fuel consumption of the taxi.
     */
    public FuelConsumption getFuel (){
        return fuelConsumption;
    }
    
    public abstract int obtainConsumption ();

    /**
     * Set the current location.
     * @param location Where it is. Must not be null.
     * @throws NullPointerException If location is null.
     */
    public void setLocation(Location location)
    {
        if(location != null) {
            this.location = location;
        }
        else {
            throw new NullPointerException();
        }
    }
    
    /**
     * Get the target location.
     * @return Where this vehicle is currently headed, or null
     *         if it is idle.
     */
    public Location getTargetLocation()
    {
        return targetLocation;
    }

    /**
     * Set the required target location.
     * @param location Where to go. Must not be null.
     */
    public void setTargetLocation(Location location)
    {
        targetLocation = location;
    }

     /**
     * Receive a pickup location. This becomes the
     * target location.
     * @param location The pickup location.
     */
    public void setPickupLocation(Location location)
    {
        setTargetLocation(location);
    }
    
    /**
     * Set the occupation of the taxi.
     */
    
    public void setOccupation(int occupation1){
        this.occupation = occupation1;
    }
    /**
     * Has the vehicle a target Location?
     * @return Whether or not this vehicle has a target Location.
     */
    public boolean hasTargetLocation(){
        return getTargetLocation() != null;
    }
    
    /**
     * Clear the target location.
     */
    public void clearTargetLocation()
    {
        targetLocation = null;
    }

    /**
     * @return on how many steps this vehicle has been idle.
     */
    public int getIdleCount()
    {
        return idleCount;
    }

    /**
     * Increment the number of steps on which this vehicle
     * has been idle.
     */
    public void incrementIdleCount()
    {
        idleCount++;
    }

    /**
     * Return details of the taxi, such as where it is.
     * @return A string representation of the taxi.
     */
    public String toString()
    {
        return getClass().getName() + " " +getName()+" at " + getLocation() 
        + " occupation " + occupation;
    }

    /**
     * Is the taxi free?
     * @return Whether or not this taxi is free.
     */
    public boolean isFree()
    {
        return passengers.isEmpty();
    }

    /**
     * Notify the company of our arrival at a pickup location.
     */
    public void notifyPickupArrival()
    {
           company.arrivedAtPickup(this);
    }

    /**
     * Notify the company of our arrival at a passenger's destination.
     */
    public void notifyPassengerArrival(Passenger passenger)
    {
           company.arrivedAtDestination(this , passenger);
    }
    

    /**
     * Receive a passenger.
     * Set passenger's destination as its target location.
     * @param passenger The passenger.
     */
    public void pickup(Passenger passenger)
    {   
        if(passenger != null){
        this.targetLocation = passenger.getDestination();
        passengersTransported++;
        passenger.setTaxiName(this.getName());
        this.passengers.add(passenger);
        }else{
            throw new NullPointerException("passenger");
        }
    }   
    
    /**
     * Offload the passenger.
     */
    public void offloadPassenger()
    {
        this.targetLocation = null;
        passengersTransported--;       
        this.passengers.pollFirst();
    }

    /**
     * @return how many passengers this vehicle has transported.
     */
    public int passengersTransported()
    {
        return passengersTransported;
    }
    
    /**
     * Increment the number of passengers this vehicle has transported.
     */
    protected void incrementPassengersTransported()
    {
        this.passengersTransported ++;
    }

    /**
     * Get the distance to the target location from the current location.
     * @return distance to target location.
     */
    public int distanceToTheTargetLocation()
    {
        return this.location.distance(targetLocation);
    }

    /**
     * Carry out a taxi's actions.
     */
    public abstract void act();
    
    /**
     * Add to valuation n.
     */
    public void incrementValuation(int n)
    {
        valuation = valuation + n;   
    }
    
    /**
     * @return valuation.
     */   
    public int getValuation(){
        return valuation;
    }    
    
     /**
     * Return details of the taxi, such as where it is.
     * @return A string representation of the taxi.
     */
    public String showFinalInfo()
    {
        return  getClass().getSimpleName() +" " + name +" at "+ location.toString()+
        " occupation " + occupation + " -  passengers transported: "+ passengersTransported +
        " - non active for: "+ idleCount + " times - valuation: " + valuation
        + " - consumption: " + obtainConsumption(); 
    }
}   