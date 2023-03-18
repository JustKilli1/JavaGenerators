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
    private JScrollPane spContainer;
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
            StringBuilder val = new StringBuilder();
            if(taTxtView.getText().length() != 0) val.append("\n");
            val.append(value.get(i));
            taTxtView.append(val.toString());
        }
    }

    @Override
    public void println(String value) {
        println(Arrays.asList(value));
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

    @Override
    public void clearOutput() {
        taTxtView.setText("");
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

        spContainer.setBackground(design.getBackgroundComponents());
        spContainer.setBorder(design.getBorder());
        spContainer.getVerticalScrollBar().setBackground(design.getBackgroundColor());
        spContainer.getVerticalScrollBar().setForeground(design.getBackgroundComponents());
        spContainer.getHorizontalScrollBar().setBackground(design.getBackgroundColor());
        spContainer.getHorizontalScrollBar().setForeground(design.getBackgroundComponents());

    }

    private void buildView() {
        lblTxtViewDesc = new JLabel(descriptionLabelName);
        lblTxtViewDesc.setVerticalAlignment(SwingConstants.BOTTOM);
        taTxtView = new JTextArea();
        taTxtView.setEditable(false);

        spContainer = new JScrollPane(taTxtView);

        view = new JPanel(new BorderLayout(0, 5));
        view.add(lblTxtViewDesc, BorderLayout.NORTH);
        view.add(spContainer, BorderLayout.CENTER);
    }
}
