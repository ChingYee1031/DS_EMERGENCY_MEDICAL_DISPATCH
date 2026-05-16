package wia1002_group_assignment.emergency_medical_dispatch;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class DispatchCenter {
    // Priority Queue for urgent calls (Severity 1 & 2)
    private final PriorityQueue<EmergencyCall> urgentPriorityQueue = new PriorityQueue<>();
    
    // Regular FIFO Queue for non-urgent calls (Severity 3)
    private final Queue<EmergencyCall> nonUrgentQueue = new LinkedList<>();

    public void receiveCall(EmergencyCall call) {
        if (call.getSeverity() == 3) {
            nonUrgentQueue.add(call);
            System.out.println("[Queue] Non-urgent call added to FIFO Queue: " + call.getType());
        } else {
            urgentPriorityQueue.add(call);
            System.out.println("[Priority Queue] Urgent call added: " + call.getType() + " (Severity " + call.getSeverity() + ")");
        }
    }

    public EmergencyCall getNextCall() {
        // ALWAYS check Priority Queue first!
        if (!urgentPriorityQueue.isEmpty()) {
            return urgentPriorityQueue.poll();
        }
        // Process regular queue ONLY when priority queue is empty
        if (!nonUrgentQueue.isEmpty()) {
            return nonUrgentQueue.poll();
        }
        return null; // Both queues are empty
    }

    public boolean hasPendingCalls() {
        return !urgentPriorityQueue.isEmpty() || !nonUrgentQueue.isEmpty();
    }
}