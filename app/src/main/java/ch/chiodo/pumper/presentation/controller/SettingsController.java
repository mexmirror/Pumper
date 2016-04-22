package ch.chiodo.pumper.presentation.controller;

import android.content.Context;

import java.util.List;

import ch.chiodo.pumper.infrastructure.exception.DatabaseOperationException;
import ch.chiodo.pumper.model.Device;
import ch.chiodo.pumper.model.Training;

public class SettingsController extends BaseController {
    public SettingsController(Context context) {
        super(context);
    }

    public Training addTraining(String name) throws DatabaseOperationException {
        Training t = new Training(name);
        t = service.insertTraining(t);
        if(t != null) {
            return t;
        }
        log.error(getClass().toString(), name + " not found in database");
        throw new DatabaseOperationException(name + " could not be saved into database");
    }
    public List<Training> getTrainings(){
        return service.getTrainings();
    }
    public Training getTraining(long id) throws DatabaseOperationException {
        Training t = service.getTraining(id);
        if(t != null){
            return t;
        }
        throw new DatabaseOperationException("Training with " + id + " could not be found in database");
    }
    public Training deleteTraining(Training training) throws DatabaseOperationException {
        Training t = service.deleteTraining(training);
        if(t != null){
            return t;
        }
        throw new DatabaseOperationException(training.getName() + " could not be deleted from database");

    }
    public List<Device> getDevices(){
        return service.getDevices();
    }
    public Device getDevice(long id) throws DatabaseOperationException {
        Device d = service.getDevice(id);
        if(d != null){
            return d;
        }
        throw new DatabaseOperationException("Device with " + id + " could net be found in database");
    }
    public Device addDevice(String deviceName) throws DatabaseOperationException {
        Device d = new Device(deviceName);
        d = service.insertDevice(d);
        if(d != null){
            return d;
        }
        throw new DatabaseOperationException(deviceName + " could not be saved into database");
    }
    public Device deleteDevice(Device device) throws DatabaseOperationException {
        Device d = service.deleteDevice(device);
        if(d != null){
            return d;
        }
        throw new DatabaseOperationException(device.getDeviceName() + " could not be deleted from database");
    }

}
