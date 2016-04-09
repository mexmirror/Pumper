package ch.chiodo.pumper.business.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.ParseException;
import java.util.List;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.business.async.DeleteTask;
import ch.chiodo.pumper.business.async.GetListTask;
import ch.chiodo.pumper.business.async.GetTask;
import ch.chiodo.pumper.business.async.InsertTask;
import ch.chiodo.pumper.business.async.UpdateTask;
import ch.chiodo.pumper.model.Execution;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class ExecutionService extends Service implements BusinessService<Execution> {
    private IBinder binder = new ExecutionBinder();
    private PumperService service;

    @Override
    public IBinder onBind(Intent intent) {
        service = ((PumperApplication)getApplication()).getPumperService();
        return binder;
    }

    @Override
    public void insert(Execution execution, Callback<Execution> callback) {
        InsertTask<Execution> task = new InsertTask<Execution>(service, callback) {
            @Override
            protected Execution doInBackground(Execution... params) {
                Execution e = params[0];
                return service.insertExecution(e);
            }
        };
        task.execute(execution);
    }

    @Override
    public void get(long id, Callback<Execution> callback) {
        GetTask<Execution> task = new GetTask<Execution>(service, callback) {
            @Override
            protected Execution doInBackground(Long... params) {
                long id = params[0];
                try {
                    return service.getExecution(id);
                } catch (ParseException e){
                    this.cancel(true);
                    callback.onError(e);
                }
                return null;
            }
        };
        task.execute(id);
    }

    @Override
    public void getList(Callback<List<Execution>> callback) {
        GetListTask<Execution> task = new GetListTask<Execution>(service, callback) {
            @Override
            protected List<Execution> doInBackground(Void... params) {
                try {
                    return service.getExecutions();
                } catch (ParseException e){
                    this.cancel(true);
                    callback.onError(e);
                }
                return null;
            }
        };
        task.execute();
    }

    @Override
    public void update(Execution execution, Callback<Execution> callback) {
        UpdateTask<Execution> task = new UpdateTask<Execution>(service, callback) {
            @Override
            protected Execution doInBackground(Execution... params) {
                Execution modified = params[0];
                try {
                    Execution original = service.getExecution(modified.getId());
                    service.modifyExecution(modified, original);
                } catch (ParseException e) {
                    this.cancel(true);
                    callback.onError(e);
                }
                return null;
            }
        };
        task.execute(execution);
    }

    @Override
    public void delete(Execution execution, Callback<Execution> callback) {
        DeleteTask<Execution> task = new DeleteTask<Execution>(service, callback) {
            @Override
            protected Execution doInBackground(Execution... params) {
                Execution e = params[0];
                return service.deleteExecution(e);
            }
        };
        task.execute(execution);
    }

    public class ExecutionBinder extends Binder {
        ExecutionService getService() {
            return ExecutionService.this;
        }
    }
}
