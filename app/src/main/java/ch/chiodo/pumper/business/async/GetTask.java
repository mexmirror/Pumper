package ch.chiodo.pumper.business.async;

import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public abstract class GetTask<T> extends BaseAsyncTask<Long, Void, T> {

    public GetTask(PumperService service, Callback<T> callback) {
        super(service, callback);
    }
}
