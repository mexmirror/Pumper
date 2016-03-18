package ch.chiodo.pumper.presentation.model;

public class Exercise {
    private long id;
    private Training training;
    private Device device;
    private double weight;
    private int repetition;

    public Exercise(Training training, Device device, double weight, int repetition){
        this.weight = weight;
        this.repetition = repetition;
        this.device = device;
        this.training = training;
    }
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public void update(Exercise other){
        this.device = other.device;
        this.weight = other.weight;
        this.repetition = other.repetition;
        this.training = other.training;
    }
}
