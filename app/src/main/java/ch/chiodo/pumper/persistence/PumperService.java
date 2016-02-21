package ch.chiodo.pumper.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ch.chiodo.pumper.model.Device;
import ch.chiodo.pumper.model.Execution;
import ch.chiodo.pumper.model.Exercise;
import ch.chiodo.pumper.model.Iteration;
import ch.chiodo.pumper.model.Training;

public class PumperService implements IPumperServce{
    private SQLiteOpenHelper dbHelper;

    public PumperService(SQLiteOpenHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    @Override
    public List<Training> getTrainings() {
        List<Training> trainingList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + TrainingContract.Training.TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do{
                Training t = new Training();
                t.setId(c.getLong(c.getColumnIndex(TrainingContract.Training._ID)));
                trainingList.add(t);
            }while(c.moveToNext());
        }
        return trainingList;
    }

    @Override
    public Training getTraining(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {TrainingContract.Training._ID};
        String selection = TrainingContract.Training._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor c = db.query(TrainingContract.Training.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
                );
        if(c != null){
            c.moveToFirst();
        }
        Training t = new Training();
        t.setId(c.getLong(c.getColumnIndex(TrainingContract.Training._ID)));
        return t;
    }

    @Override
    public Training insertTraining(Training training) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        long rowId = db.insert(TrainingContract.Training.TABLE_NAME, null, values);
        training.setId(rowId);
        return training;
    }

    @Override
    public Training modifyTraining(Training modified, Training original) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(TrainingContract.Training._ID, modified.getId());
        String where = TrainingContract.Training._ID + "=?";
        String[] whereArgs = {String.valueOf(original.getId())};
        int affectedRows = db.update(TrainingContract.Training.TABLE_NAME, values, where, whereArgs);
        if(affectedRows == 1){
            original.update(modified.getId());
            return original;
        }
        return null;
    }

    @Override
    public Training deleteTraining(Training training) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = TrainingContract.Training._ID + "=?";
        String[] selectionArgs = { String.valueOf(training.getId()) };
        int affectedRows = db.delete(TrainingContract.Training.TABLE_NAME, selection, selectionArgs);
        if(affectedRows == 1){
            return training;
        }
        return null;
    }

    @Override
    public List<Device> getDevices() {
        List<Device> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DeviceContract.Device.TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do{
                String deviceName = c.getString(c.getColumnIndex(DeviceContract.Device.COLUMN_NAME_DEVICE_ID));
                int id = c.getInt(c.getColumnIndex(DeviceContract.Device._ID));
                Device d = new Device(deviceName);
                d.setId(id);
                list.add(d);
            }while(c.moveToNext());
        }
        return list;
    }

    @Override
    public Device getDevice(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {DeviceContract.Device.COLUMN_NAME_DEVICE_ID};
        String selection = DeviceContract.Device._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor c = db.query(DeviceContract.Device.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null);
        if(c!=null){
            c.moveToFirst();
        }
        Device d = new Device(c.getString(c.getColumnIndex(DeviceContract.Device.COLUMN_NAME_DEVICE_ID)));
        d.setId(c.getInt(c.getColumnIndex(DeviceContract.Device._ID)));
        return d;
    }

    @Override
    public Device insertDevice(Device device) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DeviceContract.Device.COLUMN_NAME_DEVICE_ID, device.getDeviceName());
        long rowId = db.insert(DeviceContract.Device.TABLE_NAME, null, values);
        if(rowId != -1){
            return device;
        }
        return null;
    }

    @Override
    public Device modifyDevice(Device modified, Device original) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DeviceContract.Device.COLUMN_NAME_DEVICE_ID, modified.getDeviceName());
        String where = DeviceContract.Device._ID + "=?";
        String[] whereArgs = { String.valueOf(original.getId()) };
        int affectedRows = db.update(DeviceContract.Device.TABLE_NAME, values, where, whereArgs);
        if(affectedRows == 1){
            original.update(modified.getDeviceName(), modified.getId());
            return original;
        }
        return null;
    }

    @Override
    public Device deleteDevice(Device device) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = DeviceContract.Device._ID + "=?";
        String[] selectionArgs = {String.valueOf(device.getId())};
        int affectedRows = db.delete(DeviceContract.Device.TABLE_NAME, selection, selectionArgs);
        if(affectedRows == 1){
            return device;
        }
        return null;
    }

    @Override
    public List<Exercise> getExercises() {
        List<Exercise> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + ExerciseContract.Exercise.TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do{
                int id = c.getInt(c.getColumnIndex(ExerciseContract.Exercise._ID));
                double weight = c.getDouble(c.getColumnIndex(ExerciseContract.Exercise.COLUMN_NAME_WEIGHT));
                int repetition = c.getInt(c.getColumnIndex(ExerciseContract.Exercise.COLUMN_NAME_REPETITION));
                Training t = getTraining(c.getInt(c.getColumnIndex(ExerciseContract.Exercise.COLUMN_NAME_TRAINING)));
                Device d = getDevice(c.getInt(c.getColumnIndex(ExerciseContract.Exercise.COLUMN_NAME_DEVICE)));
                Exercise e = new Exercise(t, d, weight, repetition);
                e.setId(id);
                list.add(e);
            }while(c.moveToNext());
        }
        return list;
    }

    @Override
    public Exercise getExercise(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                ExerciseContract.Exercise._ID,
                ExerciseContract.Exercise.COLUMN_NAME_WEIGHT,
                ExerciseContract.Exercise.COLUMN_NAME_REPETITION,
                ExerciseContract.Exercise.COLUMN_NAME_TRAINING,
                ExerciseContract.Exercise.COLUMN_NAME_DEVICE
        };
        String selection = ExerciseContract.Exercise._ID + "=?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor c = db.query(
                ExerciseContract.Exercise.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
        );
        if(c!=null){
            c.moveToFirst();
        }
        int foundId = c.getInt(c.getColumnIndex(ExerciseContract.Exercise._ID));
        double weight = c.getDouble(c.getColumnIndex(ExerciseContract.Exercise.COLUMN_NAME_WEIGHT));
        int repetition = c.getInt(c.getColumnIndex(ExerciseContract.Exercise.COLUMN_NAME_REPETITION));
        Training t = getTraining(c.getInt(c.getColumnIndex(ExerciseContract.Exercise.COLUMN_NAME_TRAINING)));
        Device d = getDevice(c.getInt(c.getColumnIndex(ExerciseContract.Exercise.COLUMN_NAME_DEVICE)));
        Exercise e = new Exercise(t, d, weight, repetition);
        e.setId(foundId);
        return e;
    }

    @Override
    public Exercise insertExercise(Exercise exercise) {
        return null;
    }

    @Override
    public Exercise modifyExercise(Exercise modified, Exercise original) {
        return null;
    }

    @Override
    public Exercise deleteExercise(Exercise exercise) {
        return null;
    }

    @Override
    public List<Execution> getExecutions() {
        return null;
    }

    @Override
    public Execution getExecution(int id) {
        return null;
    }

    @Override
    public Execution insertExecution(Execution execution) {
        return null;
    }

    @Override
    public Execution modifyExecution(Execution modified, Execution original) {
        return null;
    }

    @Override
    public Execution deleteExecution(Execution execution) {
        return null;
    }

    @Override
    public List<Iteration> getIterations() {
        return null;
    }

    @Override
    public Iteration getIteration(int id) {
        return null;
    }

    @Override
    public Iteration insertIteration(Iteration iteration) {
        return null;
    }

    @Override
    public Iteration modifyIteration(Iteration modified, Iteration original) {
        return null;
    }

    @Override
    public Iteration deleteIteration(Iteration iteration) {
        return null;
    }
}
