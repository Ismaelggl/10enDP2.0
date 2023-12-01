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
    private Taxi taxiEx;
    private Passenger passengerV;
    private Taxi taxiSh;
    private Passenger passengerNv;
    
      Location taxiExLocation = new Location(0, 0);
        Location pickup1 = new Location(1, 2);
        Location destination1 = new Location(5, 6);
        Location taxiShLocation = new Location(13, 4);
        Location pickup2 = new Location(2, 7);
        Location destination2 = new Location(1, 14);


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
        // Starting position for the taxiEx.
        
        // Locations for the passengerV.
      
        passengerV = new PassengerVip(pickup1, destination1,"Ana Botín", 25000);
        taxiEx = new TaxiExclusive(company, taxiExLocation,"T1");
        
        // Starting position for the taxiSh.
        
        // Locations for the passengerNv.
        

        passengerNv = new PassengerNoVip(pickup2, destination2,"Rosario Parrales", 1000);
        taxiSh = new TaxiShuttle(company, taxiShLocation,"T2");
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
        assertEquals(true, taxiEx.isFree()); // PRIV: Aqui hay un error que no permite pasar la prueba
        //assertEquals(0, taxiEx.getWeight()); // PRIV: Aquí hay un error que no permite compilar
        
        //assertEquals(true, taxiSh.isFree());
    }

    /**
     * Test that a taxi is no longer free after it has
     * picked up a passenger.
     */
    @Test
    public void testPickup()
    {
        taxiEx.pickup(passengerV);
        assertEquals(passengerV.getDestination(),taxiEx.getTargetLocation());
        assertEquals(passengerV.getTaxiName(),taxiEx.getName());
        
        taxiSh.pickup(passengerNv);
        assertEquals(passengerNv.getDestination(),taxiSh.getTargetLocation());
        assertEquals(passengerNv.getTaxiName(),taxiSh.getName());
    }

    /**
     * Test that a taxi becomes free again after offloading
     * a passenger.
     */
    @Test
    public void testOffload()
    {
        taxiEx.pickup(passengerV);
        taxiEx.offloadPassenger();
        assertEquals(false,taxiEx.hasTargetLocation());
        
        taxiSh.pickup(passengerNv);
        taxiSh.offloadPassenger();
        assertEquals(false,taxiSh.hasTargetLocation());
    }


    /**
     * Test that a taxi picks up and delivers a passenger within
     * a reasonable number of steps.
     */
    @Test
    public void testDelivery()
    {
        int i;
        
        assertEquals(true, taxiEx.isFree());
        for(i = 0; i < 2; i++){
            taxiEx.act();
            assertEquals(false, taxiEx.isFree());
        }
        
        for(i = 0; i < 30; i++){
            taxiEx.act();
            assertEquals(true, taxiEx.isFree());
        }
    /*
        assertEquals(false,taxiEx.hasTargetLocation());
        assertEquals(passengerV.getDestination(),taxiEx.getLocation());
        
        taxiSh.pickup(passengerNv);
        for(i = 0; i < 100; i++){
            taxiSh.act();
        }
        taxiSh.offloadPassenger();
        assertEquals(false,taxiSh.hasTargetLocation());
        assertEquals(passengerNv.getDestination(),taxiSh.getLocation());
        */
    }
    
}

