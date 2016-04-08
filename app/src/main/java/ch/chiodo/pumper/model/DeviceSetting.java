package ch.chiodo.pumper.model;

public class DeviceSetting implements Updateable<DeviceSetting>{
    private String settingName;
    private String settingValue;

    public DeviceSetting(String name, String value){
        settingName = name;
        settingValue = value;
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

    @Override
    public void update(DeviceSetting modified) {
        this.settingName = modified.settingName;
        this.settingValue = modified.settingValue;
    }
}
