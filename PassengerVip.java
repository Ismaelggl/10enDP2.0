
/**
 * Write a description of class PassengerVip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PassengerVip extends Passenger

{
    /**
     * Constructor for objects of class Passenger
     */
    public PassengerVip(Location pickup, Location destination, String name, int arrivalTime, int creditCard, Reliable reliable)
    {
        super(pickup,destination,name,arrivalTime, creditCard, reliable);
    }
    
    /**
     * Decrement creditCard of passengers to 610 
     */
    public void pay()
    {
        decrementCreditCard(600+10);
    }
    
    /**
     * Rate the taxi
     * Increment his valuation in 15 and double his valuation
     */
    public int calculateEvaluationValue(Taxi taxi){
        if(taxi==null){
            throw new NullPointerException("taxi");
        }
        taxi.incrementValuation(15);
        return super.calculateEvaluationValue(taxi);
    }
}