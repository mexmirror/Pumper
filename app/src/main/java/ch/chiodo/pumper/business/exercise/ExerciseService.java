package ch.chiodo.pumper.business.exercise;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.business.BusinessService;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.business.training.UpdateTrainingTask;
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
        InsertExerciseTask task = new InsertExerciseTask(service, callback);
        task.execute(exercise);
    }

    @Override
    public void get(long id, Callback<Exercise> callback) {
        GetExerciseTask task = new GetExerciseTask(service, callback);
        task.execute(id);
    }

    @Override
    public void getList(Callback<List<Exercise>> callback) {
        GetExerciseListTask task = new GetExerciseListTask(service, callback);
        task.execute();
    }

    @Override
    public void update(Exercise exercise, Callback<Exercise> callback) {
        UpdateExerciseTask task = new UpdateExerciseTask(service, callback);
        task.execute(exercise);
    }

    @Override
    public void delete(Exercise exercise, Callback<Exercise> callback) {
        DeleteExerciseTask task = new DeleteExerciseTask(service, callback);
        task.execute(exercise);
    }

    public class ExerciseBinder extends Binder {
        ExerciseService getService(){
            return ExerciseService.this;
        }
    }
}
