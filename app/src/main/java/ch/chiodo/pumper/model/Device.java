package ch.chiodo.pumper.model;

public class Device {
    private String id;

    public Device(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        return id.equals(device.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
