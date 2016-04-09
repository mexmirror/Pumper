package ch.chiodo.pumper.business.async;

import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public abstract class DeleteTask<T> extends BaseAsyncTask<T, Void, T> {

    public DeleteTask(PumperService service, Callback<T> callback) {
        super(service, callback);
    }
}
