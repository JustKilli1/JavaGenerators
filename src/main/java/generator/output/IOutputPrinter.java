package generator.output;

import javax.swing.*;
import java.util.List;

public interface IOutputPrinter {

    /**
     * Writes the given Values to the Output Target as new Line
     * @param value Value that gets written
     * */
    void println(List<String> value);

    /**
     * Writes the given Value to the Output Target as new Line
     * @param value Value that gets written
     * */
    void println(String value);

    /**
     * Writes the given Values to the Output Target
     * @param value Value that gets written
     * */
    void print(List<String> value);

    /**
     * Writes the given Value to the Output Target
     * @param value Value that gets written
     * */
    void print(String value);

    /**
     * Get all Components needed for this Generator Output to function properly
     * @return All Components needed for this Generator Output to function properly
     * */
    List<JComponent> getView();


}
