package generator;

import generator.output.IOutputPrinter;

public interface IGenerator<T> {

    T generate();
    IOutputPrinter getOutputWriter();

}
