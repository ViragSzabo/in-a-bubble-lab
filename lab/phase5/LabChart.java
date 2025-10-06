package phase5;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class LabChart extends JPanel {
    private List<Integer> data;

    public void setData(List<Integer> data) {
        this.data = data;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 300);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (data == null || data.isEmpty()) return;
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(90, 120, 255, 180));
        int width = getWidth() / data.size();
        int max = data.stream().max(Integer::compare).orElse(1);
        for (int i = 0; i < data.size(); i++) {
            int h = (int) ((data.get(i) / (double) max) * getHeight());
            g2.fillRect(i * width, getHeight() - h, width - 2, h);
        }
    }

    // Example for test
    public static LabChart sampleChart() {
        LabChart chart = new LabChart();
        Random r = new Random();
        chart.setData(r.ints(10, 5, 100).boxed().toList());
        return chart;
    }
}