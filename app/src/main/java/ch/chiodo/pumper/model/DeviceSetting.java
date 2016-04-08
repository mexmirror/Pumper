package ch.chiodo.pumper.model;

public class DeviceSetting implements Updateable<DeviceSetting>{
    private long id;
    private Device device;
    private String settingName;
    private String settingValue;

    public DeviceSetting(){}

    public DeviceSetting(Device device, String name, String value){
        settingName = name;
        settingValue = value;
        this.device = device;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public void update(DeviceSetting modified) {
        this.device = modified.device;
        this.settingName = modified.settingName;
        this.settingValue = modified.settingValue;
    }
}
