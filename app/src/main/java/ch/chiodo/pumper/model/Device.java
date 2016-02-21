package ch.chiodo.pumper.model;

public class Device {
    private int id;
    private String deviceName;

    public Device(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String id) {
        this.deviceName = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void update(String deviceName, int id){

    }
}
