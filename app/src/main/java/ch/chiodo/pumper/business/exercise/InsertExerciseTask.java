package ch.chiodo.pumper.business.exercise;

import ch.chiodo.pumper.business.BaseAsyncTask;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.model.Exercise;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class InsertExerciseTask extends BaseAsyncTask<Exercise, Void, Exercise>{

    public InsertExerciseTask(PumperService service, Callback<Exercise> callback) {
        super(service, callback);
    }

    @Override
    protected Exercise doInBackground(Exercise... params) {
        Exercise e = params[0];
        return service.insertExercise(e);
    }
}
