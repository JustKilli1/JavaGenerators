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
        //Ignore
    }

    @Override
    public void log(LogLevel logLevel, List<String> message) {
        logToDialog(logLevel, message);
    }

    @Override
    public void log(LogLevel logLevel, String message, Exception ex) {
        //Ignore
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        log(logLevel, message);
    }

    @Override
    public void log(LogLevel logLevel, Exception ex) {
        //Ignore
    }

    @Override
    public String getName() {
        return name;
    }
}
