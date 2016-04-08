package ch.chiodo.pumper.business.training;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.model.Training;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class TrainingService extends Service{
    private IBinder binder = new TrainingBinder();
    private PumperService service;

    @Override
    public IBinder onBind(Intent intent) {
        service = ((PumperApplication)getApplication()).getPumperService();
        return binder;
    }

    public Training getTraining(long id){
        return new GetTrainingTask(service).doInBackground(id);
    }

    public class TrainingBinder extends Binder {
        TrainingService getService(){
            return TrainingService.this;
        }
    }
}
