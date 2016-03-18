package ch.chiodo.pumper;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;

import ch.chiodo.pumper.persistence.PumperDbHelper;
import ch.chiodo.pumper.persistence.PumperService;
import ch.chiodo.pumper.presentation.model.Training;

public class PumperApplication extends Application{
    private SQLiteOpenHelper dbHelper;
    private PumperService pumperService;

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new PumperDbHelper(getApplicationContext());
        pumperService  = new PumperService(dbHelper);

    }

    public PumperService getPumperService(){
        return pumperService;
    }

    public SQLiteOpenHelper getPumperDbHelper(){
        return dbHelper;
    }
}

