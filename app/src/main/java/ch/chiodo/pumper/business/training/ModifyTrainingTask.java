package ch.chiodo.pumper.business.training;

import ch.chiodo.pumper.business.BaseAsyncTask;
import ch.chiodo.pumper.model.Training;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class ModifyTrainingTask extends BaseAsyncTask<Training, Void, Training>{

    public ModifyTrainingTask(PumperService service) {
        super(service);
    }

    /**
     * Asynchronously modify a training object
     * @param params Vararg with original and modified training. First parameter is the old one, second parameter is the second one
     * @return Updated original training
     */
    @Override
    protected Training doInBackground(Training... params) {
        Training original = params[0];
        Training modified = params[1];
        return service.modifyTraining(original, modified);
    }
}
