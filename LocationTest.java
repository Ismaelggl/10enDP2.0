import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test implementation of the Location class.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023.10.10 DP classes 
 */
public class LocationTest
{
    Location startLocation;
    Location destination;

    /**
     * Default constructor for test class LocationTest
     */
    public LocationTest()
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
        startLocation = new Location(1, 2);
        destination = new Location(2, 2);
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
     * Test the distance method of the Location class.
     */
    @Test
    public void testDistance()
    {
        assertEquals(startLocation.distance(new Location(5, 7)), 5);
        assertEquals(startLocation.distance(destination), 1);
        //Utilizando otra aserción:
        assertTrue(startLocation.distance(destination) == 1);
    }

    /**
     * Run tests of the nextLocation method of the Location class.
     */
    @Test
    public void testAdjacentLocations()
    {
        // Testear la adyacencia entre dos localizaciones. Se puede hacer 
        // utilizando llamada al método "nextLocation".

        Location destination2 = new Location(6,6);
        
        assertEquals(1, Math.abs(startLocation.nextLocation(destination2).getX() - startLocation.getX()));
        assertEquals(1, Math.abs(startLocation.nextLocation(destination2).getY() - startLocation.getY()));
        
        assertEquals(1, Math.abs(startLocation.nextLocation(destination2).nextLocation(destination2).getX() - startLocation.nextLocation(destination2).getX()));
        assertEquals(1, Math.abs(startLocation.nextLocation(destination2).nextLocation(destination2).getY() - startLocation.nextLocation(destination2).getY()));
    }
}
