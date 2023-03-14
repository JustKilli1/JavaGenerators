package generator;

import generator.output.IOutputPrinter;
import ui.windows.WindowDesign;

import javax.swing.*;
import java.util.List;

/**
 * Generator that can generate a Value
 * */
public interface IGenerator<T> {

    /**
     * Generates a Value with Type {@code T}
     * @return Generated Value
     * */
    T generate();

    /**
     * @return The Output Printer responsible for this Generator
     * {@link IOutputPrinter}
     * */
    List<IOutputPrinter> getOutputPrinter();

    /**
     * @return A JPanel with all Components needed to Display this Generator
     * */
    JPanel getView();

    String getName();
    void changeDesign(WindowDesign design);

}
