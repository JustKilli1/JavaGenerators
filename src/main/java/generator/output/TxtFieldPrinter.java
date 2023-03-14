package generator.output;

import ui.windows.WindowDesign;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtFieldPrinter implements IOutputPrinter{

    private WindowDesign design;
    private String descriptionLabelName;
    private JPanel view;
    private JLabel lblTxtViewDesc;
    private JTextField tfTxtView;


    public TxtFieldPrinter(WindowDesign design, String descriptionLabelName) {
        this.design = design;
        this.descriptionLabelName = descriptionLabelName;
        buildView();
        design();
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
        return view;
    }

    @Override
    public void changeDesign(WindowDesign design) {
        this.design = design;
        design();
    }

    private void design() {
        view.setBackground(design.getBackgroundColor());

        lblTxtViewDesc.setFont(design.getTextFont());
        lblTxtViewDesc.setForeground(design.getTextColor());
        lblTxtViewDesc.setBackground(design.getBackgroundComponents());

        tfTxtView.setBackground(design.getBackgroundComponents());
        tfTxtView.setForeground(design.getTextColor());
        tfTxtView.setFont(design.getTextFont());
        tfTxtView.setCaretColor(design.getCaretColor());
        tfTxtView.setBorder(design.getBorder());
    }

    private void buildView() {
        lblTxtViewDesc = new JLabel(descriptionLabelName);
        lblTxtViewDesc.setVerticalAlignment(SwingConstants.BOTTOM);
        tfTxtView = new JTextField();
        tfTxtView.setEditable(false);

        view = new JPanel(new BorderLayout(0, 5));
        view.add(lblTxtViewDesc, BorderLayout.NORTH);
        view.add(tfTxtView, BorderLayout.CENTER);
    }
}
