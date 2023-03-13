package shared.logging.type;

import shared.logging.LogLevel;
import ui.windows.MainWindow;
import ui.windows.WindowDesign;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoggerTypeDialog {

    private JDialog dialog;
    private WindowDesign design;
    private List<JLabel> messageComponents = new ArrayList<>();

    public LoggerTypeDialog() {
        this.design = MainWindow.getDesign();
        buildDialog("");
    }

    /**
     * Writes A LogMessage to a File
     * @param message The Message that gets written to the File
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
    public void logToDialog(LogLevel level, String message) {
        logToDialog(level, Arrays.asList(message));
    }

    /**
     * Build the Dialog
     * @param title Title of the Dialog
     * */
    private void buildDialog(String title) {
        dialog = new JDialog();
        dialog.setTitle(title);

        JPanel panel = new JPanel(new GridLayout(messageComponents.size(), 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        panel.setBackground(design.getBackgroundColor());
        messageComponents.forEach(panel::add);
        dialog.add(panel);
        dialog.setAlwaysOnTop(true);
        //dialog.setIconImage(new BufferedImage(""));
        dialog.setModal(true);
        dialog.pack();
    }

    /**
     * Build the {@code messageComponents} List
     * @param message Messages that get build
     * */
    private void buildMessageComponents(List<String> message) {
        message.forEach(msg -> {
            JLabel label = new JLabel(msg);
            label.setBackground(design.getBackgroundComponents());
            label.setForeground(design.getTextColor());
            label.setFont(design.getTextFont());
            messageComponents.add(label);
        });
    }

}
