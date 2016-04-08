package ch.chiodo.pumper.business.training;

import ch.chiodo.pumper.business.BaseAsyncTask;
import ch.chiodo.pumper.model.Training;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class InsertTrainingTask extends BaseAsyncTask<Training, Void, Training>{

    public InsertTrainingTask(PumperService service) {
        super(service);
    }

    @Override
    protected Training doInBackground(Training... params) {
        Training t = params[0];
        return service.insertTraining(t);
    }
}
