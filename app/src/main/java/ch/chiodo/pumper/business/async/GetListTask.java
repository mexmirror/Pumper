package ch.chiodo.pumper.business.async;

import java.util.List;

import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public abstract class GetListTask<T> extends BaseAsyncTask<Void, Void, List<T>> {

    public GetListTask(PumperService service, Callback<List<T>> callback) {
        super(service, callback);
    }
}
