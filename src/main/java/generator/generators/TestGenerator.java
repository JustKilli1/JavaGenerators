package generator.generators;

import generator.IGenerator;
import generator.output.IOutputPrinter;
import generator.output.TxtFieldPrinter;
import ui.windows.WindowDesign;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestGenerator implements IGenerator<String> {

    private String name = "Generator " + ThreadLocalRandom.current().nextInt(100);
    private JPanel view;
    private WindowDesign design;
    private List<IOutputPrinter> outputPrinter;

    public TestGenerator(WindowDesign design) {
        this.design = design;
        outputPrinter = Arrays.asList(
                new TxtFieldPrinter(this.design, "Test Output Field")
        );
        buildView();
    }

    private void buildView() {
        view = new JPanel();
        JLabel label = new JLabel(name);
        label.setFont(design.getHeaderFont());
        label.setForeground(design.getHeaderColor());
        label.setBackground(design.getBackgroundComponents());

        JPanel outView = outputPrinter.get(0).getView();
        view.add(outView);
        view.add(label);
    }

    @Override
    public String generate() {
        String testPw = String.valueOf(ThreadLocalRandom.current().nextLong(999999999));
        outputPrinter.get(0).println(testPw);
        return testPw;
    }

    @Override
    public List<String> generate(long amount) {
        return null;
    }

    @Override
    public List<IOutputPrinter> getOutputPrinter() {
        return outputPrinter;
    }

    @Override
    public JPanel getView() {
        return view;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void changeDesign(WindowDesign design) {
        this.design = design;
    }

}
