package ch.chiodo.pumper.presentation.viewmodel;

import android.content.Context;

import ch.chiodo.pumper.presentation.model.Training;

public class SettingsViewModel extends BaseViewModel{
    public SettingsViewModel(Context context) {
        super(context);
    }

    public Training addTraining(String name) throws IllegalAccessException {
        Training t = new Training(name);
        t = service.insertTraining(t);
        if(t != null) {
            return t;
        }
        throw new IllegalAccessException("Object could not be saved into database");
    }
}
