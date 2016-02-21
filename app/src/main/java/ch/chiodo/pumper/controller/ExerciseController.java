package ch.chiodo.pumper.controller;

import ch.chiodo.pumper.model.Device;
import ch.chiodo.pumper.model.Exercise;

public class ExerciseController {
    private TrainingController trainingController;
    public ExerciseController(){
        trainingController = new TrainingController();
    }
    public Exercise newExercise(String deviceId, double weight, int repetitions){
        return null;
    }
}
