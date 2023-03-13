package generator.output;

import shared.logging.ILogger;
import shared.logging.LogCategory;
import shared.logging.LogLevel;
import shared.logging.files.FileHandler;
import shared.logging.loggergroups.LoggerGroupDialogFile;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class FilePrinter implements IOutputPrinter {

    private ILogger logger;
    private FileHandler fileHandler;

    public FilePrinter(String name) {
        fileHandler = new FileHandler("out/" + name);
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
        println(Arrays.asList(value));
    }

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
}
