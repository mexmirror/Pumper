package ch.chiodo.pumper.presentation.controller;

import android.content.Context;

import ch.chiodo.pumper.PumperApplication;
import ch.chiodo.pumper.infrastructure.logging.LogService;
import ch.chiodo.pumper.infrastructure.logging.Logger;
import ch.chiodo.pumper.persistence.dataaccess.PumperService;

public abstract class BaseController {
    protected PumperService service;
    protected final Logger log;

    public BaseController(Context context){
        service = ((PumperApplication)context.getApplicationContext()).getPumperService();
        log = LogService.getInstance();
    }

}
