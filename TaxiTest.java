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
    private Taxi taxi1;
    private Passenger passenger1;
    private Taxi taxi2;
    private Passenger passenger2;

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
        TransportCompany company = new TransportCompany("Compañía Taxis Cáceres");
        // Starting position for the taxi1.
        Location taxi1Location = new Location(0, 0);
        // Locations for the passenger1.
        Location pickup1 = new Location(1, 2);
        Location destination1 = new Location(5, 6);

        //passenger1 = new Passenger(pickup1, destination1,"Kevin");
        taxi1 = new Taxi(company, taxi1Location,"T1");
        
        // Starting position for the taxi2.
        Location taxi2Location = new Location(13, 4);
        // Locations for the passenger2.
        Location pickup2 = new Location(2, 7);
        Location destination2 = new Location(1, 14);

       // passenger2 = new Passenger(pickup2, destination2,"Parrales");
        taxi2 = new Taxi(company, taxi2Location,"T2");
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
        assertEquals(true, taxi1.isFree());
    }

    /**
     * Test that a taxi is no longer free after it has
     * picked up a passenger.
     */
    @Test
    public void testPickup()
    {
        taxi1.pickup(passenger1);
        assertEquals(passenger1.getDestination(),taxi1.getTargetLocation());
        assertEquals(passenger1.getTaxiName(),taxi1.getName());
        
        taxi2.pickup(passenger2);
        assertEquals(passenger2.getDestination(),taxi2.getTargetLocation());
        assertEquals(passenger2.getTaxiName(),taxi2.getName());
    }

    /**
     * Test that a taxi becomes free again after offloading
     * a passenger.
     */
    @Test
    public void testOffload()
    {
        taxi1.pickup(passenger1);
        taxi1.offloadPassenger();
        assertEquals(false,taxi1.hasTargetLocation());
        
        taxi2.pickup(passenger2);
        taxi2.offloadPassenger();
        assertEquals(false,taxi2.hasTargetLocation());
    }


    /**
     * Test that a taxi picks up and delivers a passenger within
     * a reasonable number of steps.
     */
    @Test
    public void testDelivery()
    {
        int i;
            
        taxi1.pickup(passenger1);
        for(i = 0; i < 100; i++){
            taxi1.act();
        }
        taxi1.offloadPassenger();
        assertEquals(false,taxi1.hasTargetLocation());
        assertEquals(passenger1.getDestination(),taxi1.getLocation());
        
        taxi2.pickup(passenger2);
        for(i = 0; i < 100; i++){
            taxi2.act();
        }
        taxi2.offloadPassenger();
        assertEquals(false,taxi2.hasTargetLocation());
        assertEquals(passenger2.getDestination(),taxi2.getLocation());
    }
}

