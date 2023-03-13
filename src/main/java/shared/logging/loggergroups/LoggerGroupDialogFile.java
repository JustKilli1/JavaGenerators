package shared.logging.loggergroups;

import shared.logging.ILogger;
import shared.logging.LogCategory;
import shared.logging.LogLevel;
import shared.logging.loggers.BaseDialogLogger;
import shared.logging.loggers.BaseFileLogger;

import java.util.Arrays;
import java.util.List;

public class LoggerGroupDialogFile implements ILoggerGroup{

    private List<ILogger> loggers;

    public LoggerGroupDialogFile(String loggerName, LogCategory logCategory, String logFileName) {
        loggers = Arrays.asList(
                new BaseDialogLogger(loggerName),
                new BaseFileLogger(logCategory, logFileName, loggerName)
        );
    }

    @Override
    public void log(LogLevel logLevel, List<String> message, Exception ex) {
        loggers.forEach(logger -> logger.log(logLevel, message, ex));
    }

    @Override
    public void log(LogLevel logLevel, List<String> message) {
        loggers.forEach(logger -> logger.log(logLevel, message));
    }

    @Override
    public void log(LogLevel logLevel, String message, Exception ex) {
        log(logLevel, Arrays.asList(message), ex);
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        log(logLevel, Arrays.asList(message));
    }

    @Override
    public void log(LogLevel logLevel, Exception ex) {
        loggers.forEach(logger -> logger.log(logLevel, ex));
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<ILogger> getLogger() {
        return loggers;
    }
}
