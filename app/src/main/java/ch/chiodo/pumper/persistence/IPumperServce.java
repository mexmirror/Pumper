package ch.chiodo.pumper.persistence;

import java.util.List;

import ch.chiodo.pumper.model.Device;
import ch.chiodo.pumper.model.Execution;
import ch.chiodo.pumper.model.Exercise;
import ch.chiodo.pumper.model.Iteration;
import ch.chiodo.pumper.model.Training;

public interface IPumperServce {
    List<Training> getTrainings();
    Training getTraining(int id);
    Training insertTraining(Training training);
    Training modifyTraining(Training modified, Training original);
    Training deleteTraining(Training training);

    List<Device> getDevices();
    Device getDevice(int id);
    Device insertDevice(Device device);
    Device modifyDevice(Device modified, Device original);
    Device deleteDevice(Device device);

    List<Exercise> getExercises();
    Exercise getExercise(int id);
    Exercise insertExercise(Exercise exercise);
    Exercise modifyExercise(Exercise modified, Exercise original);
    Exercise deleteExercise(Exercise exercise);

    List<Execution> getExecutions();
    Execution getExecution(int id);
    Execution insertExecution(Execution execution);
    Execution modifyExecution(Execution modified, Execution original);
    Execution deleteExecution(Execution execution);

    List<Iteration> getIterations();
    Iteration getIteration(int id);
    Iteration insertIteration(Iteration iteration);
    Iteration modifyIteration(Iteration modified, Iteration original);
    Iteration deleteIteration(Iteration iteration);
}
