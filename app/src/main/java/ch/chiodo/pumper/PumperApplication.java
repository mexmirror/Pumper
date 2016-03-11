package ch.chiodo.pumper;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;

import ch.chiodo.pumper.persistence.PumperDbHelper;
import ch.chiodo.pumper.persistence.PumperService;

public class PumperApplication extends Application{
    private SQLiteOpenHelper dbHelper = new PumperDbHelper(getApplicationContext());
    private PumperService pumperService = new PumperService(dbHelper);

    public PumperService getPumperService(){
        return pumperService;
    }

    public SQLiteOpenHelper getPumperDbHelper(){
        return dbHelper;
    }
}
