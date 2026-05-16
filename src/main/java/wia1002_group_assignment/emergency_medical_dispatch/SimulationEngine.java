package wia1002_group_assignment.emergency_medical_dispatch;

import java.util.List;

public class SimulationEngine {
    private final CityGraph cityGraph;
    private final DispatchCenter dispatchCenter;
    private final List<Ambulance> fleet;

    public SimulationEngine(CityGraph cityGraph, DispatchCenter dispatchCenter, List<Ambulance> fleet) {
        this.cityGraph = cityGraph;
        this.dispatchCenter = dispatchCenter;
        this.fleet = fleet;
    }

    // Core logic: Processes pending incidents if ambulances are free
    public void processNextDispatch() {
        if (!dispatchCenter.hasPendingCalls()) return;

        // 1. Find an available ambulance
        Ambulance availableAmbulance = fleet.stream()
                .filter(Ambulance::isAvailable)
                .findFirst()
                .orElse(null);

        if (availableAmbulance == null) {
            System.out.println("[System Alert] All ambulances busy. Calls waiting in queues.");
            return; 
        }

        // 2. Fetch the highest priority call waiting
        EmergencyCall call = dispatchCenter.getNextCall();
        if (call == null) return;

        // 3. Find closest ambulance using the Graph and compute route (Dijkstra)
        // (For simplicity, picking the first free ambulance; for a completely flawless system, 
        // you would loop through ALL free ambulances and find the one with the smallest Dijkstra totalETA).
        CityGraph.DijkstraResult routeResult = cityGraph.findShortestPath(
                availableAmbulance.getCurrentLocation(), 
                call.getLocation()
        );

        if (routeResult != null) {
            availableAmbulance.setAvailable(false);
            System.out.println("\n--- DISPATCHING ---");
            System.out.println("Assigned " + availableAmbulance.getId() + " to " + call.getType());
            System.out.println("Route: " + routeResult.path);
            System.out.println("Estimated Time of Arrival (ETA): " + routeResult.totalETA + " mins");
            
            // Simulating arrival (In a real system, this would be asynchronous or tick-based)
            availableAmbulance.setCurrentLocation(call.getLocation());
        }
    }
    
    public void releaseAmbulance(String ambulanceId) {
        for (Ambulance a : fleet) {
            if (a.getId().equals(ambulanceId)) {
                a.setAvailable(true);
                System.out.println("\n[System Update] " + ambulanceId + " is now free.");
                // Immediately check if someone else needs it
                processNextDispatch();
                break;
            }
        }
    }
}