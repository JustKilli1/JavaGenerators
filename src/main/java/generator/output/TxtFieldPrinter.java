package generator.output;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtFieldPrinter implements IOutputPrinter{

    private String descriptionLabelName;
    private JLabel lblTxtViewDesc;
    private JTextField tfTxtView;


    public TxtFieldPrinter(String descriptionLabelName) {
        this.descriptionLabelName = descriptionLabelName;
        buildView();
    }

    @Override
    public void println(List<String> value) {
        value.stream().forEach(val -> println(val));
    }

    @Override
    public void println(String value) {
        tfTxtView.setText(value);
    }

    @Override
    public void print(List<String> value) {
        println(value);
    }

    @Override
    public void print(String value) {
        print(Arrays.asList(value));
    }

    @Override
    public JPanel getView() {
        JPanel view = new JPanel(new BorderLayout(10, 10));
        view.add(lblTxtViewDesc, BorderLayout.NORTH);
        view.add(tfTxtView, BorderLayout.CENTER);
        return view;
    }

    private List<JComponent> buildView() {
        List<JComponent> components = new ArrayList<>();

        lblTxtViewDesc = new JLabel(descriptionLabelName);
        lblTxtViewDesc.setVerticalAlignment(SwingConstants.BOTTOM);
        tfTxtView = new JTextField();
        tfTxtView.setEditable(false);

        components.add(lblTxtViewDesc);
        components.add(tfTxtView);
        return components;
    }
}
