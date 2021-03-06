package ch.chiodo.pumper.business.async;

import android.os.AsyncTask;

import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public abstract class BaseAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result>{
    protected PumperService service;
    protected Callback<Result> callback;

    public BaseAsyncTask(PumperService service, Callback<Result> callback){
        this.service = service;
        this.callback = callback;
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        if(!isCancelled()) {
            try {
                callback.onSuccess(result);
            } catch (Exception e) {
                callback.onError(e);
            }
        }
    }
}
