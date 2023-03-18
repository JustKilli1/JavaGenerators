package generator.generators;

import generator.IGenerator;
import generator.output.FilePrinter;
import generator.output.IOutputPrinter;
import generator.output.TxtAreaPrinter;
import shared.Utils;
import ui.windows.WindowDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordGenerator implements IGenerator<String> {

    private WindowDesign design;
    private List<IOutputPrinter> outputPrinter;
    private char[] alphabet, specialChars;
    private int passwordLength;
    private final String name = "Password Generator";
    private JPanel view, pnlPasswordLength, pnlSpecialCharacter, pnlPasswordAmount;
    private JLabel lblPasswordLengthDesc, lblPasswordAmountDesc, lblSpecialCharsDesc;
    private JSlider sPasswordLength;
    private JTextField tfSpecialChars, tfPasswordAmount;
    private JButton cmdGenerate;

    public PasswordGenerator(WindowDesign design, List<IOutputPrinter> outputPrinter) {
        this.outputPrinter = outputPrinter;
        this.design = design;
        alphabet = Utils.generateAlphabet(false);
        buildView();
        design();
    }

    public PasswordGenerator(WindowDesign design) {
        this(design, Arrays.asList(
                new TxtAreaPrinter(design, "Password"),
                new FilePrinter("Passwords")
        ));
    }

    /**
     * Constructor mainly used for JUnit Tests
     * */
    public PasswordGenerator(WindowDesign design, int passwordLength) {
        this(design);
        this.passwordLength = passwordLength;
    }
    /**
     * Method to build the GUI view
     * */
    private void buildView() {
        view = new JPanel(new GridLayout(4 + outputPrinter.size(), 1, 10, 20));
        outputPrinter.stream()
                        .filter(printer -> printer.getView() != null)
                        .forEach(outputPrinter -> view.add(outputPrinter.getView()));
        buildPasswordOptions();
        getCurrentLength();
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

        lblPasswordAmountDesc = new JLabel("Special Character");
        lblPasswordAmountDesc.setVerticalAlignment(SwingConstants.BOTTOM);
        tfPasswordAmount = new JTextField();
        tfPasswordAmount.setText("1");

        cmdGenerate = new JButton("Generate");
        cmdGenerate.addActionListener(new PassGenActionListener());

        pnlPasswordLength = new JPanel(new BorderLayout(0, 5));
        pnlPasswordLength.add(lblPasswordLengthDesc, BorderLayout.CENTER);
        pnlPasswordLength.add(sPasswordLength, BorderLayout.SOUTH);

        pnlPasswordAmount = new JPanel(new BorderLayout(0, 5));
        pnlPasswordAmount.add(lblPasswordAmountDesc, BorderLayout.CENTER);
        pnlPasswordAmount.add(tfPasswordAmount, BorderLayout.SOUTH);

        pnlSpecialCharacter = new JPanel(new BorderLayout(0, 5));
        pnlSpecialCharacter.add(lblSpecialCharsDesc, BorderLayout.CENTER);
        pnlSpecialCharacter.add(tfSpecialChars, BorderLayout.SOUTH);

        view.add(pnlPasswordLength);
        view.add(pnlPasswordAmount);
        view.add(pnlSpecialCharacter);
        view.add(cmdGenerate);
    }

    public void updatePasswordLengthDesc(int newValue) {
        lblPasswordLengthDesc.setText("Password Length: " + newValue + " Characters");
    }

    public void updatePasswordLengthDesc() {
        updatePasswordLengthDesc(getCurrentLength());
    }

    private int getCurrentLength() {
        passwordLength = sPasswordLength.getValue();
        return passwordLength;
    }

    private int getCurrentAmount() {
        return Integer.parseInt(tfPasswordAmount.getText());
    }

    @Override
    public String generate() {
        StringBuilder password = new StringBuilder();
        for(int i = 0; i < passwordLength; i++) {
            char randomChar = getRandomChar();
            if(password.length() > 0) {
                while(password.charAt(password.length() - 1) == randomChar) {
                    randomChar = getRandomChar();
                }
            }
            password.append(randomChar);
        }
        notifyPrinter(password.toString());
        return password.toString();
    }

    @Override
    public List<String> generate(long amount) {
        outputPrinter.forEach(printer -> printer.clearOutput());
        List<String> generatedValues = new ArrayList<>();
        for(int y = 0; y < amount; y++) generatedValues.add(generate());
        return generatedValues;
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
            return new SecureRandom().nextInt(10) < 4 ? getRandomSpecialChar() : getRandomLetter();
        else return getRandomLetter();
    }

    /**
     * Gets a Random Letter from the {@code alphabet} array.
     * Returns it either as Upper or Lower Case char.
     * */
    private char getRandomLetter() {
        SecureRandom random = new SecureRandom();
        char randomChar = alphabet[random.nextInt(alphabet.length)];
        return random.nextInt() % 2 == 0 ? Character.toUpperCase(randomChar) : Character.toLowerCase(randomChar);
    }

    /**
     * Gets a random Special Character from the {@code specialChars} array.
     * Notice that this Method does not check if the array is null
     * */
    private char getRandomSpecialChar() {
        return specialChars[new SecureRandom().nextInt(specialChars.length)];
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

    class PassGenActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder amountStr = new StringBuilder(tfPasswordAmount.getText());
            specialChars = tfSpecialChars.getText().toCharArray();
            while(amountStr.length() > 0) {
                int length = amountStr.length() > 18 ? 18 : amountStr.length();
                String str = amountStr.substring(0, length);
                generate(Long.parseLong(str));
                amountStr.delete(0, length);
            }
            //18
        }
    }
}
