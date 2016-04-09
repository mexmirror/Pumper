package ch.chiodo.pumper.business.training;

import ch.chiodo.pumper.business.BaseAsyncTask;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.model.Execution;
import ch.chiodo.pumper.model.Training;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class GetTrainingTask extends BaseAsyncTask<Long, Void, Training> {

    public GetTrainingTask(PumperService service, Callback<Training> callback){
        super(service, callback);
    }

    @Override
    protected Training doInBackground(Long... params) {
        long id = params[0];
        return service.getTraining(id);
    }
}
