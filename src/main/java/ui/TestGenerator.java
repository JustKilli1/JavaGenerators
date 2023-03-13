package ui;

import generator.IGenerator;
import generator.output.IOutputPrinter;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class TestGenerator implements IGenerator {

    private String name = "Generator " + ThreadLocalRandom.current().nextInt(100);

    @Override
    public String toString() {
        return "  " + name + "  ";
    }

    @Override
    public Object generate() {
        return null;
    }

    @Override
    public IOutputPrinter getOutputPrinter() {
        return null;
    }

    @Override
    public JPanel getView() {
        return null;
    }
}
