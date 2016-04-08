package ch.chiodo.pumper.infrastructure.logging;

import android.util.Log;

public final class LogService implements Logger {
    private LogService(){}

    public LogService getInstance(){
        return new LogService();
    }

    @Override
    public void debug(String tag, String message){
        Log.d(tag, message);
    }

    @Override
    public void info(String tag, String message){
        Log.i(tag, message);
    }

    @Override
    public void warning(String tag, String message){
        Log.w(tag, message);
    }

    @Override
    public void error(String tag, String message){
        Log.e(tag, message);
    }

    @Override
    public void verbose(String tag, String message){
        Log.v(tag, message);
    }
}
