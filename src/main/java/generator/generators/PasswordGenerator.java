package generator.generators;

import generator.IGenerator;
import generator.output.IOutputPrinter;
import generator.output.TxtFieldPrinter;
import shared.Utils;
import ui.windows.WindowDesign;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PasswordGenerator implements IGenerator<String> {

    private List<IOutputPrinter> outputPrinter;
    private char[] alphabet, specialChars;

    private final String name = "Password Generator";
    private WindowDesign design;
    private JPanel view, pnlPasswordLength, pnlSpecialCharacter;
    private JLabel lblPasswordLengthDesc, lblSpecialCharsDesc;
    private JSlider sPasswordLength;
    private JTextField tfSpecialChars;
    private JButton cmdGenerate;

    public PasswordGenerator(WindowDesign design) {
        this.design = design;
        outputPrinter = Arrays.asList(
                new TxtFieldPrinter(this.design, "Password")
        );
        alphabet = Utils.generateAlphabet(false);
        buildView();
        design();
    }

    private void buildView() {
        view = new JPanel(new GridLayout(4, 1, 10, 20));
        outputPrinter.forEach(printer -> view.add(printer.getView()));
        buildPasswordOptions();
    }

    /**
     * Builds the Password Options Components.
     * */
    private void buildPasswordOptions() {
        lblPasswordLengthDesc = new JLabel();
        lblPasswordLengthDesc.setVerticalAlignment(SwingConstants.BOTTOM);
        updatePasswordLengthDesc(20);
        sPasswordLength = new JSlider(1, 200, 20);
        sPasswordLength.addChangeListener(event -> updatePasswordLengthDesc());

        lblSpecialCharsDesc = new JLabel("Special Character");
        lblSpecialCharsDesc.setVerticalAlignment(SwingConstants.BOTTOM);
        tfSpecialChars = new JTextField();
        tfSpecialChars.setText("!\"§$%&/()=?`{[]}\\+*#',;.:-_<>|");

        cmdGenerate = new JButton("Generate");
        cmdGenerate.addActionListener(event -> generate());

        pnlPasswordLength = new JPanel(new BorderLayout(0, 5));
        pnlPasswordLength.add(lblPasswordLengthDesc, BorderLayout.CENTER);
        pnlPasswordLength.add(sPasswordLength, BorderLayout.SOUTH);

        pnlSpecialCharacter = new JPanel(new BorderLayout(0, 5));
        pnlSpecialCharacter.add(lblSpecialCharsDesc, BorderLayout.CENTER);
        pnlSpecialCharacter.add(tfSpecialChars, BorderLayout.SOUTH);

        view.add(pnlPasswordLength);
        view.add(pnlSpecialCharacter);
        view.add(cmdGenerate);
    }

    public void updatePasswordLengthDesc(int newValue) {
        lblPasswordLengthDesc.setText("Password Length: " + newValue + " Characters");
    }

    public void updatePasswordLengthDesc() {
        updatePasswordLengthDesc(getCurrentLength());
    }

    private int getCurrentLength() { return sPasswordLength.getValue(); }

    @Override
    public String generate() {
        int length = getCurrentLength();
        String password = "";
        for(int i = 0; i < length; i++) {
            char randomChar = getRandomChar();
            if(password.length() > 0) {
                while(password.toCharArray()[password.length() - 1] == randomChar) {
                    randomChar = getRandomChar();
                }
            }
            password += randomChar;
        }
        notifyPrinter(password);
        return password;
    }

    private void notifyPrinter(String msg) {
        outputPrinter.forEach(printer -> printer.println(msg));
    }

    /**
     * Generates a Random Char.
     * To Achieve this, this Method calls two Methods that generate either a Letter or,
     * if any present in the {@code specialChars} array, a Special Character.
     * */
    private char getRandomChar() {
        if(specialChars != null && specialChars.length > 0)
            return ThreadLocalRandom.current().nextInt(0, 10) < 4 ? getRandomSpecialChar() : getRandomLetter();
        else return getRandomLetter();
    }

    /**
     * Gets a Random Letter from the {@code alphabet} array.
     * Returns it either as Upper or Lower Case char.
     * */
    private char getRandomLetter() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        char randomChar = alphabet[random.nextInt(alphabet.length)];
        return random.nextInt() % 2 == 0 ? Character.toUpperCase(randomChar) : Character.toLowerCase(randomChar);
    }

    /**
     * Gets a random Special Character from the {@code specialChars} array.
     * Notice that this Method does not check if the array is null
     * */
    private char getRandomSpecialChar() {
        return specialChars[ThreadLocalRandom.current().nextInt(specialChars.length)];
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
        design();
    }

    private void design() {
        view.setBackground(design.getBackgroundColor());
        view.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        lblPasswordLengthDesc.setFont(design.getTextFont());
        lblPasswordLengthDesc.setForeground(design.getTextColor());
        lblPasswordLengthDesc.setBackground(design.getBackgroundComponents());

        sPasswordLength.setBackground(design.getBackgroundComponents());
        sPasswordLength.setForeground(design.getTextColor());
        sPasswordLength.setBorder(design.getBorder());

        lblSpecialCharsDesc.setFont(design.getTextFont());
        lblSpecialCharsDesc.setForeground(design.getTextColor());
        lblSpecialCharsDesc.setBackground(design.getBackgroundComponents());

        tfSpecialChars.setBackground(design.getBackgroundComponents());
        tfSpecialChars.setForeground(design.getTextColor());
        tfSpecialChars.setFont(design.getTextFont());
        tfSpecialChars.setCaretColor(design.getCaretColor());
        tfSpecialChars.setBorder(design.getBorder());

        cmdGenerate.setBackground(design.getBackgroundComponents());
        cmdGenerate.setForeground(design.getTextColor());
        cmdGenerate.setBorder(design.getBorder());
        cmdGenerate.setFont(design.getHeaderFont());

        pnlPasswordLength.setBackground(design.getBackgroundColor());
        pnlSpecialCharacter.setBackground(design.getBackgroundColor());
    }
}