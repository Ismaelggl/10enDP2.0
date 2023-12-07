import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TaxiTest.
 *
 * @author  David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023.10.10 DP classes 
 */
public class TaxiTest
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
     * Default constructor for test class TaxiTest
     */
    public TaxiTest()
    {
    }

    /**
     * Create a taxi.
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
        
        company = new TransportCompany("Compañía Taxis Cáceres");
      
        passengerV = new PassengerVip(pickup1, destination1,"Ana Botín", 30, 25000, Reliable.LOW);
        taxiEx = new TaxiExclusive(company, taxiExLocation,"T1", FuelConsumption.HIGH, 10);

        passengerNv = new PassengerNoVip(pickup2, destination2,"Rosario Parrales", 10, 1000, Reliable.HIGH);
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
     * Test creation and the initial state of a taxi.
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
     * Test that a taxi is no longer free after it has
     * picked up a passenger.
     */
    @Test
    public void testPickup()
    {
        // Taxi Exclusive Test
        // 1st passenger
        taxiEx.pickup(passengerV);
        assertEquals(passengerV.getDestination(),taxiEx.getTargetLocation());
        assertEquals(passengerV.getTaxiName(),taxiEx.getName());
        taxiEx.offloadPassenger();
        // 2nd passenger
        taxiEx.pickup(passengerNv);
        assertEquals(passengerNv.getDestination(),taxiEx.getTargetLocation());
        assertEquals(passengerNv.getTaxiName(),taxiEx.getName());
        taxiEx.offloadPassenger();
        
        // Taxi Shuttle Test
        // 1st passenger
        taxiSh.pickup(passengerNv);
        assertEquals(passengerNv.getDestination(),taxiSh.getTargetLocation());
        assertEquals(passengerNv.getTaxiName(),taxiSh.getName());
        taxiEx.offloadPassenger();
        // 2nd passenger
        taxiSh.pickup(passengerV);
        assertEquals(passengerV.getDestination(),taxiSh.getTargetLocation());
        assertEquals(passengerV.getTaxiName(),taxiSh.getName());
    }

    /**
     * Test that a taxi becomes free again after offloading
     * a passenger.
     */
    @Test
    public void testOffload()
    {
        
        
        // Taxi Exclusive Test
        // 1st passenger
        taxiEx.pickup(passengerV);
        taxiEx.offloadPassenger();
        assertEquals(false,taxiEx.hasTargetLocation());
        // 2nd passenger
        taxiEx.pickup(passengerNv);
        taxiEx.offloadPassenger();
        assertEquals(false,taxiEx.hasTargetLocation());
        
        // Taxi Shuttle Test
        // 1st passenger
        taxiSh.pickup(passengerNv);
        taxiSh.offloadPassenger();
        assertEquals(false,taxiSh.hasTargetLocation());
        // 2nd passenger
        taxiSh.pickup(passengerV);
        taxiSh.offloadPassenger();
        assertEquals(false,taxiSh.hasTargetLocation());
    }


    /**
     * Test that a Taxi Exclusive picks up and delivers a passenger within
     * a reasonable number of steps.
     */
    @Test
    public void testDeliveryTaxiExclusive()
    {
        company.addVehicle(taxiEx);
        company.addPassenger(passengerV);
       
        
        int i;
        
        assertEquals(true, taxiEx.isFree());
        assertEquals(false,taxiEx.hasTargetLocation());
        
        company.requestPickup(passengerV);
        
        assertEquals(true,taxiEx.hasTargetLocation());
        for(i = 0; i < 5; i++){
            taxiEx.act();
        }
        
        assertEquals(false, taxiEx.isFree());
        assertEquals(true,taxiEx.hasTargetLocation());
        
        for(i = 0; i < 6; i++){
            taxiEx.act();
        }
        assertEquals(true, taxiEx.isFree());
        assertEquals(false,taxiEx.hasTargetLocation());       
    }
  
    
    /** 
     * Test that a Taxi Shuttle picks up and delivers a passenger within
     * a reasonable number of steps.
     */
    @Test
    public void testDeliveryTaxiShuttle()
    {
        company.addVehicle(taxiSh);
        company.addPassenger(passengerNv);
        
        int i;
    
        assertEquals(true, taxiSh.isFree());
        assertEquals(false,taxiSh.hasTargetLocation());
        company.requestPickup(passengerNv);
        assertEquals(true,taxiSh.hasTargetLocation());
        for(i = 0; i < 5; i++){
            taxiSh.act();
        }
        assertEquals(false, taxiSh.isFree());
        assertEquals(true,taxiSh.hasTargetLocation());
        
        for(i = 0; i < 15; i++){
            taxiSh.act();
        }
        assertEquals(true, taxiSh.isFree());
        assertEquals(false,taxiSh.hasTargetLocation());
        
    }
    
    
    /** 
     * Test 
     * 
     */
    @Test
    public void testObtainComsumption()
    {
        // Taxi Exclusive
        int i;
        company.addVehicle(taxiEx);
        company.addPassenger(passengerV);
        company.requestPickup(passengerV);
        
        for(i = 0; i < 5; i++){
            taxiEx.act();
        }
        
        assertEquals(200, taxiEx.obtainConsumption());
        
        // Taxi Shuttle
        
        company.addVehicle(taxiSh);
        company.addPassenger(passengerNv);
        company.requestPickup(passengerNv);
        
        for(i = 0; i < 5; i++){
            taxiSh.act();
        }
        
        assertEquals(4, taxiSh.obtainConsumption());
    }
    
     /** 
     * Test 
     * 
     */
    @Test
    public void testGetPassenger()
    {
        // Taxi Exclusive
        
        taxiEx.addPassenger(passengerV);
        assertEquals(passengerV, taxiEx.getPassenger());
        
        // Taxi Shuttle
        
        taxiSh.addPassenger(passengerNv);
        assertEquals(passengerNv, taxiSh.getPassenger());
        
    }
    
}
    


