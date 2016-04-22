package ch.chiodo.pumper.presentation.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.model.Exercise;
import ch.chiodo.pumper.model.Training;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public class EditTrainingController extends BaseController {
    private Training training;
    public EditTrainingController(Context context, long trainingId){
        super(context);
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
    public Training getTraining(){
        return training;
    }

}
