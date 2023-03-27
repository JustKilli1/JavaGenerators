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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PasswordGenerator implements IGenerator<String> {

    private static ExecutorService passwordGenThreads;
    private static boolean generateValues;
    private WindowDesign design;
    private List<IOutputPrinter> outputPrinter;
    private char[] alphabet, specialChars;
    private int passwordLength;
    private final String name = "Password Generator";
    private JPanel view, pnlPasswordLength, pnlSpecialCharacter, pnlPasswordAmount;
    private JLabel lblPasswordLengthDesc, lblPasswordAmountDesc, lblSpecialCharsDesc;
    private JProgressBar pbGeneratorProgress;
    private JSlider sPasswordLength;
    private JTextField tfSpecialChars, tfPasswordAmount;
    private JButton cmdGenerate;

    public PasswordGenerator(WindowDesign design, List<IOutputPrinter> outputPrinter) {
        generateValues = false;
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
        //notifyPrinter(password.toString());
        return password.toString();
    }

    @Override
    public List<String> generate(long amount) {
        outputPrinter.forEach(printer -> printer.clearOutput());
        List<String> generatedValues = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
            generatedValues.add(generate());
        }
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

        lblPasswordAmountDesc.setFont(design.getTextFont());
        lblPasswordAmountDesc.setForeground(design.getTextColor());
        lblPasswordAmountDesc.setBackground(design.getBackgroundComponents());

        tfPasswordAmount.setBackground(design.getBackgroundComponents());
        tfPasswordAmount.setForeground(design.getTextColor());
        tfPasswordAmount.setFont(design.getTextFont());
        tfPasswordAmount.setCaretColor(design.getCaretColor());
        tfPasswordAmount.setBorder(design.getBorder());

        cmdGenerate.setBackground(design.getBackgroundComponents());
        cmdGenerate.setForeground(design.getTextColor());
        cmdGenerate.setBorder(design.getBorder());
        cmdGenerate.setFont(design.getHeaderFont());

        pnlPasswordLength.setBackground(design.getBackgroundColor());
        pnlPasswordAmount.setBackground(design.getBackgroundColor());
        pnlSpecialCharacter.setBackground(design.getBackgroundColor());
    }

    /**
     * Method to build the GUI view
     * */
    private void buildView() {
        view = new JPanel(new GridLayout(5 + outputPrinter.size(), 1, 10, 20));
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

        lblPasswordAmountDesc = new JLabel("Password Amount");
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

        pbGeneratorProgress = new JProgressBar();

        view.add(pnlPasswordLength);
        view.add(pnlPasswordAmount);
        view.add(pnlSpecialCharacter);
        view.add(cmdGenerate);
        view.add(pbGeneratorProgress);
    }

    private void changeMaxValueProgressBar(int value) {
        pbGeneratorProgress.setMaximum(value);
    }

    private void addValueProgressBar(int value) {
        pbGeneratorProgress.setValue(pbGeneratorProgress.getValue() + value);
    }


    class PassGenActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder amountStr = new StringBuilder(tfPasswordAmount.getText());
            specialChars = tfSpecialChars.getText().toCharArray();
            if(generateValues) {
                generateValues = false;
                return;
            } else {
                generateValues = true;
            }
            long amount = Long.parseLong(amountStr.toString());
            changeMaxValueProgressBar(Integer.parseInt(amountStr.toString()));
            pbGeneratorProgress.setValue(0);
            int threads = amount / 10 == 0 ? 1 : 10;
            long amountPerThread = amount / threads;
            passwordGenThreads = Executors.newFixedThreadPool(threads);
            for(int i = 0; i < threads; i++) {
                passwordGenThreads.execute(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0; i < amountPerThread && generateValues; i++) {
                            generate();
                            addValueProgressBar(1);
                        }
                    }
                });
            }
        }
    }
}
