package shared.logging.database;

import shared.logging.ILogger;
import shared.logging.LogLevel;

import java.sql.ResultSet;
import java.util.Optional;

public class DBAccessLayer {
    private ILogger logger;
    private MySQL mySQL;

    public DBAccessLayer(ILogger logger) {
        this.logger = logger;
        mySQL = new MySQL(this.logger);
        mySQL.connect();
    }

    public void disconnect() {
        mySQL.disconnect();
    }

    public void checkAndReconnect() {
        if(!mySQL.isConnected()) {
            mySQL.connect();
        }
    }

    public boolean executeSQLRequest(String query) {
        checkAndReconnect();
        if(!mySQL.isConnected()) return false;
        try {
            mySQL.getDatabaseCon().prepareStatement(query).executeUpdate();
            return true;
        } catch(Exception ex) {
            logger.log(LogLevel.ERROR, "Could not Execute following SQL Statement: " + query, ex);
            return false;
        }
    }

    public Optional<ResultSet> querySQLRequest(String query) {
        checkAndReconnect();
        if(!mySQL.isConnected()) return Optional.empty();
        try {
            return Optional.ofNullable(mySQL.getDatabaseCon().prepareStatement(query).executeQuery());
        } catch(Exception ex) {
            logger.log(LogLevel.ERROR, "Could not Execute following SQL Statement: " + query, ex);
            return Optional.empty();
        }
    }

}
