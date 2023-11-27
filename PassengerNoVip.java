
/**
 * Write a description of class PassengerNoVip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PassengerNoVip extends Passenger
{
    /**
     * Constructor for objects of class Passenger
     */
    public PassengerNoVip(Location pickup, Location destination, String name, int creditCard)
    {
        super(pickup,destination,name,creditCard);
    }
    
    /**
     * Decrement creditCard of passengers to 30 
     */
    public void pay()
    {
        decrementCreditCard(30);
    }
    
    /**
     * Rate the taxi
     * Double his valuation
     */
    public int calculateEvaluationValue(Taxi taxi){
        return super.calculateEvaluationValue(taxi);
    }
}
