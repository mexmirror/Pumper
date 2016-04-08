package ch.chiodo.pumper.persistence;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.List;

import ch.chiodo.pumper.persistence.dataaccess.PumperDbHelper;
import ch.chiodo.pumper.persistence.dataaccess.PumperServiceSqlite;
import ch.chiodo.pumper.model.Device;
import ch.chiodo.pumper.model.Execution;
import ch.chiodo.pumper.model.Exercise;
import ch.chiodo.pumper.model.Training;
import ch.chiodo.pumper.infrastructure.service.DateParseService;
import ch.chiodo.pumper.persistence.dbcontract.DeviceContract;
import ch.chiodo.pumper.persistence.dbcontract.ExerciseContract;
import ch.chiodo.pumper.persistence.dbcontract.TrainingContract;

@RunWith(MockitoJUnitRunner.class)
public class PumperServiceTest extends AndroidTestCase {
    private SQLiteOpenHelper fakeDbHelper;
    private PumperServiceSqlite service;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        fakeDbHelper = new PumperDbHelper(context);
        service = new PumperServiceSqlite(fakeDbHelper);
    }

    @Test
    public void testGetTrainings() {
        SQLiteDatabase db = fakeDbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO " + TrainingContract.Training.TABLE_NAME + " VALUES(1, \"test\")");
        db.execSQL("INSERT INTO " + TrainingContract.Training.TABLE_NAME + " VALUES(2, \"test\")");
        db.execSQL("INSERT INTO " + TrainingContract.Training.TABLE_NAME + " VALUES(3, \"test\")");
        List<Training> list = service.getTrainings();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).getId());
        assertEquals("test", list.get(0).getName());
        assertEquals(2, list.get(1).getId());
        assertEquals(3, list.get(2).getId());
    }

    @Test
    public void testGetTraining() {
        SQLiteDatabase db = fakeDbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO " + TrainingContract.Training.TABLE_NAME + " VALUES(1, \"test\")");
        Training t = service.getTraining(1);
        assertNotNull(t);
        assertEquals(1, t.getId());
        assertEquals("test", t.getName());
    }

    @Test
    public void testInsertTraining() {
        Training t = new Training("test");
        assertNotNull(service.insertTraining(t));
        assertEquals(1, t.getId());
    }

    @Test
    public void testModifyTraining() {
        String modified = "modified";
        Training t1 = new Training("test");
        assertNotNull(service.insertTraining(t1));
        Training t2 = new Training(modified);
        t2.setId(t1.getId());
        assertNotNull(service.modifyTraining(t2, t1));
        assertEquals(modified, t1.getName());
        Training t3 = service.getTraining(t1.getId());
        assertNotNull(t3);
        assertEquals(modified, t3.getName());
    }

    @Test
    public void testDeleteTraining() {
        Training t = new Training("test");
        assertNotNull(service.insertTraining(t));
        assertNotNull(service.getTraining(t.getId()));
        assertNotNull(service.deleteTraining(t));
        assertNull(service.getTraining(t.getId()));
    }

    @Test
    public void testGetDevice() {
        SQLiteDatabase db = fakeDbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO " + DeviceContract.Device.TABLE_NAME + " VALUES(1,\"Test Device\")");
        Device d = service.getDevice(1);
        assertNotNull(d);
        assertEquals(1, d.getId());
        assertEquals("Test Device", d.getDeviceName());
    }

    @Test
    public void testGetDevices() {
        SQLiteDatabase db = fakeDbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO " + DeviceContract.Device.TABLE_NAME + " VALUES(1,\"Test Device\");");
        db.execSQL("INSERT INTO " + DeviceContract.Device.TABLE_NAME + " VALUES(2,\"Test Device\");");
        db.execSQL("INSERT INTO " + DeviceContract.Device.TABLE_NAME + " VALUES(3,\"Test Device\");");
        List<Device> list = service.getDevices();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).getId());
        assertEquals("Test Device", list.get(0).getDeviceName());
        assertEquals(2, list.get(1).getId());
        assertEquals(3, list.get(2).getId());
    }

    @Test
    public void testInsertDevice() {
        Device d = new Device("Test Device");
        assertNotNull(service.insertDevice(d));
        assertEquals(1, d.getId());
    }

    @Test
    public void testModifyDevice() {
        String modified = "modified device";
        Device d1 = new Device("test device");
        assertNotNull(service.insertDevice(d1));
        Device d2 = new Device(modified);
        d2.setId(d1.getId());
        assertNotNull(service.modifyDevice(d2, d1));
        assertEquals(modified, d1.getDeviceName());
        Device d3 = service.getDevice(d1.getId());
        assertNotNull(d3);
        assertEquals(modified, d3.getDeviceName());
    }

    @Test
    public void testDeleteDevice() {
        Device d = new Device("test device");
        assertNotNull(service.insertDevice(d));
        assertNotNull(service.getDevice(d.getId()));
        assertNotNull(service.deleteDevice(d));
        assertNull(service.getDevice(d.getId()));
    }
    @Test
    public void testGetExercise(){
        SQLiteDatabase db = fakeDbHelper.getWritableDatabase();
        assertNotNull(service.insertTraining(new Training("test")));
        assertNotNull(service.insertDevice(new Device("test device")));
        db.execSQL("INSERT INTO " + ExerciseContract.Exercise.TABLE_NAME + " values(1, 25.5, 8, 1, 1);");
        Exercise e = service.getExercise(1);
        assertNotNull(e);
        assertEquals(25.5, e.getWeight(), 0.0001);
        assertEquals(8, e.getRepetition());
        assertEquals("test", e.getTraining().getName());
        assertEquals("test device", e.getDevice().getDeviceName());
    }
    @Test
    public void testGetExercises(){
        SQLiteDatabase db = fakeDbHelper.getWritableDatabase();
        assertNotNull(service.insertTraining(new Training("test")));
        assertNotNull(service.insertDevice(new Device("test device")));
        db.execSQL("INSERT INTO " + ExerciseContract.Exercise.TABLE_NAME + " values(1, 25.5, 8, 1, 1);");
        db.execSQL("INSERT INTO " + ExerciseContract.Exercise.TABLE_NAME + " values(2, 25.5, 8, 1, 1);");
        db.execSQL("INSERT INTO " + ExerciseContract.Exercise.TABLE_NAME + " values(3, 25.5, 8, 1, 1);");
        List<Exercise> list = service.getExercises();
        assertEquals(3, list.size());
        assertEquals(1, list.get(0).getId());
        assertEquals(25.5, list.get(0).getWeight(), 0.0001);
        assertEquals(8, list.get(0).getRepetition());
        assertEquals(1, list.get(0).getTraining().getId());
        assertEquals(1, list.get(0).getDevice().getId());
        assertEquals(2, list.get(1).getId());
        assertEquals(3, list.get(2).getId());
    }
    @Test
    public void testInsertExercise(){
        Training t = new Training("test");
        assertNotNull(service.insertTraining(t));
        Device d = new Device("test device");
        assertNotNull(service.insertDevice(d));
        Exercise e = new Exercise(t, d, 60.0, 8);
        assertNotNull(service.insertExercise(e));
        assertEquals(1, e.getId());
    }
    @Test
    public void testModifyExercise(){
        Training t = new Training("test");
        assertNotNull(service.insertTraining(t));
        Device d = new Device("test device");
        assertNotNull(service.insertDevice(d));
        Exercise e1 = new Exercise(t, d, 60.0, 8);
        assertNotNull(service.insertExercise(e1));
        Exercise e2 = new Exercise(t, d, 65.0, 7);
        assertNotNull(service.modifyExercise(e2, e1));
        assertEquals(65.0, e1.getWeight(), 0.0001);
        assertEquals(7, e1.getRepetition());
        Exercise e3 = service.getExercise(e1.getId());
        assertNotNull(e3);
        assertEquals(7, e3.getRepetition());
    }
    @Test
    public void testDeleteExercise(){
        Training t = new Training("test");
        assertNotNull(service.insertTraining(t));
        Device d = new Device("test device");
        assertNotNull(service.insertDevice(d));
        Exercise e = new Exercise(t, d, 60.0, 8);
        assertNotNull(service.insertExercise(e));
        assertNotNull(service.getExercise(e.getId()));
        assertNotNull(service.deleteExercise(e));
        assertNull(service.getExercise(e.getId()));
    }
    @Test
    public void testInsertExecution() {
        Training t = new Training("test");
        service.insertTraining(t);
        Execution execution = new Execution(t);
        service.insertExecution(execution);
        try {
            assertEquals(DateParseService.fromCalendar(execution.getDate()),
                    DateParseService.fromCalendar(service.getExecution(execution.getId()).getDate())
            );
        } catch (ParseException e) {
            fail(e.toString());
        }
    }

    @Test
    public void testGetDeviceSetting(){

    }
    @Override
    public void tearDown() throws Exception {
        fakeDbHelper.close();
        super.tearDown();
    }
}
