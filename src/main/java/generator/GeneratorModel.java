package generator;

import shared.Utils;
import shared.logging.ILogger;
import shared.logging.LogCategory;
import shared.logging.LogLevel;
import shared.logging.loggergroups.LoggerGroupDialogFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GeneratorModel {

    private ILogger logger;
    private List<IGenerator> generators;

    public GeneratorModel() {
        logger = new LoggerGroupDialogFile("ModelLogger", LogCategory.SYSTEM, "Model");
        generators = new ArrayList<>();
    }

    /**
     * Adds a new IGenerator to the {@code generators List}
     * @param generator Generator that gets added
     * */
    public void addGenerator(IGenerator generator) {
        generators.add(generator);
    }

    public void generate(int index) {
        generators.get(index).generate();
    }

    /**
     * Gets a Generator with the specified index
     * @param index Index of the desired Generator in the {@code generators List}
     * @return An Empty Optional if the given index >= {@code generators.size()}<br>
     *         A IGenerator Optional if the index was in bounds
     * */
    public Optional<IGenerator> getGenerator(int index) {
        if(index >= generators.size()) return Optional.empty();
        return Optional.ofNullable(generators.get(index));
    }

    public List<IGenerator> getGenerators() {
        return generators;
    }

    public Optional<List<IGenerator>> loadAllGenerators() {
        try {
            generators = Utils.findAllGenerators("generator.generators");
            return Optional.ofNullable(generators);
        } catch(Exception ex) {
            logger.log(LogLevel.ERROR, "Could not load Generators", ex);
            return Optional.empty();
        }
    }
}
