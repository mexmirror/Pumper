package ch.chiodo.pumper.controller;

import ch.chiodo.pumper.data.Device;
import ch.chiodo.pumper.data.Exercise;
import ch.chiodo.pumper.service.DeviceContainer;

public class ExerciseController {
    private TrainingController trainingController;
    public ExerciseController(){
        trainingController = new TrainingController();
    }
    public Exercise newExercise(String deviceId, double weight, int repetitions){
        DeviceContainer container = DeviceContainer.getDeviceContainer();
        Device d;
        try{
            d = container.getDeviceById(deviceId);
        } catch (IllegalArgumentException e){
             d = new Device(deviceId);
            container.addDevice(d);
        }
        Exercise e = new Exercise(d, weight, repetitions);
        trainingController.addExerciseToTraining(e);
        return e;
    }
}
