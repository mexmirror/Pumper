package ch.chiodo.pumper.business.training;

import java.util.List;

import ch.chiodo.pumper.business.BaseAsyncTask;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.model.Training;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class GetTrainingListTask extends BaseAsyncTask<Void, Void, List<Training>>{


    public GetTrainingListTask(PumperService service, Callback<List<Training>> callback) {
        super(service, callback);
    }

    @Override
    protected List<Training> doInBackground(Void... params) {
        return service.getTrainings();
    }
}
