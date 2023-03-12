package generator.output;

import javax.swing.*;
import java.util.List;

public interface IOutputPrinter {

    /**
     * Writes the given Value to the Output Target
     * @param value Value that gets written
     * */
    void println(String value, JComponent... target);

    /**
     * Build all Components needed for this Generator Output to function properly
     * @return All Components needed for this Generator Output to function properly
     * */
    List<JComponent> buildView();


}
