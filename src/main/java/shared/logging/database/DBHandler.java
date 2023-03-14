package shared.logging.database;

import shared.logging.ILogger;
import shared.logging.LogCategory;
import shared.logging.loggergroups.LoggerGroupDialogFile;

public class DBHandler {

    private ILogger logger;
    private DBAccessLayer sql;

    public DBHandler() {
        logger = new LoggerGroupDialogFile("DatabaseLogger", LogCategory.SYSTEM, "Database");
        sql = new DBAccessLayer(logger);
    }

}
