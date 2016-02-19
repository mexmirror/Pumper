package ch.chiodo.pumper.view;

import android.os.AsyncTask;

import ch.chiodo.pumper.service.TrainingContainer;

public class TrainingTask extends AsyncTask<Void, Void, TrainingContainer>{

    @Override
    protected TrainingContainer doInBackground(Void... params) {
        /*TrainingContainer trainings = TrainingContainer.getTrainingContainer();
        try {
            trainings.loadTrainings();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        return null;
    }
}
