package base;

import shared.logging.ILogger;
import shared.logging.LogCategory;
import shared.logging.LogLevel;
import shared.logging.loggergroups.LoggerGroupDialogFile;
import shared.logging.loggers.BaseDialogLogger;
import shared.logging.type.LoggerTypeDialog;
import ui.windows.MainWindow;
import ui.components.RoundBorder;
import ui.windows.WindowDesign;

import java.awt.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        WindowDesign design = buildDesign();
        MainWindow window = new MainWindow(design);
        window.setVisible(true);
        ILogger loggerDialog = new LoggerGroupDialogFile("Test Group Logger", LogCategory.SYSTEM, "GroupTestLoggerFile");
        loggerDialog.log(LogLevel.INFO, Arrays.asList("Hello I'm Dave it's very ", "nice to meet you"), null);
    }

    private static WindowDesign buildDesign() {
        WindowDesign design = new WindowDesign();
        design.setBackgroundColor(new Color(28, 30, 46));
        design.setBackgroundComponents(new Color(47, 51, 79));
        design.setSelectionBackground(design.getBackgroundColor());
        design.setSelectionForeground(Color.WHITE);
        design.setCaretColor(Color.WHITE);
        design.setHeaderColor(Color.WHITE);
        design.setTextColor(Color.WHITE);
        design.setBorder(new RoundBorder(design.getBackgroundComponents(), 5, 5));
        design.setTextFont(new Font("Cascadia Code" , Font.PLAIN, 12));
        design.setHeaderFont(new Font("Cascadia Code", Font.BOLD, 16));
        return design;
    }
}