package shared.logging.type;

import shared.logging.LogCategory;
import shared.logging.LogLevel;
import shared.logging.LoggingUtils;
import shared.logging.files.FileHandler;

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
    public static String formatMessage(LogLevel logLevel, String loggerName, List<String> message, Exception ex) {
        String messageMSG = message == null ? "" : "\n" + getMessageStr(message);
        String exceptionMSG = ex == null ? "" : "\nException: " + LoggingUtils.getStackTraceAsStr(ex);
        return "Level: " + logLevel.getName() + "\n" +
                "Logger Name: " + loggerName +
                messageMSG +
                exceptionMSG;
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
        return formatMessage(logLevel, loggerName, Arrays.asList(message), ex);
    }

    /**
     * @return the given Messages List as one combined String with new line chars between the List entrys
     * */
    private static String getMessageStr(List<String> messages) {
        String combined = "";
        for(int i = 0; i < messages.size(); i++) {
            String current = messages.get(i);
            combined += current;
            if(i + 1 != messages.size()) combined += "\n";
        }
        return combined;
    }

}
