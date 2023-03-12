package generator.output;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtFieldPrinter implements IOutputPrinter{

    private String descriptionLabelName;

    public TxtFieldPrinter(String descriptionLabelName) {
        this.descriptionLabelName = descriptionLabelName;
    }

    @Override
    public void println(List<String> value, JComponent... target) {
        value.stream().forEach(val -> println(val, target));
    }

    @Override
    public void println(String value, JComponent... target) {
        if(target == null || target.length != 1) return;
        JComponent component = target[0];
        if(!(component instanceof JTextField)) return;
        JTextField outputField = (JTextField) component;
        outputField.setText(value);
    }

    @Override
    public void print(List<String> value, JComponent... target) {
        println(value, target);
    }

    @Override
    public void print(String value, JComponent... target) {
        print(Arrays.asList(value), target);
    }

    @Override
    public List<JComponent> buildView() {
        List<JComponent> components = new ArrayList<>();

        JLabel lblTxtViewDesc = new JLabel(descriptionLabelName);
        lblTxtViewDesc.setVerticalAlignment(SwingConstants.BOTTOM);
        JTextField tfTxtView = new JTextField();
        tfTxtView.setEditable(false);

        components.add(lblTxtViewDesc);
        components.add(tfTxtView);
        return components;
    }
}
