
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
    public PassengerVip(Location pickup, Location destination, String name, int creditCard)
    {
        super(pickup,destination,name,creditCard);
    }
    
    /**
     * Decrement creditCard of passengers to 615 
     */
    public void pay()
    {
        decrementCreditCard(600+15);
    }
    
    /**
     * Rate the taxi
     * Increment his valuation in 15 and double his valuation
     */
    public int calculateEvaluationValue(Taxi taxi){
        //taxi.incrementValuation(15);
        return 0; //super.calculateEvaluationValue(taxi);
    }
}