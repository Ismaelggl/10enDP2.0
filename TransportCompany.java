import java.util.*;

/**
 * Model the operation of a taxi company, operating different
 * types of vehicle. This version operates a single taxi.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class TransportCompany  
{
    //nombre de la compañía
    private String name;
    //Company taxis
    private ArrayList<Taxi> vehicles;
    //Company passengers arranged in alphabetical order
    private ArrayList<Passenger> passengers;
    //Assignaments between passengers and taxis
    private Map<Taxi, Passenger> assignments;
    /**
     * Constructor for objects of class TransportCompany
     */
    public TransportCompany(String name)
    {
        this.name = name;
        vehicles= new ArrayList<> ();
        passengers = new ArrayList<>();
        assignments = new HashMap<>();
    }

    /**
     * @return The name of the company.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @return The asignments between passengers and taxis.
     */
    public Map<Taxi, Passenger> getAssignments ()
    {
        return assignments;
    }

    /**
     * A vehicle has arrived at a passenger's destination.
     * @param vehicle The vehicle at the destination.
     * @param passenger The passenger being dropped off.
     */
    public void arrivedAtDestination(Taxi vehicle,
    Passenger passenger)
    {
        if(vehicle.getLocation().equals( passenger.getDestination() )){
        System.out.println(">>>> Taxi " + vehicle.getName() +" at " + vehicle.getLocation() +
        " offloads Passenger " + passenger.getName() + " travelling from " +
        passenger.getPickup()  + " to " + passenger.getDestination() );
        }
    }

    /**
     * @return The list of vehicles.
     */
    public List<Taxi> getVehicles()
    {       
        return this.vehicles;
    }

    /**
     * @return The list of passengers.
     */
    public List<Passenger> getPassengers()
    {
        return this.passengers;
    }

    /**
     * @param Add the new Vehicle.
     */
    public void addVehicle(Taxi vehicle)
    {
        this.vehicles.add(vehicle);
    }

    /**
     * Add a new passenger in the company.
     * @param passenger The new passenger.
     */
    public void addPassenger(Passenger passenger)
    {
        passengers.add(passenger);
        Collections.sort(passengers, new ComparadorNombrePassenger() );
    }

    /**
     * Find a the most closed free vehicle to a location, if any.
     * @param location location to go
     * @return A free vehicle, or null if there is none.
     */
    private Taxi scheduleVehicle(Location location)
    {
        boolean salir=false;
        Iterator<Taxi> it = this.vehicles.iterator();
        Taxi taxi = null;
        //Asignamos la localización a los taxis libres
        while(it.hasNext()){
           Taxi aux = it.next();
            if(aux.isFree() && assignments.get(aux) == null) 
                aux.setPickupLocation(location);
        }
        // Creamos un nuevo iterador para recorrer nuevamente la lista ordenada
        //Ordenamos vehicles por distancia al objetivo
        Collections.sort(vehicles, new ComparadorDistanciaTaxi());
        //Devolvemos el primer taxi libre una vez ya ordenados
        Taxi aux = null;
        it = this.vehicles.iterator();
        while (it.hasNext() && !salir){
                aux = it.next();
                if(aux.isFree() && assignments.get(aux) == null){
                    salir = true;
                    taxi = aux;
                    aux.setPickupLocation(location);
                  }
        }
        while (it.hasNext()){
            aux=it.next();
            if (assignments.get(aux) == null){
            aux.setPickupLocation(null);
        }
        }
        //Sino hay taxis libres devolvemos nulo para poder comprobralo
        return taxi;
    }

    /**
     * Request a pickup for the given passenger.
     * @param passenger The passenger requesting a pickup.
     * @return Whether a free vehicle is available.
     */
    public boolean requestPickup(Passenger passenger)
    {
        Taxi taxi;
        boolean salir = true;
        taxi = scheduleVehicle(passenger.getPickup() );
        if(taxi != null && assignments.get(taxi) == null){
        taxi.setPickupLocation(passenger.getPickup() );
        assignments.put(taxi, passenger);//Asigna un taxi a un pasajero (Map)
        System.out.println("<<<< Taxi " + taxi.getName() + " at " + taxi.getLocation() + " go to pick up passenger " + passenger.getName() + " at " 
            + passenger.getPickup());
        }

        else{
            salir = false;
        }
        return salir;
    }

    /**
     * A vehicle has arrived at a pickup point.
     * @param vehicle The vehicle at the pickup point.
     */
    public void arrivedAtPickup(Taxi taxi)
    {
        // Obtén el pasajero asignado al taxi
        Passenger passenger = assignments.get(taxi);
        taxi.pickup(passenger);
        System.out.println("<<<< "+taxi + " picks up " + passenger.getName());
        //Se elimina la asignacion
        assignments.remove(taxi);
        passenger.setTaxiName(taxi.getName() );
        
    }

}