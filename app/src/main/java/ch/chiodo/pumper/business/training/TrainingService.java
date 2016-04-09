package ch.chiodo.pumper.business.training;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.business.Callback;
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

    public void getTraining(long id, Callback<Training> callback){
        GetTrainingTask task = new GetTrainingTask(service, callback);
        task.execute(id);
    }

    public void insertTraining(Training training, Callback<Training> callback){
        InsertTrainingTask task = new InsertTrainingTask(service, callback);
        task.execute(training);
    }

    public void updateTraining(Training training, Callback<Training> callback){
        UpdateTrainingTask task = new UpdateTrainingTask(service, callback);
        task.execute(training);
    }

    public void deleteTraining(Training training, Callback<Training> callback){
        DeleteTrainingTask task = new DeleteTrainingTask(service, callback);
        task.execute(training);
    }

    public void getTrainingList(Callback<List<Training>> callback){
        GetTrainingListTask task = new GetTrainingListTask(service, callback);
        task.execute();
    }

    public class TrainingBinder extends Binder {
        TrainingService getService(){
            return TrainingService.this;
        }
    }
}
