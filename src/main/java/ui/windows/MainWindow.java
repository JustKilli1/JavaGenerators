package ui.windows;

import generator.IGenerator;
import ui.TestGenerator;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private WindowDesign design;
    private JPanel pnlMain, pnlGeneratorList, pnlGeneratorView, pnlNorth, pnlEast, pnlSouth;
    private JTabbedPane tpGeneratorView;
    private JList<IGenerator> lGenerators;
    private DefaultListModel<IGenerator> generatorsListModel = new DefaultListModel<>();
    private JLabel lblGeneratorsDesc;
    private JScrollPane spGeneratorsContainer;

    public MainWindow(WindowDesign design) {
        super("Java Generators");
        init();
        build();
        changeDesign(design);
        design();
    }

    private void init() {
        setSize(new Dimension(1000, 1000));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void build() {
        pnlMain = new JPanel(new BorderLayout(10, 20));
        pnlNorth = new JPanel();
        pnlEast = new JPanel();
        pnlSouth = new JPanel();
        buildGeneratorList();
        buildGeneratorView();

        pnlMain.add(pnlGeneratorList, BorderLayout.WEST);
        pnlMain.add(tpGeneratorView, BorderLayout.CENTER);
        pnlMain.add(pnlNorth, BorderLayout.NORTH);
        pnlMain.add(pnlSouth, BorderLayout.SOUTH);
        pnlMain.add(pnlEast, BorderLayout.EAST);
        add(pnlMain);
    }

    private void buildGeneratorList() {
        lblGeneratorsDesc = new JLabel("Generators", SwingConstants.CENTER);
        lGenerators = new JList<>(generatorsListModel);
        spGeneratorsContainer = new JScrollPane(lGenerators);

        pnlGeneratorList = new JPanel(new BorderLayout(10, 10));
        pnlGeneratorList.add(lblGeneratorsDesc, BorderLayout.NORTH);
        pnlGeneratorList.add(spGeneratorsContainer, BorderLayout.CENTER);
        addGenerator(new TestGenerator());
        addGenerator(new TestGenerator());
        addGenerator(new TestGenerator());
    }

    private void buildGeneratorView() {
        tpGeneratorView = new JTabbedPane();
    }

    public void design() {
        pnlMain.setBackground(design.getBackgroundColor());
        pnlNorth.setBackground(design.getBackgroundColor());
        pnlEast.setBackground(design.getBackgroundColor());
        pnlSouth.setBackground(design.getBackgroundColor());
        pnlGeneratorList.setBackground(design.getBackgroundColor());
        pnlGeneratorList.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        lblGeneratorsDesc.setBackground(design.getBackgroundColor());
        lblGeneratorsDesc.setFont(design.getHeaderFont());
        lblGeneratorsDesc.setForeground(design.getHeaderColor());

        lGenerators.setBackground(design.getBackgroundComponents());
        lGenerators.setFont(design.getTextFont());
        lGenerators.setForeground(design.getTextColor());
        lGenerators.setSelectionBackground(design.getSelectionBackground());
        lGenerators.setSelectionForeground(design.getSelectionForeground());
        lGenerators.setBorder(design.getBorder());

        tpGeneratorView.setBackground(design.getBackgroundColor());

        spGeneratorsContainer.setBackground(design.getBackgroundColor());
    }

    public void changeDesign(WindowDesign design) {
        this.design = design;
    }

    public void addGenerator(IGenerator generator) {
        generatorsListModel.add(generatorsListModel.getSize(), generator);
    }

}
