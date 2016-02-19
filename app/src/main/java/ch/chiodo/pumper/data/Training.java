package ch.chiodo.pumper.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Training {
    private List<Exercise> exercises;
    public Training(){
        exercises = new ArrayList<>();
    }
    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }
    public Exercise getExerciseAtPosition(int position){
        return exercises.get(position);
    }
    public Exercise removeExerciseAtPosition(int position){
        return exercises.remove(position);
    }
    public List<Exercise> getExercises() {
        return new ArrayList<>(exercises);
    }
}
