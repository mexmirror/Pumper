package ch.chiodo.pumper.business.training;

import ch.chiodo.pumper.business.BaseAsyncTask;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.model.Training;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class UpdateTrainingTask extends BaseAsyncTask<Training, Void, Training>{

    public UpdateTrainingTask(PumperService service, Callback<Training> callback) {
        super(service, callback);
    }

    @Override
    protected Training doInBackground(Training... params) {
        Training modified = params[0];
        Training original = service.getTraining(modified.getId());
        return service.modifyTraining(original, modified);
    }
}
