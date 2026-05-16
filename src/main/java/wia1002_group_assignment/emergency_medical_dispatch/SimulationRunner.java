package wia1002_group_assignment.emergency_medical_dispatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class SimulationRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n========== STARTING EMERGENCY MEDICAL DISPATCH SIMULATION ==========\n");

        // 1. Initialize the City Graph (Nodes, Edges, Weights) [cite: 21, 25]
        CityGraph cityGraph = new CityGraph();
        cityGraph.addRoad("Base Station", "Location A", 5.0);
        cityGraph.addRoad("Base Station", "Location B", 12.0);
        cityGraph.addRoad("Location A", "Location B", 6.0);
        cityGraph.addRoad("Location A", "Location C", 4.0);
        cityGraph.addRoad("Location B", "Location C", 8.0);

        // 2. Initialize the Dispatch Center (Queues) [cite: 26, 27]
        DispatchCenter dispatchCenter = new DispatchCenter();

        // 3. Initialize Ambulance Fleet (Assume 2 ambulances initially parked at Base Station) 
        List<Ambulance> fleet = new ArrayList<>();
        fleet.add(new Ambulance("Ambulance-01", "Base Station", true));
        fleet.add(new Ambulance("Ambulance-02", "Base Station", true));

        // 4. Initialize Simulation Engine [cite: 28]
        SimulationEngine engine = new SimulationEngine(cityGraph, dispatchCenter, fleet);

        // ====================================================================
        // THE ASSIGNMENT SCENARIO [cite: 10]
        // ====================================================================
        
        System.out.println("--- PHASE 1: Incoming Emergency Calls ---");
        // Call 1: Heart attack (Severity 1) at Location A 
        EmergencyCall call1 = new EmergencyCall("C01", "Heart Attack", "Location A", 1);
        
        // Call 2: Minor car accident (Severity 3) at Location B 
        EmergencyCall call2 = new EmergencyCall("C02", "Minor Car Accident", "Location B", 3);
        
        // Call 3: House fire with burns (Severity 2) at Location C 
        EmergencyCall call3 = new EmergencyCall("C03", "House Fire with Burns", "Location C", 2);

        // Simulating rapid succession ingestion [cite: 10]
        dispatchCenter.receiveCall(call1);
        dispatchCenter.receiveCall(call2);
        dispatchCenter.receiveCall(call3);

        System.out.println("\n--- PHASE 2: Processing First Dispatches ---");
        // This will process the highest priority call first (Heart attack), then the next (House fire) 
        engine.processNextDispatch(); // Dispatches Ambulance 1 to Heart Attack
        engine.processNextDispatch(); // Dispatches Ambulance 2 to House Fire

        System.out.println("\n--- PHASE 3: Attempting Dispatch When Fleet is Full ---");
        // Both ambulances are now busy. The Car Accident (Severity 3) should remain safely in the queue.
        engine.processNextDispatch(); 

        System.out.println("\n--- PHASE 4: Simulating Ambulance Resource Recovery ---");
        // Ambulance 1 finishes its job at Location A and becomes free [cite: 18]
        engine.releaseAmbulance("Ambulance-01"); 

        System.out.println("\n========== SIMULATION COMPLETED ==========");
    }
}