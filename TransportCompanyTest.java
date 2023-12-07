

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TransportCompanyTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TransportCompanyTest
{
    private TaxiExclusive taxiEx;
    private PassengerVip passengerV;
    private TaxiShuttle taxiSh;
    private PassengerNoVip passengerNv;
    
        Location taxiExLocation;
        Location pickup1;
        Location destination1;
        Location taxiShLocation;
        Location pickup2;
        Location destination2;
        TransportCompany company;
        
    /**
     * Default constructor for test class TransportCompanyTest
     */
    public TransportCompanyTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        taxiExLocation = new Location(0, 0);
        pickup1 = new Location(1, 2);
        destination1 = new Location(5, 6);
        taxiShLocation = new Location(4, 7);
        pickup2 = new Location(2, 7);
        destination2 = new Location(9, 8);
        
        company = new TransportCompany("Compañía Taxis Jaraíz de la Vera");
      
        passengerV = new PassengerVip(pickup1, destination1,"Gerard Piqué", 30, 25000, Reliable.LOW);
        taxiEx = new TaxiExclusive(company, taxiExLocation,"T1", FuelConsumption.HIGH, 10);

        passengerNv = new PassengerNoVip(pickup2, destination2,"Richard Widmark", 10, 1000, Reliable.HIGH);
        taxiSh = new TaxiShuttle(company, taxiShLocation,"T2", FuelConsumption.LOW, 6);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * 
     */
    @Test
    public void testCreation()
    {
        assertEquals(taxiExLocation, taxiEx.getLocation());
        assertEquals(false, taxiEx.hasTargetLocation());
        assertEquals(true, taxiEx.isFree());
        assertEquals(10, taxiEx.getWeight());
        assertEquals(8, taxiEx.getFuel().getValor());
        
        assertEquals(true, taxiSh.isFree());
        assertEquals(false, taxiSh.hasTargetLocation());
        assertEquals(true, taxiSh.isFree());
        assertEquals(4, taxiSh.getFuel().getValor());
        
    }
    
    /**
     * 
     */
    @Test
    public void testRequestPickup()
    {
        // Taxi Exclusive
        company.addVehicle(taxiEx);
        company.addPassenger(passengerV);
        
        assertEquals(false, taxiEx.hasTargetLocation());
        company.requestPickup(passengerV);
        assertEquals(true, taxiEx.hasTargetLocation());
            
        // Taxi Shuttle
        company.addVehicle(taxiSh);
        company.addPassenger(passengerNv);
        
        assertEquals(false, taxiSh.hasTargetLocation());
        company.requestPickup(passengerNv);
        assertEquals(true, taxiSh.hasTargetLocation());
    }
    
    /**
     * 
     */
    @Test
    public void testArrivedAtPickup()
    {
        int i;
        
        // Taxi Exclusive
        company.addVehicle(taxiEx);
        company.addPassenger(passengerV);
        
        assertEquals(true,taxiEx.isFree());
        company.requestPickup(passengerV);
        
        for(i = 0; i < 5; i++){
            taxiEx.act(); // The method act of the class Taxi calls testArrivedAtPickUp
        }
        assertEquals(false,taxiEx.isFree());
        
        // Taxi Shuttle
        company.addVehicle(taxiSh);
        company.addPassenger(passengerNv);
        
        assertEquals(true,taxiSh.isFree());
        company.requestPickup(passengerNv);
        
        for(i = 0; i < 5; i++){
            taxiSh.act(); // The method act of the class Taxi calls testArrivedAtPickUp
        }
        assertEquals(false,taxiSh.isFree());
        
    }
    
     /**
     * 
     */
    @Test
    public void testArrivedAtDestinationTaxiExclusive()
    {
         // Taxi Exclusive
        company.addVehicle(taxiEx);
        company.addPassenger(passengerV);
         
        int i;
        
        company.requestPickup(passengerV);
        
        assertEquals(true,taxiEx.hasTargetLocation());
        for(i = 0; i < 5; i++){
            taxiEx.act();
        }
        
        assertEquals(0, taxiEx.getValuation());
        
        for(i = 0; i < 6; i++){
            taxiEx.act(); // The method act of the class Taxi calls testArrivedDestination, which modifies the valuation of the taxi
        }
        assertEquals(25, taxiEx.getValuation()); 
        
        
        
    }
    
       /**
     * 
     */
    @Test
    public void testArrivedAtDestinationTaxiShuttle()
    {
        company.addVehicle(taxiSh);
        company.addPassenger(passengerNv);
        
        int i;

        company.requestPickup(passengerNv);
        for(i = 0; i < 5; i++){
            taxiSh.act();
        }
       
        assertEquals(0, taxiSh.getValuation());
        for(i = 0; i < 15; i++){
            taxiSh.act();
        }
        assertEquals(20, taxiSh.getValuation());
        
        
    }
    
    
}
