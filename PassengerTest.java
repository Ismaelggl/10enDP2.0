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
    Passenger passenger1v;
    Passenger passenger2nv;
    TaxiShuttle taxi1;
    TransportCompany transportc;
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
        passenger1v = new PassengerVip(pickup, destination, "Amador Rivas",1000);
        passenger1v.setTaxiName("Xavineta");
        
        passenger2nv = new PassengerNoVip(pickup, destination, "Maite Figueroa",300);
        passenger2nv.setTaxiName("Submarino amarillo");
        
        transportc = new TransportCompany("Ejemplo");
        
        taxi1 = new TaxiShuttle(transportc, destination, "Submarino amarillo");
        taxi1.incrementValuation(30);
        
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
        
        assertEquals("Amador Rivas",passenger1v.getName());
        assertEquals(pickup,passenger1v.getPickup());
        assertEquals(destination,passenger1v.getDestination());
        
        assertEquals("Maite Figueroa",passenger2nv.getName());
        assertEquals(pickup,passenger2nv.getPickup());
        assertEquals(destination,passenger2nv.getDestination());
        assertEquals("Submarino amarillo",passenger2nv.getTaxiName());
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
        
        assertEquals("Xavineta",passenger1v.getTaxiName());
        assertEquals("Submarino amarillo",passenger2nv.getTaxiName());
    }

    /**
     * Test of the getPickupLocation method.
     * Ensure that this method gets and returns the pickup location correctly.
     */
    @Test
    public void testGetPickupLocation ()
    {
       
        // Testear el método que devuelve la dirección de recogida del objeto.
        assertEquals(pickup,passenger1v.getPickup());
        assertEquals(pickup,passenger2nv.getPickup());
    }
    
    
    /**
     * Test of the decrementCreditCard method.
     * Ensure that this method decrement de credit card amount of money correctly.
     */
    @Test
    public void testdecrementCreditCard ()
    {
       passenger1v.decrementCreditCard(100);
       assertEquals(900,passenger1v.getCreditCard());
    }
    
    /**
     * Test of the getPickupLocation method.
     * Ensure that the passenger pays and rates the taxi after reaching the destination.
     */
    @Test
    public void testAct ()
    {
       assertEquals(60,passenger2nv.act(taxi1));
       assertEquals(270, passenger2nv.getCreditCard());
    }
}
