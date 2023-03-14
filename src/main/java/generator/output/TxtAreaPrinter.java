package generator.output;

import ui.windows.WindowDesign;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class TxtAreaPrinter implements IOutputPrinter{

    private WindowDesign design;
    private String descriptionLabelName;
    private JPanel view;
    private JLabel lblTxtViewDesc;
    private JTextArea taTxtView;


    public TxtAreaPrinter(WindowDesign design, String descriptionLabelName) {
        this.design = design;
        this.descriptionLabelName = descriptionLabelName;
        buildView();
        design();
    }

    @Override
    public void println(List<String> value) {
        for(int i = 0; i < value.size(); i++) {
            String val = value.get(i);
            if(i == 0) {
                println(val);
                continue;
            }
            taTxtView.append("\n" + val);
        }
    }

    @Override
    public void println(String value) {
        taTxtView.setText(value);
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

        taTxtView.setBackground(design.getBackgroundComponents());
        taTxtView.setForeground(design.getTextColor());
        taTxtView.setFont(design.getTextFont());
        taTxtView.setCaretColor(design.getCaretColor());
        taTxtView.setBorder(design.getBorder());
    }

    private void buildView() {
        lblTxtViewDesc = new JLabel(descriptionLabelName);
        lblTxtViewDesc.setVerticalAlignment(SwingConstants.BOTTOM);
        taTxtView = new JTextArea();
        taTxtView.setEditable(false);

        view = new JPanel(new BorderLayout(0, 5));
        view.add(lblTxtViewDesc, BorderLayout.NORTH);
        view.add(taTxtView, BorderLayout.CENTER);
    }
}
