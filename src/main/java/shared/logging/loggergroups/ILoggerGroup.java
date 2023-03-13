package shared.logging.loggergroups;

import shared.logging.ILogger;

import java.util.List;

public interface ILoggerGroup extends ILogger {

    List<ILogger> getLogger();

}
