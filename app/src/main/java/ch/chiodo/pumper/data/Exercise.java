package ch.chiodo.pumper.data;

public class Exercise {
    private Device device;
    private double weight;
    private int repetition;
    public Exercise(Device device, double weight, int repetition){
        this.weight = weight;
        this.repetition = repetition;
        this.device = device;
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
}
