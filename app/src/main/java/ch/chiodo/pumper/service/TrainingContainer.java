package ch.chiodo.pumper.service;

import java.io.Serializable;

import ch.chiodo.pumper.data.Training;

public class TrainingContainer implements Serializable, TrainingPersistence {
    private Training training;
    public final static String FILENAME = "trainings.bin";
    private static TrainingContainer container;
    private TrainingContainer(){
        training = new Training();
    }
    public static TrainingContainer getTrainingContainer() {
        if (container == null) {
            container = new TrainingContainer();
        }
        return container;
    }
    @Override
    public final Training getTraining(){
        return training;
    }
}