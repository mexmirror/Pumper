package ch.chiodo.pumper.business.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.List;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.business.async.DeleteTask;
import ch.chiodo.pumper.business.async.GetListTask;
import ch.chiodo.pumper.business.async.GetTask;
import ch.chiodo.pumper.business.async.InsertTask;
import ch.chiodo.pumper.business.async.UpdateTask;
import ch.chiodo.pumper.model.Device;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class DeviceService extends Service implements BusinessService<Device>{
    private IBinder binder = new DeviceBinder();
    private PumperService service;
    @Override
    public IBinder onBind(Intent intent) {
        service = ((PumperApplication)getApplication()).getPumperService();
        return binder;
    }

    @Override
    public void insert(Device device, Callback<Device> callback) {
        InsertTask<Device> task = new InsertTask<Device>(service, callback) {
            @Override
            protected Device doInBackground(Device... params) {
                Device d = params[0];
                return service.insertDevice(d);
            }
        };
        task.execute(device);
    }

    @Override
    public void get(long id, Callback<Device> callback) {
        GetTask<Device> task = new GetTask<Device>(service, callback) {
            @Override
            protected Device doInBackground(Long... params) {
                long id = params[0];
                return service.getDevice(id);
            }
        };
        task.execute(id);
    }

    @Override
    public void getList(Callback<List<Device>> callback) {
        GetListTask<Device> task = new GetListTask<Device>(service, callback) {
            @Override
            protected List<Device> doInBackground(Void... params) {
                return service.getDevices();
            }
        };
        task.execute();
    }

    @Override
    public void update(Device device, Callback<Device> callback) {
        UpdateTask<Device> task = new UpdateTask<Device>(service, callback) {
            @Override
            protected Device doInBackground(Device... params) {
                Device modified = params[0];
                Device original = service.getDevice(modified.getId());
                return service.modifyDevice(modified, original);
            }
        };
        task.execute(device);
    }

    @Override
    public void delete(Device device, Callback<Device> callback) {
        DeleteTask<Device> task = new DeleteTask<Device>(service, callback) {
            @Override
            protected Device doInBackground(Device... params) {
                Device d = params[0];
                return service.deleteDevice(d);
            }
        };
        task.execute(device);
    }

    public class DeviceBinder extends Binder{
        DeviceService getService() {
            return DeviceService.this;
        }
    }
}
