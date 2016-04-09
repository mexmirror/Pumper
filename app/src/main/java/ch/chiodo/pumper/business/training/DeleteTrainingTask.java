package ch.chiodo.pumper.business.training;

import ch.chiodo.pumper.business.BaseAsyncTask;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.model.Training;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class DeleteTrainingTask extends BaseAsyncTask<Training, Void, Training>{

    public DeleteTrainingTask(PumperService service, Callback<Training> callback) {
        super(service, callback);
    }

    @Override
    protected Training doInBackground(Training... params) {
        Training t = params[0];
        return service.deleteTraining(t);
    }
}
