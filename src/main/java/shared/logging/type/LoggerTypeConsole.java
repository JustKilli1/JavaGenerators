package shared.logging.type;

import net.marscraft.general.logging.LogLevel;
import net.marscraft.general.logging.LoggingUtils;

import java.util.Arrays;
import java.util.List;

public class LoggerTypeConsole {

    /**
     * Writes A LogMessage to a File
     * @param message The Message that gets written to the File
     * @see FileHandler
     * */
    public void logToConsole(List<String> message) {
        message.forEach(System.out::println);
    }

    /**
     * Writes A LogMessage to a File
     * @param message The Message that gets written to the File
     * */
    public void logToConsole(String message) {
        logToConsole(Arrays.asList(message));
    }

    /**
     * Method for Logger to Format a Console Log Message
     * @param logLevel LogLevel
     * @param loggerName Name of the calling logger
     * @param message Custom Message
     * @param ex occurring Exception
     * @return Formatted LogMessage
     * {@link LogLevel}
     * */
    public static String formatMessage(LogLevel logLevel, String loggerName, String message, Exception ex) {
        String messageMSG = message == null ? "" : message;
        String exceptionMSG = ex == null ? "" : "\nException: " + LoggingUtils.getStackTraceAsStr(ex);

        return "[" + loggerName + "]" +
                "[" + logLevel.getName() + "]: " +
                messageMSG +
                exceptionMSG;
    }

}
