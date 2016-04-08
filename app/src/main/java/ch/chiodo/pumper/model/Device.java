package ch.chiodo.pumper.model;

public class Device implements Updateable<Device>{
    private long id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void update(Device other){
        this.deviceName = other.deviceName;
    }
}
