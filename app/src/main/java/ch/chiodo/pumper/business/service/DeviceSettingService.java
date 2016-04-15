package ch.chiodo.pumper.business.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.business.Callback;
import ch.chiodo.pumper.business.async.DeleteTask;
import ch.chiodo.pumper.business.async.GetListTask;
import ch.chiodo.pumper.business.async.GetTask;
import ch.chiodo.pumper.business.async.InsertTask;
import ch.chiodo.pumper.business.async.UpdateTask;
import ch.chiodo.pumper.model.DeviceSetting;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class DeviceSettingService extends Service implements BusinessService<DeviceSetting>{
    private final IBinder binder = new DeviceSettingBinder();
    private PumperService service;

    @Override
    public IBinder onBind(Intent intent) {
        service = ((PumperApplication)getApplication()).getPumperService();
        return binder;
    }

    @Override
    public void insert(DeviceSetting deviceSetting, Callback<DeviceSetting> callback) {
        InsertTask<DeviceSetting> task = new InsertTask<DeviceSetting>(service, callback) {
            @Override
            protected DeviceSetting doInBackground(DeviceSetting... params) {
                DeviceSetting ds = params[0];
                return service.insertDeviceSetting(ds);
            }
        };
        task.execute(deviceSetting);
    }

    @Override
    public void get(long id, Callback<DeviceSetting> callback) {
        GetTask<DeviceSetting> task = new GetTask<DeviceSetting>(service, callback) {
            @Override
            protected DeviceSetting doInBackground(Long... params) {
                long id = params[0];
                return service.getDeviceSetting(id);
            }
        };
        task.execute(id);
    }

    @Override
    public void getList(Callback<List<DeviceSetting>> callback) {
        GetListTask<DeviceSetting> task = new GetListTask<DeviceSetting>(service, callback) {
            @Override
            protected List<DeviceSetting> doInBackground(Void... params) {
                return service.getDeviceSettings();
            }
        };
        task.execute();
    }

    @Override
    public void update(DeviceSetting deviceSetting, Callback<DeviceSetting> callback) {
        UpdateTask<DeviceSetting> task = new UpdateTask<DeviceSetting>(service, callback) {
            @Override
            protected DeviceSetting doInBackground(DeviceSetting... params) {
                DeviceSetting modified = params[0];
                DeviceSetting original = service.getDeviceSetting(modified.getId());
                return service.modifyDeviceSetting(modified, original);
            }
        };
        task.execute(deviceSetting);
    }

    @Override
    public void delete(DeviceSetting deviceSetting, Callback<DeviceSetting> callback) {
        DeleteTask<DeviceSetting> task = new DeleteTask<DeviceSetting>(service, callback) {
            @Override
            protected DeviceSetting doInBackground(DeviceSetting... params) {
                DeviceSetting ds = params[0];
                return service.deleteDeviceSetting(ds);
            }
        };
        task.execute(deviceSetting);
    }

    private class DeviceSettingBinder extends Binder {
        public DeviceSettingService getService() {
            return DeviceSettingService.this;
        }
    }
}
