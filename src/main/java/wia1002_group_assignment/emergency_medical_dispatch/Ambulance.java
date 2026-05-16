package wia1002_group_assignment.emergency_medical_dispatch;

public class Ambulance {
    private String id;
    private String currentLocation;
    private boolean isAvailable;

    public Ambulance(String id, String currentLocation, boolean isAvailable) {
        this.id = id;
        this.currentLocation = currentLocation;
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}
