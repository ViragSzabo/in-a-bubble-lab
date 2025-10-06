package phase5;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class LabChart extends JPanel {
    private List<Integer> data;
    private List<String> labels;

    public void setData(List<Integer> data, List<String> labels) {
        this.data = data;
        this.labels = labels;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
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
            int x = i * width;
            int y = getHeight() - h;

            // Draw bar
            g2.fillRect(x, y, width - 2, h);

            // Draw value above bar
            g2.setColor(Color.BLACK);
            String value = String.valueOf(data.get(i));
            g2.drawString(value, x + (width / 4), y - 2);

            // Reset color for next bar
            g2.setColor(new Color(90, 120, 255, 180));

            if (labels != null && i < labels.size()) {
                g2.drawString(labels.get(i), x + 2, getHeight() - 5);
            }
        }
    }

    // Example for test
    public static LabChart sampleChart() {
        LabChart chart = new LabChart();
        Random r = new Random();

        // Generate 10 random values
        List<Integer> data = r.ints(10, 5, 100).boxed().toList();

        // Generate labels like "Item 1", "Item 2", ...
        List<String> labels = new java.util.ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            labels.add(STR."Item \{i + 1}");
        }

        chart.setData(data, labels);
        return chart;
    }
}