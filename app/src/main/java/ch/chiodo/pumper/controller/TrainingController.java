package ch.chiodo.pumper.controller;

import java.util.List;

import ch.chiodo.pumper.data.Exercise;
import ch.chiodo.pumper.data.Training;
import ch.chiodo.pumper.service.TrainingContainer;
import ch.chiodo.pumper.service.TrainingPersistence;

public class TrainingController {
    private final TrainingPersistence trainingPersistence;

    public TrainingController(){
        trainingPersistence = TrainingContainer.getTrainingContainer();
    }
    public List<Exercise> getExercises(){
        Training t = trainingPersistence.getTraining();
        return t.getExercises();
    }
    public Exercise addExerciseToTraining(Exercise exercise){
        trainingPersistence.getTraining().addExercise(exercise);
        return exercise;
    }
}
