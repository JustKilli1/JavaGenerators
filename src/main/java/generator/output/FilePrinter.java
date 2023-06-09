package generator.output;

import shared.logging.ILogger;
import shared.logging.LogCategory;
import shared.logging.LogLevel;
import shared.logging.files.FileHandler;
import shared.logging.loggergroups.LoggerGroupDialogFile;
import ui.windows.WindowDesign;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class FilePrinter implements IOutputPrinter {

    private ILogger logger;
    private FileHandler fileHandler;
    public static final String OUTPUT_DIRECTORY = "out/";
    public static final String FILE_TYPE = ".txt";

    public FilePrinter(String name) {
        fileHandler = new FileHandler(OUTPUT_DIRECTORY + name + FILE_TYPE);
        logger = new LoggerGroupDialogFile("", LogCategory.SYSTEM, "Log_FilePrinter");
    }

    @Override
    public void println(List<String> value) {
        try {
            fileHandler.write(value, false);
        } catch(Exception ex) {
            logger.log(LogLevel.ERROR, "Could not Write data to File", ex);
        }
    }

    @Override
    public void println(String value) {
        try {
            fileHandler.write(Arrays.asList(value), true);
        } catch(Exception ex) {
            logger.log(LogLevel.ERROR, "Could not Write data to File", ex);
        }    }

    @Override
    public void print(List<String> value) {
        try {
            fileHandler.write(value, true);
        } catch(Exception ex) {
            logger.log(LogLevel.ERROR, "Could not Write data to File", ex);
        }
    }

    @Override
    public void print(String value) {
        print(Arrays.asList(value));
    }

    @Override
    public JPanel getView() {
        return null;
    }

    @Override
    public void changeDesign(WindowDesign design) {

    }

    @Override
    public void clearOutput() {
        //Ignore
    }
}
