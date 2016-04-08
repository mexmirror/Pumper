package ch.chiodo.pumper.business.training;

import ch.chiodo.pumper.business.BaseAsyncTask;
import ch.chiodo.pumper.model.Training;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class GetTrainingTask extends BaseAsyncTask<Long, Void, Training> {

    public GetTrainingTask(PumperService service){
        super(service);
    }

    @Override
    protected Training doInBackground(Long... params) {
        long id = params[0];
        return service.getTraining(id);
    }
}
