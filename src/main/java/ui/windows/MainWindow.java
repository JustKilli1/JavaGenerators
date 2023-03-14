package ui.windows;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainWindow extends JFrame {

    private static WindowDesign design;
    private JPanel pnlMain, pnlNorth, pnlEast, pnlSouth, pnlWest;
    private JTabbedPane tpGeneratorView;

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
        pnlWest = new JPanel();
        buildGeneratorView();

        pnlMain.add(tpGeneratorView, BorderLayout.CENTER);
        pnlMain.add(pnlWest, BorderLayout.WEST);
        pnlMain.add(pnlNorth, BorderLayout.NORTH);
        pnlMain.add(pnlSouth, BorderLayout.SOUTH);
        pnlMain.add(pnlEast, BorderLayout.EAST);
        add(pnlMain);
    }

    private void buildGeneratorView() {
        tpGeneratorView = new JTabbedPane();
    }

    public void design() {
        pnlMain.setBackground(design.getBackgroundColor());
        pnlWest.setBackground(design.getBackgroundColor());
        pnlNorth.setBackground(design.getBackgroundColor());
        pnlEast.setBackground(design.getBackgroundColor());
        pnlSouth.setBackground(design.getBackgroundColor());
        tpGeneratorView.setBackground(design.getBackgroundColor());
        tpGeneratorView.setBorder(design.getEmptyBorder());
    }

    public void changeDesign(WindowDesign design) {
        this.design = design;
    }

    public void addGeneratorViewChangeListener(ChangeListener listener) {
        tpGeneratorView.addChangeListener(listener);
    }

    public int getSelectedTabIndex() { return tpGeneratorView.getSelectedIndex(); }

    public void addGeneratorView(String name, JPanel pnlGeneratorView) {
        tpGeneratorView.addTab(name, pnlGeneratorView);
    }

    public static WindowDesign getDesign() {
        return design;
    }

}
