package ch.chiodo.pumper.presentation.model;

public class Iteration {
    private long id;
    private double actualWeight;
    private int repetition;
    private Execution execution;
    private Exercise exercise;

    public Iteration(double actualWeight, int repetition, Execution execution, Exercise exercise) {
        this.actualWeight = actualWeight;
        this.repetition = repetition;
        this.execution = execution;
        this.exercise = exercise;
    }

    public double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public Execution getExecution() {
        return execution;
    }

    public void setExecution(Execution execution) {
        this.execution = execution;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void update(Iteration other) {
        this.actualWeight = other.actualWeight;
        this.repetition = other.repetition;
        this.execution = other.execution;
        this.exercise = other.exercise;

    }
}
