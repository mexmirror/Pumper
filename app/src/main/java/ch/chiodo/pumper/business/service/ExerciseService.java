package ch.chiodo.pumper.business.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.business.async.DeleteTask;
import ch.chiodo.pumper.business.async.GetListTask;
import ch.chiodo.pumper.business.async.GetTask;
import ch.chiodo.pumper.business.async.InsertTask;
import ch.chiodo.pumper.business.async.UpdateTask;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.model.Exercise;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class ExerciseService extends Service implements BusinessService<Exercise> {
    private IBinder binder = new ExerciseBinder();
    private PumperService service;

    @Override
    public IBinder onBind(Intent intent) {
        service = ((PumperApplication)getApplication()).getPumperService();
        return binder;
    }

    @Override
    public void insert(Exercise exercise, Callback<Exercise> callback) {
        InsertTask<Exercise> task = new InsertTask<Exercise>(service, callback) {
            @Override
            protected Exercise doInBackground(Exercise... params) {
                Exercise e = params[0];
                return service.insertExercise(e);
            }
        };
        task.execute(exercise);
    }

    @Override
    public void get(long id, Callback<Exercise> callback) {
        GetTask<Exercise> task = new GetTask<Exercise>(service, callback) {
            @Override
            protected Exercise doInBackground(Long... params) {
                long id = params[0];
                return service.getExercise(id);
            }
        };
        task.execute(id);
    }

    @Override
    public void getList(Callback<List<Exercise>> callback) {
        GetListTask<Exercise> task = new GetListTask<Exercise>(service, callback) {
            @Override
            protected List<Exercise> doInBackground(Void... params) {
                return service.getExercises();
            }
        };
        task.execute();
    }

    @Override
    public void update(Exercise exercise, Callback<Exercise> callback) {
        UpdateTask<Exercise> task = new UpdateTask<Exercise>(service, callback) {
            @Override
            protected Exercise doInBackground(Exercise... params) {
                Exercise modified = params[0];
                Exercise original = service.getExercise(modified.getId());
                return service.modifyExercise(modified, original);
            }
        };
        task.execute(exercise);
    }

    @Override
    public void delete(Exercise exercise, Callback<Exercise> callback) {
        DeleteTask<Exercise> task = new DeleteTask<Exercise>(service, callback) {
            @Override
            protected Exercise doInBackground(Exercise... params) {
                Exercise e = params[0];
                return service.deleteExercise(e);
            }
        };
        task.execute(exercise);
    }

    public class ExerciseBinder extends Binder {
        ExerciseService getService(){
            return ExerciseService.this;
        }
    }
}
