package shared;

import base.Main;
import generator.IGenerator;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ui.windows.WindowDesign;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    /**
     * Generates a Alphabet and returns it as a Char Array
     * @param capital Defines if the Letters int the returned char Array should be Capital
     * @return Alphabet as Char Array
     * */
    public static char[] generateAlphabet(boolean capital) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        return capital ? alphabet.toUpperCase().toCharArray() : alphabet.toCharArray();
    }

    public static ArrayList<IGenerator> findAllGenerators(String packageName) throws Exception {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        List<Class> classes = reflections.getSubTypesOf(IGenerator.class)
                            .stream()
                            .collect(Collectors.toList());
        ArrayList<IGenerator> generators = new ArrayList<>();
        for(Class clazz : classes) {
            Constructor constructor = clazz.getDeclaredConstructor(WindowDesign.class);
            generators.add((IGenerator) constructor.newInstance(Main.buildDesign()));
        }
        return generators;
    }

}
