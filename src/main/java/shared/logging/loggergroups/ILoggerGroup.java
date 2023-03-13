package shared.logging.loggergroups;

import shared.logging.ILogger;

import java.util.List;

/**
 * Calls Multiple Loggers
 * */
public interface ILoggerGroup extends ILogger {

    /**
     * @return All Loggers present in this instance
     * */
    List<ILogger> getLogger();

}
