package ch.chiodo.pumper.presentation.viewmodel;

import android.content.Context;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.infrastructure.logging.LogService;
import ch.chiodo.pumper.infrastructure.logging.Logger;
import ch.chiodo.pumper.persistence.dataaccess.IPumperService;

public abstract class BaseViewModel {
    protected IPumperService service;
    protected final Logger log;

    public BaseViewModel(Context context){
        service = ((PumperApplication)context.getApplicationContext()).getPumperService();
        log = LogService.getInstance();
    }

}
