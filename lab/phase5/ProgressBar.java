package phase5;

import javax.swing.*;
import java.awt.*;

public class ProgressBar {
    private final JProgressBar progressBar = new JProgressBar(0, 100);

    private void setupProgressBar(JPanel bottomPanel) {
        progressBar.setStringPainted(true);
        bottomPanel.add(progressBar, BorderLayout.SOUTH);
    }

    private JPanel createVisualizationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(LabChart.sampleChart(), BorderLayout.CENTER);
        return panel;
    }
}