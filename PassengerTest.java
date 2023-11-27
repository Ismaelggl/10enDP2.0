import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PassengerTest.
 *
 * @author  David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023.10.10 DP classes 
 */
public class PassengerTest
{
    Location pickup;     
    Location destination;
    Passenger passenger1;
    Passenger passenger2;
    /**
     * Default constructor for test class PassengerTest
     */
    public PassengerTest()
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
        pickup = new Location(2,2);
        destination = new Location(5,1);
        passenger1 = new Passenger(pickup,destination,"Neymar");
        passenger2 = new Passenger(pickup,destination,"Neymar","Xavineta");
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
     * Test basic creation of a passenger.
     * Ensure that the pickup and destination locations
     * have been set.
     */
    @Test
    public void testCreation()
    {
        
        // Testear la creación correcta de objetos de tipo Passenger comprobando 
        // que la inicialización de campos como dirección de recogida y destino es correcta.
        
        assertEquals("Neymar",passenger1.getName());
        assertEquals(pickup,passenger1.getPickup());
        assertEquals(destination,passenger1.getDestination());
        
        
        assertEquals("Neymar",passenger2.getName());
        assertEquals(pickup,passenger2.getPickup());
        assertEquals(destination,passenger2.getDestination());
        assertEquals("Xavineta",passenger2.getTaxiName());
    }

    /**
     * Test of the getTaxiName method.
     * Ensure that this method gets and returns the name of the taxi correctly.
     */
    @Test
    public void testGetTaxiName()
    {
        // Testear el método que devuelve el nombre del taxi que ha transportado
        //al pasajero/a
        
        assertEquals("Xavineta",passenger2.getTaxiName());
    }

    /**
     * Test of the getPickupLocation method.
     * Ensure that this method gets and returns the pickup location correctly.
     */
    @Test
    public void testGetPickupLocation ()
    {
       
        // Testear el método que devuelve la dirección de recogida del objeto.
        assertEquals(pickup,passenger2.getPickup());
    }
}
