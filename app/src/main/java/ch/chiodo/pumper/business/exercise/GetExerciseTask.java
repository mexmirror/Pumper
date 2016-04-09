package ch.chiodo.pumper.business.exercise;

import ch.chiodo.pumper.business.BaseAsyncTask;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.model.Exercise;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class GetExerciseTask extends BaseAsyncTask<Long, Void, Exercise>{

    public GetExerciseTask(PumperService service, Callback<Exercise> callback) {
        super(service, callback);
    }

    @Override
    protected Exercise doInBackground(Long... params) {
        long id = params[0];
        return service.getExercise(id);
    }
}
