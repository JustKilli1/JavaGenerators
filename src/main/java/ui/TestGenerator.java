package ui;

import generator.IGenerator;
import generator.output.IOutputPrinter;

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
    public IOutputPrinter getOutputWriter() {
        return null;
    }
}
