package wia1002_group_assignment.emergency_medical_dispatch;

public class EmergencyCall implements Comparable<EmergencyCall> {
    private String id;
    private String type;        // e.g., "Heart attack", "Minor car accident"
    private String location;    // e.g., "Location A"
    private int severity;       // 1 = Highest, 2 = Medium, 3 = Lowest
    private long timestamp;     // To track when it arrived

    // Constructor, Getters, Setters\
    public EmergencyCall(String id, String type, String location, int severity) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.severity = severity;
        this.timestamp = System.currentTimeMillis(); // Capture the time of call creation
    }
    
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public int getSeverity() {
        return severity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(EmergencyCall other) {
        // Natural ordering for standard min-heap priority queue logic:
        // Severity 1 comes before Severity 2, etc.
        return Integer.compare(this.severity, other.severity);
    }
}