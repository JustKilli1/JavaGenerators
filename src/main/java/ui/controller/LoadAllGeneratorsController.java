package ui.controller;

import generator.GeneratorModel;
import generator.IGenerator;
import ui.windows.MainWindow;

import java.util.List;

public class LoadAllGeneratorsController {

    private MainWindow view;
    private GeneratorModel model;

    public LoadAllGeneratorsController(MainWindow view, GeneratorModel model) {
        this.view = view;
        this.model = model;

        List<IGenerator> generators = this.model.loadAllGenerators();
        generators.forEach(generator -> view.addGeneratorView(generator.getName(), generator.getView()));
    }
}
