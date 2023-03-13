package generator.output;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TxtFieldPrinterTest {

    String descLabelName = "Test Output Field";
    String testPrint = "Test";

    @Test
    void shouldChangeLabelText() {
        TxtFieldPrinter printer = new TxtFieldPrinter(descLabelName);
        JLabel label = (JLabel) printer.getView().getComponent(0);
        assertEquals(descLabelName, label.getText());
    }

    @Test
    void shouldReturnTwoComponents() {
        TxtFieldPrinter printer = new TxtFieldPrinter(descLabelName);
        assertEquals(2, printer.getView().getComponents().length);
    }

    @Test
    void shouldReturnLabelAtIndexZero() {
        TxtFieldPrinter printer = new TxtFieldPrinter(descLabelName);
        assertTrue(printer.getView().getComponent(0) instanceof JLabel);
    }

    @Test
    void shouldReturnTextFieldAtIndexOne() {
        TxtFieldPrinter printer = new TxtFieldPrinter(descLabelName);
        assertTrue(printer.getView().getComponent(1) instanceof JTextField);
    }

    @Test
    void shouldPrintTextToTextField() {
        TxtFieldPrinter printer = new TxtFieldPrinter(descLabelName);
        JPanel view = printer.getView();
        JTextField textField = (JTextField)view.getComponent(1);
        printer.println(testPrint);
        assertTrue(textField.getText().equals(testPrint));
    }
}