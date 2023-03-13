package shared.logging.type;

import shared.logging.LogLevel;
import shared.logging.LoggingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoggerTypeDialog {

    private JDialog dialog;
    private List<JLabel> messageComponents = new ArrayList<>();

    public LoggerTypeDialog() {
        buildDialog("");
    }

    private void buildDialog(String title) {
        dialog = new JDialog();
        dialog.setTitle(title);

        JPanel panel = new JPanel(new GridLayout(messageComponents.size(), 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        messageComponents.forEach(panel::add);
        dialog.add(panel);
        dialog.setAlwaysOnTop(true);
        //dialog.setIconImage(new BufferedImage(""));
        dialog.setModal(true);
        dialog.pack();
    }

    private void buildMessageComponents(List<String> message) {
        message.forEach(msg -> messageComponents.add(new JLabel(msg)));
    }

    /**
     * Writes A LogMessage to a File
     * @param message The Message that gets written to the File
     * @see FileHandler
     * */
    public void logToDialog(LogLevel level, List<String> message) {
        buildMessageComponents(message);
        buildDialog(level.getName());
        dialog.setVisible(true);
    }

    /**
     * Writes A LogMessage to a File
     * @param message The Message that gets written to the File
     * */
    public void logToConsole(String message) {
        /*logToConsole(Arrays.asList(message));*/
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
