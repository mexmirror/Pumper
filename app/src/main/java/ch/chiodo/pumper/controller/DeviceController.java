package ch.chiodo.pumper.controller;

import ch.chiodo.pumper.data.Device;
import ch.chiodo.pumper.service.DeviceContainer;

public class DeviceController {
    private DeviceContainer deviceContainer;
    public DeviceController(){
        deviceContainer = DeviceContainer.getDeviceContainer();
    }
    public Device getDeviceById(String id) throws IllegalArgumentException{
        return deviceContainer.getDeviceById(id);
    }
}
