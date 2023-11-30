import java.util.*;

/**
 * Provide a simple demonstration of running a stage-one
 * scenario. A single passenger and taxi are created, and a pickup
 * requested. As the simulation is run, the passenger
 * should be picked up and then taken to their destination.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 * @version 2023 DP Classes
 */
public class DemoOnePassanger
{
    TransportCompany company;
    private List<Taxi> actors;

    /**
     * Constructor for objects of class DemoOnePassanger
     */
    public DemoOnePassanger()
    {
        company = new TransportCompany("Compañía Taxis Cáceres");
        actors = new ArrayList<>();
        reset();
    }

    /**
     * Run the demo for a fixed number of steps.
     */
    public void run()
    {        
        //Ejecutamos un número de pasos la simulación.
        //En cada paso, cada taxi realiza su acción
        for(int step = 0; step < 100; step++) {
            step();
        }
        showFinalInfo();
    }

    /**
     * Run the demo for one step by requesting
     * all actors to act.
     */
    public void step()
    {
        for(Taxi actor : actors) {
            actor.act();
        }
    }

    /**
     * Reset the demo to a starting point.
     * A single taxi and passenger are created, and a pickup is
     * requested for a single passenger.
     * @throws IllegalStateException If a pickup cannot be found
     */
    public void reset()
    {
        actors.clear();

        createTaxis();
        createPassengers(); 
        showInicialInfo();
        runSimulation();
    }

    /**
     * Taxis are created and added to the company
     */
    private void createTaxis() {
        //Taxi taxi = new Taxi(company, new Location(10, 10),"T1");
        //company.addVehicle(taxi);
        actors.addAll(company.getVehicles());
    }

    /**
     * Passengers are created and added to the company
     */
    private void createPassengers() {
       // Passenger passenger = new Passenger(new Location(6, 6),
             //   new Location(5,2),"Lucy");
        //company.addPassenger(passenger);
    }

    /**
     * A pickup is requested for a single passenger.
     * @throws IllegalStateException If a pickup cannot be found
     */
    private void runSimulation() {
        List<Passenger> passengers = company.getPassengers();
        for(Passenger passenger : passengers) {
            if(!company.requestPickup(passenger)) {
                throw new IllegalStateException("Failed to find a pickup.");        
            }
            else{
                //System.out.println("<<<< Taxi: " + company.getAssignments().get());
            }
        }

    }

    /**
     * Initial info is showed with the information about taxis and passengers
     */
    private void showInicialInfo() {

        System.out.println("--->> Simulation of the company: "+company.getName()+" <<---");
        System.out.println("-->> Taxis of the company <<--");
        Collections.sort(actors, new ComparadorNombreTaxi());
        System.out.println("Taxi " + actors.get(0).getName() + " at " + actors.get(0).getLocation().toString());

        System.out.println("-->> Passengers requesting taxi <<--");
        
        Collections.sort(company.getPassengers(), new ComparadorNombrePassenger());
        System.out.println("Passenger " + company.getPassengers().get(0).getName() + " travelling from " + 
            company.getPassengers().get(0).getPickup().toString() + " to " + company.getPassengers().get(0).getDestination().toString());
            
        System.out.println("-->> ---------------- <<--");
        System.out.println("-->> Simulation start <<--");
        System.out.println("-->> ---------------- <<--");
        System.out.println("");
        
    }

    /**
     * Final info is showed with the information about taxis and passengers
     */
    private void showFinalInfo() {

        System.out.println("");
        System.out.println("-->> ----------------- <<--");
        System.out.println("-->> End of simulation <<--");        
        System.out.println("-->> ----------------- <<--");
        System.out.println("");

        System.out.println("-->> Taxis final information <<--");
        Collections.sort(actors, new ComparadorNumPasajeros());
        System.out.println(actors.get(0).showFinalInfo());

        System.out.println("-->> Passengers final information <<--");
        Collections.sort(company.getPassengers(), new ComparadorNombrePassenger());
        company.getPassengers().get(0).showFinalInfo();

    }
}