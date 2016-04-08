package ch.chiodo.pumper.business;

import android.os.AsyncTask;

import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public abstract class BaseAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result>{
    protected PumperService service;

    public BaseAsyncTask(PumperService service){
        this.service = service;
    }
}
