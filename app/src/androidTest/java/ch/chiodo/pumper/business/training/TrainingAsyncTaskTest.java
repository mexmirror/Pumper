package ch.chiodo.pumper.business.training;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


import ch.chiodo.pumper.model.Training;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrainingAsyncTaskTest extends AndroidTestCase{

    @Mock
    PumperService service = Mockito.mock(PumperService.class);
    private static final long CORRECT_ID = 1;

    @Test
    public void testGetTrainingAsync(){
        Training t = new Training("");
        when(service.getTraining(CORRECT_ID)).thenReturn(t);
        Training t1 = new GetTrainingTask(service).doInBackground(CORRECT_ID);
        assertEquals(t, t1);
    }
}
