package phase5_present;

import javax.swing.*;
import java.awt.*;

public abstract class Tabbed extends JFrame {
    protected final JTabbedPane tabs = new JTabbedPane();

    public Tabbed() {
        setLayout(new BorderLayout());
    }

    protected void addLabTabs(JPanel controlPanel, JPanel visualizationPanel) {
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.add(new JLabel("✨ Experiment history will appear here."), BorderLayout.CENTER);

        tabs.addTab("Control", controlPanel);
        tabs.addTab("Visualization", visualizationPanel);
        tabs.addTab("History", historyPanel);

        tabs.setFont(LabUIStyle.titleFont(14));
        tabs.setPreferredSize(new Dimension(700, 500));
        add(tabs, BorderLayout.CENTER);
    }
}