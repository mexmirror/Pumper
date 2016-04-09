package ch.chiodo.pumper.business.exercise;

import java.util.List;

import ch.chiodo.pumper.business.BaseAsyncTask;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.model.Exercise;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class GetExerciseListTask extends BaseAsyncTask<Void, Void, List<Exercise>>{

    public GetExerciseListTask(PumperService service, Callback<List<Exercise>> callback) {
        super(service, callback);
    }

    @Override
    protected List<Exercise> doInBackground(Void... params) {
        return service.getExercises();
    }
}
