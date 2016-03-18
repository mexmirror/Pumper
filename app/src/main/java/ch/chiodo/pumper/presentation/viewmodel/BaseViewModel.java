package ch.chiodo.pumper.presentation.viewmodel;

import android.content.Context;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.persistence.IPumperService;

public abstract class BaseViewModel {
    protected IPumperService service;

    public BaseViewModel(Context context){
        service = ((PumperApplication)context.getApplicationContext()).getPumperService();
    }

}
