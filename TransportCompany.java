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
            System.out.println(">>>> Taxi " + vehicle.getName() +" at " + vehicle.getLocation() +
            " offloads Passenger " + passenger.getName() + " travelling from " +
            passenger.getPickup()  + " to " + passenger.getDestination() );
            passenger.act(vehicle);//Puntua al taxi
            //Eliminamos el pasajero
            //Si hay más pasajeros asignamos el siguiente
            if(!passengers.isEmpty()){
                vehicle.setTargetLocation(passengers.get(0).getDestination());
                
            }
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
    private Taxi scheduleVehicle(Passenger passenger)
    {
        boolean salir=false;
        boolean EsVip = passenger.getCreditCard()>20000;
        Iterator<Taxi> it = this.vehicles.iterator();
        Taxi taxi = null;
        //Ponemos la localizacion objetivo a los taxis para ordenar los taxis por cercania
        //Ponemos la localizacion a los taxis libres si el pasaje
        while(it.hasNext()){
           Taxi aux = it.next();
               //Si es VIP el taxi debe estar Vacio y ser Exclusive
                if(aux.isFree() && aux.isExclusive() ||!aux.isFull())
                {
                    aux.setPickupLocation(passenger.getPickup());
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
            if(EsVip)
            {   //Si es VIP el taxi debe estar Vacio y ser Exclusive
                if(aux.isFree() && aux.getOccupation() == 1)
                {
                    salir=true;
                    taxi=aux;
                }
            }else{
                //Sino es VIP el taxi no puede estar lleno
                if(!aux.isFull())
                {
                    salir=true;
                    taxi=aux;
                }
            }
        }
        //Reseteamos localizaciones de taxis libres
        while (it.hasNext()){
            aux=it.next();
            //Si es VIP el taxi debe estar Vacio y ser Exclusive
            if(aux.isFree())
            {
                aux.setTargetLocation(null);
            }
            //Sino es VIP el taxi no puede estar lleno
            if(!aux.isFull())
            {
              if(!assignments.containsKey(aux))
              {
                aux.setTargetLocation(null);
              }else{
                aux.setTargetLocation(assignments.get(aux).first().getPickup());
              }
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
        taxi = scheduleVehicle(passenger);
        TreeSet<Passenger> TreePassengers;
        //Si no hay taxis libres devolvemos falso y terminamos
        if(taxi==null){
            salir=false;
        }else{
           //Si no existe la asignacion creamos una lista sino la devolvemos
              if(assignments.get(taxi) == null){
              TreePassengers = new TreeSet<> (new ComparadorArrivalTimePassenger());
            }else{
            TreePassengers = assignments.remove(taxi);              
            //Eliminamos temporalmente la asignacion
            }
            TreePassengers.add(passenger);
            //Ordeno y marco destino
            taxi.setTargetLocation(TreePassengers.first().getPickup());
            // Asocia la lista actualizada de pasajeros con el taxi en el mapa
            assignments.put(taxi, TreePassengers); 
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
            //Se elimina la asignacion
            assignments.put(taxi,passengers);
        }
    }
    public void showFinalInfo(){
        Collections.sort(vehicles, new ComparadorIdlCountTaxi());
        vehicles.get(0).showFinalInfo();
    }
}