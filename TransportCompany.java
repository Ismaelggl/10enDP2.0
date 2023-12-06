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
    private Map<Taxi, TreeSet<Passenger>> assignments;
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
    public Map<Taxi, TreeSet<Passenger>> getAssignments ()
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
        if(vehicle.getLocation().equals(passenger.getDestination() )){
            System.out.println(">>>>" + vehicle  + " offloads  " + passenger );
            passenger.act(vehicle);//Puntua al taxi
            //Si hay más pasajeros asignamos el siguiente
            Passenger next=vehicle.getPassenger();
            if(next != null)
            vehicle.setTargetLocation(next.getDestination());
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
     * Add a new taxi in the company.
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
    private Taxi scheduleVehicle(Passenger passenger)
    {
        Location pickup = passenger.getPickup();
        boolean salir=false;
        Iterator<Taxi> it = this.vehicles.iterator();
        Taxi taxi = null;
        //Ponemos la localizacion objetivo a los taxis para ordenar los taxis por cercania
        while(it.hasNext()){
           Taxi aux = it.next();
            if( passenger.getCreditCard()>20000 ){
                aux.setPickupLocation(passenger.getPickup()); 
            }else{ //Si passengerNoVIP
                  if( passenger.getCreditCard()<=20000 )
                    aux.setPickupLocation(passenger.getPickup()) ;
                }
        }
        // Creamos un nuevo iterador para recorrer nuevamente la lista ordenada
        //Ordenamos vehicles por distancia al objetivo
        Collections.sort(vehicles, new ComparadorDistanciaTaxi());
        //Devolvemos el primer taxi libre una vez ya ordenados
        Taxi aux = null;
        it = this.vehicles.iterator();
        while (it.hasNext() && !salir){
                aux = it.next();
                if( passenger.getCreditCard()>20000 && aux.getOccupation() == 1 && !assignments.containsKey(aux) ){
                    salir = true;
                    aux.setPickupLocation(passenger.getPickup());
                    aux.addPassenger(passenger);
                    taxi = aux;
                 }else{ //Si passengerNoVIP
                  if( passenger.getCreditCard()<=20000 && aux.getOccupation() != 1 && (!assignments.containsKey(aux) || assignments.get(aux).size() < aux.getOccupation())) 
                  {
                    salir = true;
                    aux.setPickupLocation(passenger.getPickup());
                    aux.addPassenger(passenger);
                    taxi = aux;      
                  }              
                }
        }
        //Reseteamos localizaciones de taxis libres
        it = this.vehicles.iterator();
        while (it.hasNext()){
            aux=it.next();
            if (assignments.get(aux) == null && taxi != aux){
                aux.setPickupLocation(null);
            }
            else if (taxi != aux){
                aux.resetTargetLocation();
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
        TreeSet<Passenger> currentPassengers;
        taxi = scheduleVehicle(passenger);
        //Si no hay taxis libres devolvemos falso y terminamos
        if(taxi==null){
            salir=false;
        }else{
           //Si no existe la asignacion creamos una lista sino la devolvemos
              if(assignments.get(taxi) == null){
              currentPassengers = new TreeSet<> (new ComparadorArrivalTimePassenger());
            }else{
              currentPassengers = assignments.remove(taxi);
              //Eliminamos temporalmente la asignacion
            }
            currentPassengers.add(passenger);
            //Marco destino
            taxi.setTargetLocation(currentPassengers.first().getPickup());
            // Asocia la lista actualizada de pasajeros con el taxi en el mapa
            assignments.put(taxi, currentPassengers); 
            System.out.println("<<<<" + taxi + " go to pick up passenger " + passenger.getName() + " at " +passenger.getPickup());
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
        TreeSet<Passenger> passengers = assignments.get(taxi);        
        if(taxi.getLocation().equals(passengers.first().getPickup()) ){
            assignments.remove(taxi);
            Passenger passenger = passengers.pollFirst();
            taxi.pickup(passenger);
            System.out.println("<<<< "+taxi + " picks up " + passenger.getName());
            assignments.put(taxi, passengers);
        }
    }
    
    /**
     * Return details of the company.
     */
    public void showFinalInfo(){
        System.out.println("-->> Taxi(s) with less time not active <<--");
        Collections.sort(vehicles, new ComparadorIdlCountTaxi());
        System.out.println(vehicles.get(0).showFinalInfo());
        
        System.out.println("-->> Taxi(s) with highest evaluation <<--");
        Collections.sort(vehicles, new ComparadorValoracionTaxi());
        System.out.println(vehicles.get(vehicles.size() - 1).showFinalInfo());    
    }
}