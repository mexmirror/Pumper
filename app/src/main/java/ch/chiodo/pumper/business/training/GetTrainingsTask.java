package ch.chiodo.pumper.business.training;

import java.util.List;

import ch.chiodo.pumper.business.BaseAsyncTask;
import ch.chiodo.pumper.model.Training;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class GetTrainingsTask extends BaseAsyncTask<Void, Void, List<Training>>{


    public GetTrainingsTask(PumperService service) {
        super(service);
    }

    @Override
    protected List<Training> doInBackground(Void... params) {
        return service.getTrainings();
    }
}
