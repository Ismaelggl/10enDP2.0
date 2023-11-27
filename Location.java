/**
 * Model a location in a city.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023.10.10 DP classes 
 */
public class Location
{
    private int x;
    private int y; 

    /**
     * Model a location in the city.
     * @param x The x coordinate. Must be positive.
     * @param y The y coordinate. Must be positive.
     * @throws IllegalArgumentException If a coordinate is negative.
     */
    public Location(int x, int y)
    {
        if(x < 0) {
            throw new IllegalArgumentException(
                "Negative x-coordinate: " + x);
        }
        if(y < 0) {
            throw new IllegalArgumentException(
                "Negative y-coordinate: " + y);
        }        
        this.x = x;
        this.y = y;
    }

    /**
     * @return The x coordinate.
     */
    public int getX()
    {
        return x;
    }

    /**
     * @return The y coordinate.
     */
    public int getY()
    {    
        return y;
    }
    
    /**
     * Set the x coordinate of the location.
     * @param x The x coordinate.
     */
    public void setX(int x){
        this.x = x;
    }
    
    /**
     * Set the y coordinate of the location.
     * @param y La coordenada y.
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Generate the next location to visit in order to
     * reach the destination.
     * @param destination Where we want to get to.
     * @return A location in a direct line from this to
     *         destination.
     */
    public Location nextLocation(Location destination)
    {
        Location loc = new Location(getX(),getY());
        if (destination.x != x && destination.y != y){
            if (x < destination.x && y < destination.y){
                loc.x++;
                loc.y++;
            }
            else if (x < destination.x && y > destination.y){
                loc.x++;
                loc.y--;
            }
            else if (x > destination.x && y < destination.y){
                loc.x--;
                loc.y++;
            }
            else {
                loc.x--;
                loc.y--;
            }
        }
        else if (destination.x != x && destination.y == y){
            if (x < destination.x){
                loc.x++;
            }
            else{
                loc.x--;
            }
        }
        else if (destination.x == x && destination.y != y){
            if (y < destination.y){
                loc.y++;
            }
            else{
                loc.y--;
            }
        }
        return loc;
    }

    /**
     * Determine the number of movements required to get
     * from here to the destination.
     * @param destination The required destination.
     * @return the number of movement steps.
     */
    public int distance(Location destination)
    {
        return Math.max(Math.abs(destination.x - x), Math.abs(destination.y -y));
    }

    /**
     * Implement content equality for locations.
     * @return true if this location matches the other, false otherwise.
     */
    public boolean equals(Object other)
    {
        if(other instanceof Location) {
            Location otherLocation = (Location) other;
            return x == otherLocation.getX() &&
            y == otherLocation.getY();
        }
        else {
            return false;
        }
    }

    /**
     * @return A representation of the location.
     */
    public String toString()
    {
        return "location " + x + "," + y;
    }

    /**
     * Use the top 16 bits for the y value and the bottom for the x.
     * Except for very big grids, this should give a unique hash code
     * for each (x, y) pair.
     * @return A hashcode for the location.
     */
    public int hashCode()
    {
        return (y << 16) + x;
    }

}
