package shared.database;

import shared.logging.ILogger;
import shared.logging.LogLevel;
import shared.logging.files.FileHandler;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Class that loads the Database Driver and establishes a Connection with the Database.
 * */
public class MySQL {
    private ILogger logger;
    private String host, port, database, username, password;
    private Connection databaseCon;

    public MySQL(ILogger logger) {
        this.logger = logger;
        loadConfig();
    }

    public void connect() {
        if(!isConnected()) {
            try {
                logger.log(LogLevel.INFO, "Connect to Database...");
                databaseCon = DriverManager.getConnection("jdbc:mysql://" +
                                host +
                                ":" + port +
                                "/" + database +
                                "?autoReconnect=true&useSSL=false",
                        username, password);
                logger.log(LogLevel.INFO, "Database connection established.");
            } catch(Exception ex) {
                logger.log(LogLevel.ERROR, "Could not Connect to Database", ex);
            }
        }
    }

    public void disconnect() {
        if(isConnected()) {
            try {
                logger.log(LogLevel.INFO, "Disconnect...");
                databaseCon.close();
                logger.log(LogLevel.INFO, "Disconnected");
            } catch(Exception ex) {
                logger.log(LogLevel.WARN, "Could not Disconnect properly from Database.");
            }
        }
    }

    public boolean isConnected() {
        return databaseCon != null;
    }

    public Connection getDatabaseCon() { return databaseCon; }

    private boolean loadConfig() {
        try {
            new FileHandler("mysql_config.properties").createFileWithDirectorys();
            logger.log(LogLevel.INFO, "Loading MySQL Config File...");
            Properties prop = new Properties();
            prop.load(new FileReader("mysql_config.properties"));
            port = prop.getProperty("db.port", "3306");
            host = prop.getProperty("db.host", "localhost");
            username = prop.getProperty("db.user");
            password = prop.getProperty("db.pass");
            database = prop.getProperty("db.database");
            logger.log(LogLevel.INFO, "MySQL Config File loaded.");
            return true;
        } catch(IOException ex) {
            logger.log(LogLevel.INFO, "Could not load MySQL Config File.");
            return false;
        }
    }
}
