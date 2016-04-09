package ch.chiodo.pumper.business.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.business.async.DeleteTask;
import ch.chiodo.pumper.business.async.GetListTask;
import ch.chiodo.pumper.business.async.GetTask;
import ch.chiodo.pumper.business.async.InsertTask;
import ch.chiodo.pumper.business.async.UpdateTask;
import ch.chiodo.pumper.model.Training;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class TrainingService extends Service implements BusinessService<Training> {
    private IBinder binder = new TrainingBinder();
    private PumperService service;

    @Override
    public IBinder onBind(Intent intent) {
        service = ((PumperApplication)getApplication()).getPumperService();
        return binder;
    }

    public void get(long id, Callback<Training> callback){
        GetTask<Training> task = new GetTask<Training>(service, callback) {
            @Override
            protected Training doInBackground(Long... params) {
                long id = params[0];
                return service.getTraining(id);
            }
        };
        task.execute(id);
    }

    public void insert(Training training, Callback<Training> callback){
        InsertTask<Training> task = new InsertTask<Training>(service, callback) {
            @Override
            protected Training doInBackground(Training... params) {
                Training t = params[0];
                return service.insertTraining(t);
            }
        };
        task.execute(training);
    }

    public void update(Training training, Callback<Training> callback){
        UpdateTask<Training> task = new UpdateTask<Training>(service, callback) {
            @Override
            protected Training doInBackground(Training... params) {
                Training modified = params[0];
                Training original = service.getTraining(modified.getId());
                return service.modifyTraining(original, modified);
            }
        };
        task.execute(training);
    }

    public void delete(Training training, Callback<Training> callback){
        DeleteTask<Training> task = new DeleteTask<Training>(service, callback) {
            @Override
            protected Training doInBackground(Training... params) {
                Training t = params[0];
                return service.deleteTraining(t);
            }
        };
        task.execute(training);
    }

    public void getList(Callback<List<Training>> callback){
        GetListTask<Training> task = new GetListTask<Training>(service, callback) {
            @Override
            protected List<Training> doInBackground(Void... params) {
                return service.getTrainings();
            }
        };
        task.execute();
    }

    public class TrainingBinder extends Binder {
        TrainingService getService(){
            return TrainingService.this;
        }
    }
}
