package ch.chiodo.pumper.infrastructure.logging;

import android.util.Log;

/**
 * Created by co on 07.04.16.
 */
public interface Logger{
    void debug(String tag, String message);
    void info(String tag, String message);
    void warning(String tag, String message);
    void error(String tag, String message);
    void verbose(String tag, String message);
}
