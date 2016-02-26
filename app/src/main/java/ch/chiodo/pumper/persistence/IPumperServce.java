package ch.chiodo.pumper.persistence;

import java.text.ParseException;
import java.util.List;

import ch.chiodo.pumper.model.Device;
import ch.chiodo.pumper.model.Execution;
import ch.chiodo.pumper.model.Exercise;
import ch.chiodo.pumper.model.Iteration;
import ch.chiodo.pumper.model.Training;

public interface IPumperServce {
    List<Training> getTrainings();
    Training getTraining(long id);
    Training insertTraining(Training training);
    Training modifyTraining(Training modified, Training original);
    Training deleteTraining(Training training);

    List<Device> getDevices();
    Device getDevice(long id);
    Device insertDevice(Device device);
    Device modifyDevice(Device modified, Device original);
    Device deleteDevice(Device device);

    List<Exercise> getExercises();
    Exercise getExercise(long id);
    Exercise insertExercise(Exercise exercise);
    Exercise modifyExercise(Exercise modified, Exercise original);
    Exercise deleteExercise(Exercise exercise);

    List<Execution> getExecutions() throws ParseException;
    Execution getExecution(long id) throws ParseException;
    Execution insertExecution(Execution execution);
    Execution modifyExecution(Execution modified, Execution original);
    Execution deleteExecution(Execution execution);

    List<Iteration> getIterations() throws ParseException;
    Iteration getIteration(long id) throws ParseException;
    Iteration insertIteration(Iteration iteration);
    Iteration modifyIteration(Iteration modified, Iteration original);
    Iteration deleteIteration(Iteration iteration);
}
