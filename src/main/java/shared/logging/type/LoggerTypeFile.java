package shared.logging.type;

import net.marscraft.general.logging.LogCategory;
import net.marscraft.general.logging.LogLevel;
import net.marscraft.general.logging.LoggingUtils;
import net.marscraft.general.logging.files.FileHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Class that provides default Methods to Write Logs to a File
 * */
public class LoggerTypeFile {

    //FileHandler that Manages the LogFile
    private FileHandler fileHandler;
    protected LogCategory logCategory;

    public LoggerTypeFile(LogCategory logCategory, String logFileName) {
        this.logCategory = logCategory;
        fileHandler = new FileHandler("Logs/" + logCategory.getFolderName() + "/" + logFileName);

    }

    /**
     * Writes A LogMessage to a File
     * @param message The Message that gets written to the File
     * @see FileHandler
     * */
    public void logToFile(List<String> message) {
        try {
            fileHandler.write(message, fileHandler.fileExists());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Writes A LogMessage to a File
     * @param message The Message that gets written to the File
     * */
    public void logToFile(String message) {
        logToFile(Arrays.asList(message));
    }

    /**
     * Method for Logger to Format a File Log Message
     * @param logLevel LogLevel
     * @param loggerName Name of the calling logger
     * @param message Custom Message
     * @param ex occurring Exception
     * @return Formatted LogMessage
     * {@link LogLevel}
     * */
    public static String formatMessage(LogLevel logLevel, String loggerName, String message, Exception ex) {
        String messageMSG = message == null ? "" : "\n" + message;
        String exceptionMSG = ex == null ? "" : "\nException: " + LoggingUtils.getStackTraceAsStr(ex);
        return "Level: " + logLevel.getName() + "\n" +
                "Logger Name: " + loggerName +
                messageMSG +
                exceptionMSG;
    }

}
