package ch.chiodo.pumper.persistence.dataaccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ch.chiodo.pumper.persistence.dbcontract.DeviceContract;
import ch.chiodo.pumper.persistence.dbcontract.DeviceSettingContract;
import ch.chiodo.pumper.persistence.dbcontract.ExecutionContract;
import ch.chiodo.pumper.persistence.dbcontract.ExerciseContract;
import ch.chiodo.pumper.persistence.dbcontract.IterationContract;
import ch.chiodo.pumper.persistence.dbcontract.TrainingContract;

public class PumperDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Pumper";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    public static final String PRIMARY_KEY_AUTOINCREMENT = " PRIMARY KEY AUTOINCREMENT";

    private static final String SQL_CREATE_DEVICE_ENTRIES =
            "CREATE TABLE " + DeviceContract.Device.TABLE_NAME + "("
                    + DeviceContract.Device._ID + INTEGER_TYPE + PRIMARY_KEY_AUTOINCREMENT + COMMA_SEP
                    + DeviceContract.Device.COLUMN_NAME_DEVICE_NAME + TEXT_TYPE
                    + " )";
    private static final String SQL_CREATE_EXECUTION_ENTRIES =
            "CREATE TABLE " + ExecutionContract.Execution.TABLE_NAME + "("
                    + ExecutionContract.Execution._ID + INTEGER_TYPE + PRIMARY_KEY_AUTOINCREMENT + COMMA_SEP
                    + ExecutionContract.Execution.COLUMN_NAME_DATE  + TEXT_TYPE + COMMA_SEP
                    + ExecutionContract.Execution.COLUMN_NAME_TRAINING + INTEGER_TYPE + COMMA_SEP
                    + " FOREIGN KEY(" + ExecutionContract.Execution.COLUMN_NAME_TRAINING + ") REFERENCES " + TrainingContract.Training.TABLE_NAME
                    + ")";
    private static final String SQL_CREATE_EXERCISE_ENTRIES =
            "CREATE TABLE " + ExerciseContract.Exercise.TABLE_NAME + "("
                    + ExerciseContract.Exercise._ID + INTEGER_TYPE + PRIMARY_KEY_AUTOINCREMENT + COMMA_SEP
                    + ExerciseContract.Exercise.COLUMN_NAME_WEIGHT + REAL_TYPE + COMMA_SEP
                    + ExerciseContract.Exercise.COLUMN_NAME_REPETITION + INTEGER_TYPE + COMMA_SEP
                    + ExerciseContract.Exercise.COLUMN_NAME_DEVICE + INTEGER_TYPE + COMMA_SEP
                    + ExerciseContract.Exercise.COLUMN_NAME_TRAINING + INTEGER_TYPE + COMMA_SEP
                    + "FOREIGN KEY(" + ExerciseContract.Exercise.COLUMN_NAME_DEVICE +") REFERENCES " + DeviceContract.Device.TABLE_NAME + COMMA_SEP
                    + "FOREIGN KEY(" + ExerciseContract.Exercise.COLUMN_NAME_TRAINING + ") REFERENCES " + TrainingContract.Training.TABLE_NAME
                    + ")";
    private static final String SQL_CREATE_ITERATION_ENTRIES =
            "CREATE TABLE " + IterationContract.Iteration.TABLE_NAME + "("
                    + IterationContract.Iteration._ID + INTEGER_TYPE + PRIMARY_KEY_AUTOINCREMENT + COMMA_SEP
                    + IterationContract.Iteration.COLUMN_NAME_WEIGHT + REAL_TYPE + COMMA_SEP
                    + IterationContract.Iteration.COLUMN_NAME_REPETITION + INTEGER_TYPE + COMMA_SEP
                    + IterationContract.Iteration.COLUMN_NAME_EXECUTION + INTEGER_TYPE + COMMA_SEP
                    + IterationContract.Iteration.COLUMN_NAME_EXERCISE + INTEGER_TYPE + COMMA_SEP
                    + "FOREIGN KEY(" + IterationContract.Iteration.COLUMN_NAME_EXECUTION + ") REFERENCES " + ExecutionContract.Execution.TABLE_NAME + COMMA_SEP
                    + "FOREIGN KEY(" + IterationContract.Iteration.COLUMN_NAME_EXERCISE + ") REFERENCES " + ExerciseContract.Exercise.TABLE_NAME
                    +")";
    public static final String SQL_CREATE_TRAINING_ENTRIES =
            "CREATE TABLE " + TrainingContract.Training.TABLE_NAME + "("
                    + TrainingContract.Training._ID + INTEGER_TYPE + PRIMARY_KEY_AUTOINCREMENT + COMMA_SEP
                    + TrainingContract.Training.COLUMN_NAME_NAME + TEXT_TYPE
                    + ")";
    private static final String SQL_CREATE_DEVICESETTING_ENTRIES =
            "CREATE TABLE " + DeviceSettingContract.DeviceSetting.TABLE_NAME + "("
            + DeviceSettingContract.DeviceSetting._ID + INTEGER_TYPE + PRIMARY_KEY_AUTOINCREMENT + COMMA_SEP
            + DeviceSettingContract.DeviceSetting.COLUMN_NAME_DEVICE + INTEGER_TYPE + COMMA_SEP
            + DeviceSettingContract.DeviceSetting.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP
            + DeviceSettingContract.DeviceSetting.COLUMN_NAME_VALUE + TEXT_TYPE + COMMA_SEP
            + "FOREIGN KEY(" + DeviceSettingContract.DeviceSetting.COLUMN_NAME_DEVICE + ") REFERENCES " + DeviceContract.Device.TABLE_NAME
            + ")";
    private static final String SQL_DELETE_EXECUTION_ENTRIES =
            "DROP TABLE IF EXISTS " + ExecutionContract.Execution.TABLE_NAME;
    private static final String SQL_DELETE_DEVICE_ENTRIES =
            "DROP TABLE IF EXISTS " + DeviceContract.Device.TABLE_NAME;
    private static final String SQL_DELETE_ITERATION_ENTRIES =
            "DROP TABLE IF EXISTS " + IterationContract.Iteration.TABLE_NAME;
    public static final String SQL_DELETE_TRAINING_ENTRIES =
            "DROP TABLE IF EXISTS " + TrainingContract.Training.TABLE_NAME;
    public static final String SQL_DELETE_EXERCISE_ENTRIES =
            "DROP TABLE IF EXISTS " + ExerciseContract.Exercise.TABLE_NAME;
    public static final String SQL_DELETE_DEVICESETTINGS_ENTRIES =
            "DROP TABLE IF EXISTS " + DeviceSettingContract.DeviceSetting.TABLE_NAME;

    public PumperDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TRAINING_ENTRIES);
        db.execSQL(SQL_CREATE_DEVICE_ENTRIES);
        db.execSQL(SQL_CREATE_EXECUTION_ENTRIES);
        db.execSQL(SQL_CREATE_EXERCISE_ENTRIES);
        db.execSQL(SQL_CREATE_ITERATION_ENTRIES);
        db.execSQL(SQL_CREATE_DEVICESETTING_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TRAINING_ENTRIES);
        db.execSQL(SQL_DELETE_DEVICE_ENTRIES);
        db.execSQL(SQL_DELETE_EXECUTION_ENTRIES);
        db.execSQL(SQL_DELETE_EXERCISE_ENTRIES);
        db.execSQL(SQL_DELETE_ITERATION_ENTRIES);
        db.execSQL(SQL_DELETE_DEVICESETTINGS_ENTRIES);
        onCreate(db);
    }
}
