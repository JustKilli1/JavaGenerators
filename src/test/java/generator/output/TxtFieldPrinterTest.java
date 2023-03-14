package generator.output;

import base.Main;
import org.junit.jupiter.api.Test;
import ui.windows.WindowDesign;

import javax.swing.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TxtFieldPrinterTest {

    String descLabelName = "Test Output Field";
    String testPrint = "Test";
    WindowDesign design = Main.buildDesign();

    @Test
    void shouldChangeLabelText() {
        TxtFieldPrinter printer = new TxtFieldPrinter(design, descLabelName);
        JLabel label = (JLabel) printer.getView().getComponent(0);
        assertEquals(descLabelName, label.getText());
    }

    @Test
    void shouldReturnTwoComponents() {
        TxtFieldPrinter printer = new TxtFieldPrinter(design, descLabelName);
        assertEquals(2, printer.getView().getComponents().length);
    }

    @Test
    void shouldReturnLabelAtIndexZero() {
        TxtFieldPrinter printer = new TxtFieldPrinter(design, descLabelName);
        assertTrue(printer.getView().getComponent(0) instanceof JLabel);
    }

    @Test
    void shouldReturnTextFieldAtIndexOne() {
        TxtFieldPrinter printer = new TxtFieldPrinter(design, descLabelName);
        assertTrue(printer.getView().getComponent(1) instanceof JTextField);
    }

    @Test
    void shouldPrintTextToTextField() {
        TxtFieldPrinter printer = new TxtFieldPrinter(design, descLabelName);
        JPanel view = printer.getView();
        JTextField textField = (JTextField)view.getComponent(1);
        printer.println(testPrint);
        assertTrue(textField.getText().equals(testPrint));
    }
}