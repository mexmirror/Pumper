package ch.chiodo.pumper.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.chiodo.pumper.model.Device;
import ch.chiodo.pumper.model.Execution;
import ch.chiodo.pumper.model.Exercise;
import ch.chiodo.pumper.model.Iteration;
import ch.chiodo.pumper.model.Training;

public class PumperService implements IPumperServce{
    public static final int sqlInsertError = -1;
    private SQLiteOpenHelper dbHelper;
    private final static String iso8601DateFormat = "YYYY-MM-DD HH:MM:SS.SSS";

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
        if(rowId != sqlInsertError){
            training.setId(rowId);
            return training;
        }
        return null;
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
        if(rowId != sqlInsertError){
            device.setId(rowId);
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
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ExerciseContract.Exercise.COLUMN_NAME_WEIGHT, exercise.getWeight());
        values.put(ExerciseContract.Exercise.COLUMN_NAME_REPETITION, exercise.getRepetition());
        values.put(ExerciseContract.Exercise.COLUMN_NAME_DEVICE, exercise.getDevice().getId());
        values.put(ExerciseContract.Exercise.COLUMN_NAME_TRAINING, exercise.getTraining().getId());
        long rowId = db.insert(ExerciseContract.Exercise.TABLE_NAME, null, values);
        if(rowId != sqlInsertError){
            exercise.setId(rowId);
            return exercise;
        }
        return null;
    }

    @Override
    public Exercise modifyExercise(Exercise modified, Exercise original) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ExerciseContract.Exercise.COLUMN_NAME_WEIGHT, modified.getWeight());
        values.put(ExerciseContract.Exercise.COLUMN_NAME_REPETITION, modified.getRepetition());
        values.put(ExerciseContract.Exercise.COLUMN_NAME_DEVICE, modified.getDevice().getId());
        values.put(ExerciseContract.Exercise.COLUMN_NAME_TRAINING, modified.getTraining().getId());
        String where = ExerciseContract.Exercise._ID + "=?";
        String[] whereArgs = { String.valueOf(original.getId()) };
        int affectedRows = db.update(ExerciseContract.Exercise.TABLE_NAME, values, where, whereArgs);
        if(affectedRows == 1){
            original.update(modified.getId(),
                    modified.getTraining(),
                    modified.getDevice(),
                    modified.getWeight(),
                    modified.getRepetition());
            return original;
        }
        return null;
    }

    @Override
    public Exercise deleteExercise(Exercise exercise) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = ExerciseContract.Exercise._ID + "=?";
        String[] selectionArgs = { String.valueOf(exercise.getId()) };
        int affectedRows = db.delete(ExerciseContract.Exercise.TABLE_NAME, selection, selectionArgs);
        if(affectedRows == 1){
            return exercise;
        }
        return null;
    }

    @Override
    public List<Execution> getExecutions() throws ParseException {
        List<Execution> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + ExecutionContract.Execution.TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do{
                int id = c.getInt(c.getColumnIndex(ExecutionContract.Execution._ID));
                Training t = getTraining(c.getInt(c.getColumnIndex(ExecutionContract.Execution.COLUMN_NAME_TRAINING)));
                String dateString = c.getString(c.getColumnIndex(ExecutionContract.Execution.COLUMN_NAME_DATE));
                DateFormat format = new SimpleDateFormat(iso8601DateFormat);
                Date d = format.parse(dateString);
                Execution e = new Execution(t, d);
                e.setId(id);
                list.add(e);
            } while(c.moveToNext());
        }
        return list;
    }

    @Override
    public Execution getExecution(int id) throws ParseException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                ExecutionContract.Execution._ID,
                ExecutionContract.Execution.COLUMN_NAME_TRAINING,
                ExecutionContract.Execution.COLUMN_NAME_DATE
        };
        String selection = ExecutionContract.Execution._ID + "=?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor c = db.query(
                ExecutionContract.Execution.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
        );
        if(c.moveToFirst()){
            c.moveToFirst();
        }
        int foundId = c.getInt(c.getColumnIndex(ExecutionContract.Execution._ID));
        Training t = getTraining(c.getInt(c.getColumnIndex(ExecutionContract.Execution._ID)));
        String dateString = c.getString(c.getColumnIndex(ExecutionContract.Execution.COLUMN_NAME_DATE));
        DateFormat format = new SimpleDateFormat(iso8601DateFormat);
        Date d = format.parse(iso8601DateFormat);
        Execution e = new Execution(t, d);
        e.setId(foundId);
        return e;
    }

    @Override
    public Execution insertExecution(Execution execution) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        DateFormat format = new SimpleDateFormat(iso8601DateFormat);
        values.put(ExecutionContract.Execution.COLUMN_NAME_DATE, format.format(execution.getDate()));
        values.put(ExecutionContract.Execution.COLUMN_NAME_TRAINING, execution.getTraining().getId());
        long rowId = db.insert(ExecutionContract.Execution.TABLE_NAME, null, values);
        if(rowId != sqlInsertError){
            execution.setId(rowId);
            return execution;
        }
        return null;
    }

    @Override
    public Execution modifyExecution(Execution modified, Execution original) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        DateFormat format = new SimpleDateFormat(iso8601DateFormat);
        values.put(ExecutionContract.Execution.COLUMN_NAME_DATE, format.format(modified.getDate()));
        values.put(ExecutionContract.Execution.COLUMN_NAME_TRAINING, modified.getTraining().getId());
        values.put(ExecutionContract.Execution._ID, modified.getId());
        String where = ExecutionContract.Execution._ID + "=?";
        String[] whereArgs = { String.valueOf(original.getId()) };
        int affectedRows = db.update(ExecutionContract.Execution.TABLE_NAME, values, where, whereArgs);
        if(affectedRows == 1){
            original.update(modified.getId(), modified.getTraining(), modified.getDate());
            return original;
        }
        return null;
    }

    @Override
    public Execution deleteExecution(Execution execution) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = ExecutionContract.Execution._ID + "=?";
        String[] selectionArgs = { String.valueOf(execution.getId()) };
        int affectedRows = db.delete(ExecutionContract.Execution.TABLE_NAME, selection, selectionArgs);
        if(affectedRows == 1){
            return execution;
        }
        return null;
    }

    @Override
    public List<Iteration> getIterations() throws ParseException {
        List<Iteration> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + IterationContract.Iteration.TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do{
                int id = c.getInt(c.getColumnIndex(IterationContract.Iteration._ID));
                double weight = c.getDouble(c.getColumnIndex(IterationContract.Iteration.COLUMN_NAME_WEIGHT));
                int repetition = c.getInt(c.getColumnIndex(IterationContract.Iteration.COLUMN_NAME_REPETITION));
                Execution execution = getExecution(c.getInt(c.getColumnIndex(IterationContract.Iteration.COLUMN_NAME_EXECUTION)));
                Exercise exercise = getExercise(c.getInt(c.getColumnIndex(IterationContract.Iteration.COLUMN_NAME_EXERCISE)));
                Iteration i = new Iteration(weight, repetition, execution, exercise);
                i.setId(id);
                list.add(i);
            } while(c.moveToNext());
        }
        return list;
    }

    @Override
    public Iteration getIteration(int id) throws ParseException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                IterationContract.Iteration._ID,
                IterationContract.Iteration.COLUMN_NAME_WEIGHT,
                IterationContract.Iteration.COLUMN_NAME_REPETITION,
                IterationContract.Iteration.COLUMN_NAME_EXERCISE,
                IterationContract.Iteration.COLUMN_NAME_EXECUTION
        };
        String selection = IterationContract.Iteration._ID + "=?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor c = db.query(
                IterationContract.Iteration.TABLE_NAME,
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
        int foundId = c.getInt(c.getColumnIndex(IterationContract.Iteration._ID));
        double weight = c.getDouble(c.getColumnIndex(IterationContract.Iteration.COLUMN_NAME_WEIGHT));
        int repetition = c.getInt(c.getColumnIndex(IterationContract.Iteration.COLUMN_NAME_REPETITION));
        Exercise exercise = getExercise(c.getInt(c.getColumnIndex(IterationContract.Iteration.COLUMN_NAME_EXERCISE)));
        Execution execution = getExecution(c.getInt(c.getColumnIndex(IterationContract.Iteration.COLUMN_NAME_EXECUTION)));
        Iteration i = new Iteration(weight, repetition, execution, exercise);
        i.setId(foundId);
        return i;
    }

    @Override
    public Iteration insertIteration(Iteration iteration) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IterationContract.Iteration.COLUMN_NAME_WEIGHT, iteration.getActualWeight());
        values.put(IterationContract.Iteration.COLUMN_NAME_REPETITION, iteration.getRepetition());
        values.put(IterationContract.Iteration.COLUMN_NAME_EXERCISE, iteration.getExercise().getId());
        values.put(IterationContract.Iteration.COLUMN_NAME_EXECUTION, iteration.getExecution().getId());
        long rowId = db.insert(IterationContract.Iteration.TABLE_NAME, null, values);
        if(rowId != sqlInsertError){
            iteration.setId(rowId);
            return iteration;
        }
        return null;
    }

    @Override
    public Iteration modifyIteration(Iteration modified, Iteration original) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IterationContract.Iteration.COLUMN_NAME_WEIGHT, modified.getActualWeight());
        values.put(IterationContract.Iteration.COLUMN_NAME_REPETITION, modified.getRepetition());
        values.put(IterationContract.Iteration.COLUMN_NAME_EXERCISE, modified.getExercise().getId());
        values.put(IterationContract.Iteration.COLUMN_NAME_EXECUTION, modified.getExecution().getId());
        String where = IterationContract.Iteration._ID + "=?";
        String[] whereArgs = { String.valueOf(original.getId()) };
        int affectedRows = db.update(IterationContract.Iteration.TABLE_NAME, values, where, whereArgs);
        if(affectedRows == 1){
            original.update(modified.getId(),
                    modified.getActualWeight(),
                    modified.getRepetition(),
                    modified.getExercise(),
                    modified.getExecution());
            return original;
        }
        return null;
    }

    @Override
    public Iteration deleteIteration(Iteration iteration) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = IterationContract.Iteration._ID + "=?";
        String[] selectionArgs = { String .valueOf(iteration.getId()) };
        int affectedRow = db.delete(IterationContract.Iteration.TABLE_NAME, selection, selectionArgs);
        if(affectedRow == 1){
            return iteration;
        }
        return null;
    }
}
