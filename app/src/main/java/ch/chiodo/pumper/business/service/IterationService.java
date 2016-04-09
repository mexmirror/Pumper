package ch.chiodo.pumper.business.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.model.Iteration;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class IterationService extends Service implements BusinessService<Iteration>{
    private IBinder binder = new IterationBinder();
    private PumperService service;

    @Override
    public IBinder onBind(Intent intent) {
        service = ((PumperApplication)getApplication()).getPumperService();
        return binder;
    }

    @Override
    public void insert(Iteration iteration, Callback<Iteration> callback) {

    }

    @Override
    public void get(long id, Callback<Iteration> callback) {

    }

    @Override
    public void getList(Callback<List<Iteration>> callback) {

    }

    @Override
    public void update(Iteration iteration, Callback<Iteration> callback) {

    }

    @Override
    public void delete(Iteration iteration, Callback<Iteration> callback) {

    }

    public class IterationBinder extends Binder {
        IterationService getService() {
            return IterationService.this;
        }
    }
}
