package ch.chiodo.pumper.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ch.chiodo.pumper.data.Device;

public class DeviceContainer implements Serializable {
    public final static String FILENAME = "devices.bin";
    private static DeviceContainer container;
    private List<Device> deviceList;
    private DeviceContainer(){
        deviceList = new ArrayList<>();
    }
    public static DeviceContainer getDeviceContainer(){
        if(container == null){
            container = new DeviceContainer();
        }
        return container;
    }
    public boolean addDevice(Device device){
        return deviceList.add(device);
    }
    public Device getDeviceById(String id)throws IllegalArgumentException{
        for(Device d: deviceList){
            if(d.getId().equals(id)){
                return d;
            }
        }
        throw new IllegalArgumentException("Device not found");
    }
}
