package phase3_simulation;

import phase4_subjects.DatasetManagement;
import phase4_subjects.Experiment;
import phase5.LabChart;
import phase5.LabUIStyle;
import phase5.Tabbed;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Objects;

public class LabControlPanel extends Tabbed {

    private JComboBox<String> structureCombo;
    private JComboBox<String> algorithmCombo;
    private JTextArea outputArea;
    private JProgressBar progressBar;
    private File importedFile;
    private List<String> importedData;
    private LabChart chart;

    public LabControlPanel() {
        super();
        setTitle("🧫 In A Bubble Lab - Control Panel");
        LabUIStyle.applyGlobalStyle();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panels
        JPanel controlPanel = createControlPanel();
        JPanel visualizationPanel = new JPanel(new BorderLayout());

        // In constructor:
        chart = new LabChart();
        visualizationPanel.add(chart, BorderLayout.CENTER);

        // Add tabs
        addLabTabs(controlPanel, visualizationPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Animated title
        new Timer(150, _ -> {
            String[] msgs = {"✨ Initializing...", "🔬 Loading equipment...", "🧪 Ready!"};
            int idx = (int) ((System.currentTimeMillis() / 1500) % msgs.length);
            setTitle(STR."In A Bubble Lab – \{msgs[idx]}");
        }).start();
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Top panel: Dataset import
        JPanel topPanel = new JPanel();
        JButton importButton = new JButton("Import Dataset");
        topPanel.add(importButton);
        panel.add(topPanel, BorderLayout.NORTH);

        // Middle panel: structure & algorithm selection
        JPanel middlePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        middlePanel.add(new JLabel("Choose Data Structure:"));
        structureCombo = new JComboBox<>(new String[]{"Queue", "Stack", "Set"});
        middlePanel.add(structureCombo);

        middlePanel.add(new JLabel("Choose Algorithm:"));
        algorithmCombo = new JComboBox<>(new String[]{
                "Bubble", "Smart Bubble", "LabScanner", "LabSniper", "SoapySquad", "FoamMaster"
        });
        middlePanel.add(algorithmCombo);

        panel.add(middlePanel, BorderLayout.CENTER);

        // Bottom panel: buttons, output, progress bar
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(500, 200));

        JPanel buttonsPanel = new JPanel();
        JButton runButton = new JButton("Run");
        JButton resetButton = new JButton("Reset");
        buttonsPanel.add(runButton);
        buttonsPanel.add(resetButton);
        bottomPanel.add(buttonsPanel, BorderLayout.NORTH);

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        bottomPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        bottomPanel.add(progressBar, BorderLayout.SOUTH);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Button hover effect
        runButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                runButton.setBackground(new Color(120, 150, 255));
                runButton.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                runButton.setBackground(UIManager.getColor("control"));
                runButton.setForeground(Color.BLACK);
            }
        });

        // Actions
        importButton.addActionListener(_ -> importDataset());
        runButton.addActionListener(_ -> runExperimentInBackground());
        resetButton.addActionListener(_ -> resetDataset());

        return panel;
    }

    private void importDataset() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            importedFile = fileChooser.getSelectedFile();
            importedData = new DatasetManagement().loadDataset(importedFile);
            outputArea.setText(
                    "Imported: " + importedFile.getName() +
                            "\nTotal items: " + importedData.size()
            );
        }
    }

    private void runExperimentInBackground() {
        if (importedData == null || importedData.isEmpty()) {
            outputArea.append("\nPlease import a dataset first!\n");
            return;
        }

        String structure = (String) structureCombo.getSelectedItem();
        String algorithm = (String) algorithmCombo.getSelectedItem();

        SwingWorker<String, Integer> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                List<Integer> chartData = importedData.stream()
                        .map(String::length)
                        .toList();
                List<String> labels = importedData;

                // Update chart initially
                chart.setData(chartData, labels);

                // Progress simulation
                for (int i = 0; i <= 100; i += 5) {
                    Thread.sleep(50);
                    publish(i);
                }

                // Measure execution time
                long startTime = System.nanoTime();

                Experiment experiment = new Experiment();
                String result = experiment.runExperiment(importedData, structure, algorithm);

                //long endTime = System.nanoTime();
                //double durationSeconds = (endTime - startTime) / 1_000_000_000.0;
                double durationSeconds = System.nanoTime() - startTime;

                String formatted;
                if (durationSeconds < 1_000) {
                    formatted = durationSeconds + " ns";
                } else if (durationSeconds < 1_000_000) {
                    formatted = String.format("%.2f μs", durationSeconds / 1_000.0);
                } else {
                    formatted = String.format("%.3f ms", durationSeconds / 1_000_000.0);
                }

                // Update chart after experiment (optional)
                chart.setData(chartData, labels);

                // Return result including execution time
                return result + String.format("\n⏱ Execution time: %.2f seconds", durationSeconds);
            }

            @Override
            protected void process(List<Integer> chunks) {
                progressBar.setValue(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                try {
                    outputArea.append("\n" + get() + "\n✅ Done!\n");
                } catch (Exception ex) {
                    outputArea.append("\n❌ Error: " + ex.getMessage());
                }
            }
        };
        worker.execute();
    }

    private void resetDataset() {
        if (importedFile != null) {
            importedData = new DatasetManagement().loadDataset(importedFile);
            outputArea.append(STR."""
                Dataset reset! Total items: \{importedData.size()}""");
        } else {
            outputArea.append("\nNo dataset to reset.");
        }
    }

    public static void main() {
        SwingUtilities.invokeLater(LabControlPanel::new);
    }
}