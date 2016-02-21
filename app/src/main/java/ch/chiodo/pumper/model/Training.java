package ch.chiodo.pumper.model;

import java.util.ArrayList;
import java.util.List;

public class Training {
    private List<Exercise> exercises;
    public Training(){
        this(new ArrayList<Exercise>());
    }

    public Training (List<Exercise> list){
        exercises = list;
    }

    public boolean addExercise(Exercise exercise){
        exercises.add(exercise);

        return false;
    }

    public Exercise getExerciseAtPosition(int position){
        return exercises.get(position);
    }

    public Exercise removeExercise(Exercise exercise){
        if(exercises.remove(exercise)){
            return exercise;
        }
        return null;
    }
    public List<Exercise> getExercises() {
        return new ArrayList<>(exercises);
    }
}
