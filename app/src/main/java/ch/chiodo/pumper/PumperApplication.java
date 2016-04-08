package ch.chiodo.pumper;

import android.app.Application;
import android.database.sqlite.SQLiteOpenHelper;

import ch.chiodo.pumper.persistence.dataaccess.PumperDbHelper;
import ch.chiodo.pumper.persistence.dataaccess.PumperServiceSqlite;

public class PumperApplication extends Application{
    private SQLiteOpenHelper dbHelper;
    private PumperServiceSqlite pumperService;

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new PumperDbHelper(getApplicationContext());
        pumperService  = new PumperServiceSqlite(dbHelper);

    }

    public PumperServiceSqlite getPumperService(){
        return pumperService;
    }

    public SQLiteOpenHelper getPumperDbHelper(){
        return dbHelper;
    }
}

