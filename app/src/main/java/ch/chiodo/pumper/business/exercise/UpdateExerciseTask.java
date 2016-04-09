package ch.chiodo.pumper.business.exercise;

import ch.chiodo.pumper.business.BaseAsyncTask;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.model.Exercise;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class UpdateExerciseTask extends BaseAsyncTask<Exercise, Void, Exercise> {

    public UpdateExerciseTask(PumperService service, Callback<Exercise> callback) {
        super(service, callback);
    }

    @Override
    protected Exercise doInBackground(Exercise... params) {
        Exercise modified = params[0];
        Exercise original = service.getExercise(modified.getId());
        return service.modifyExercise(modified, original);
    }
}
