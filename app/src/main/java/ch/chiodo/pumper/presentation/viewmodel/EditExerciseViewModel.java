package ch.chiodo.pumper.presentation.viewmodel;

import android.content.Context;

import java.util.List;

import ch.chiodo.pumper.model.Device;
import ch.chiodo.pumper.model.Exercise;
import ch.chiodo.pumper.model.Training;

public class EditExerciseViewModel extends BaseViewModel{
    private long trainingId;

    public EditExerciseViewModel(Context context, long trainingId) {
        super(context);
        this.trainingId = trainingId;
    }

    public void addExercise(double weight, int repetition, String deviceName){
        Training t = service.getTraining(trainingId);
        List<Device> list = service.getDevices();
        long deviceId = -1;
        for (Device d : list) {
            if(d.getDeviceName().equals(deviceName)){
               deviceId = d.getId();
                break;
            }
        }
        if(deviceId == -1){
            Device d = new Device(deviceName);
            d = service.insertDevice(d);
            deviceId = d.getId();
        }
        Device d = service.getDevice(deviceId);
        Exercise e = new Exercise(t, d, weight, repetition);
        service.insertExercise(e);
    }

}
