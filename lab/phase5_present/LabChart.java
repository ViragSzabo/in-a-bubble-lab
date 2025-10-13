package phase5_present;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class LabChart extends JPanel {
    private List<Integer> data = List.of();
    private List<String> labels = List.of();

    public void setData(List<Integer> data, List<String> labels) {
        this.data = List.copyOf(data);
        this.labels = List.copyOf(labels);
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (data.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth() / data.size();
        int max = data.stream().max(Integer::compareTo).orElse(1);

        for (int i = 0; i < data.size(); i++) {
            int h = (int) ((data.get(i) / (double) max) * (getHeight() - 40));
            int x = i * width;
            int y = getHeight() - h - 20;

            g2.setColor(new Color(90, 120, 255, 180));
            g2.fillRect(x + 2, y, width - 4, h);

            g2.setColor(Color.BLACK);
            String value = String.valueOf(data.get(i));
            g2.drawString(value, x + width / 4, y - 2);

            if (labels != null && i < labels.size()) {
                g2.drawString(labels.get(i), x + 2, getHeight() - 5);
            }
        }
    }

    public static LabChart sampleChart() {
        LabChart chart = new LabChart();
        Random r = new Random();
        List<Integer> data = r.ints(10, 5, 100).boxed().toList();
        List<String> labels = data.stream()
                .map(i -> "Item " + (data.indexOf(i) + 1))
                .toList();
        chart.setData(data, labels);
        return chart;
    }
}