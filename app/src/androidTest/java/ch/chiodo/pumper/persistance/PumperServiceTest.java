package ch.chiodo.pumper.persistance;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ch.chiodo.pumper.persistence.PumperDbHelper;
import ch.chiodo.pumper.persistence.PumperService;
import static org.mockito.Mockito.*;

public class PumperServiceTest extends AndroidTestCase{
    private SQLiteOpenHelper fakeDbHelper;
    private PumperService service;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        fakeDbHelper = new PumperDbHelper(context);
        service = new PumperService(fakeDbHelper);
    }
    @Test
    public void testGetTraining(){
        SQLiteDatabase db = fakeDbHelper.getReadableDatabase();


    }

    @Override
    public void tearDown() throws Exception {
        fakeDbHelper.close();
        super.tearDown();
    }
}
