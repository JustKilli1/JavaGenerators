package shared.logging.loggers;

import shared.logging.ILogger;
import shared.logging.LogLevel;
import shared.logging.type.LoggerTypeDialog;

import java.util.Arrays;
import java.util.List;

public class BaseDialogLogger extends LoggerTypeDialog implements ILogger {

    private String name;

    public BaseDialogLogger(String name) {
        this.name = name;
    }

    @Override
    public void log(LogLevel logLevel, List<String> message, Exception ex) {
        //Ignore Exception ex
        logToDialog(logLevel, message);
    }

    @Override
    public void log(LogLevel logLevel, List<String> message) {
        logToDialog(logLevel, message);
    }

    @Override
    public void log(LogLevel logLevel, String message, Exception ex) {
        //Ignore Exception ex
        log(logLevel, Arrays.asList(message), ex);
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        log(logLevel, Arrays.asList(message));
    }

    @Override
    public void log(LogLevel logLevel, Exception ex) {
        //Ignore ex
        log(logLevel, "Es ist ein fehler aufgetreten");
    }

    @Override
    public String getName() {
        return name;
    }
}
