package phase5_present;

import javax.swing.*;
import java.awt.*;

public class ProgressBar extends JPanel {
    private final JProgressBar progressBar;

    public ProgressBar() {
        setLayout(new BorderLayout());
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        add(progressBar, BorderLayout.SOUTH);
    }

    public void setProgress(int value) {
        progressBar.setValue(Math.min(Math.max(value, 0), 100));
    }
}