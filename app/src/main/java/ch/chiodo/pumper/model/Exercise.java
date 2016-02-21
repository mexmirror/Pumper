package ch.chiodo.pumper.model;

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
    public void update(Device device, double weight, int repetition){
        this.device = device;
        this.weight = weight;
        this.repetition = repetition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exercise exercise = (Exercise) o;

        if (Double.compare(exercise.weight, weight) != 0) return false;
        if (repetition != exercise.repetition) return false;
        return device.equals(exercise.device);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = device.hashCode();
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + repetition;
        return result;
    }
}
