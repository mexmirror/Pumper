package ch.chiodo.pumper.presentation.viewmodel;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.presentation.model.Exercise;
import ch.chiodo.pumper.presentation.model.Training;
import ch.chiodo.pumper.persistence.IPumperService;

public class EditTrainingViewModel {
    private IPumperService service;
    private Training training;
    public EditTrainingViewModel(Context context, long trainingId){
        PumperApplication application = (PumperApplication)context.getApplicationContext();
        service = application.getPumperService();
        training = service.getTraining(trainingId);
    }
    public List<Exercise> getExercises(){
        List<Exercise> trainingsList = new ArrayList<>();
        for (Exercise e : service.getExercises()) {
            if(e.getTraining().getId() == training.getId()){
                trainingsList.add(e);
            }
        }
        return trainingsList;
    }

}
