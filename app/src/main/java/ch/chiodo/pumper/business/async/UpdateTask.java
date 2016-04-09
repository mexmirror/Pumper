package ch.chiodo.pumper.business.async;

import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public abstract class UpdateTask<T> extends BaseAsyncTask<T, Void, T> {
    public UpdateTask(PumperService service, Callback<T> callback) {
        super(service, callback);
    }
}
